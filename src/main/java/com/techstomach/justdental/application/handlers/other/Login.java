package com.techstomach.justdental.application.handlers.other;


import com.google.gson.Gson;
import com.techstomach.justdental.handles.ConfigHandle;
import com.techstomach.justdental.handles.DbHandle;
import com.techstomach.justdental.model.enums.ServiceState;
import com.techstomach.justdental.model.schema.User;
import com.techstomach.justdental.model.service.PatientResp;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;

import java.util.Base64;
import java.util.List;

public class Login implements Handler<RoutingContext> {

    private Vertx vertx;
    private ConfigHandle configHandle;
    private static Logger log = LoggerFactory.getLogger(Login.class);

    public Login(Vertx vertx) {
        this.vertx = vertx;
        this.configHandle = ConfigHandle.getInstance();
    }

    @Override
    public void handle(RoutingContext routingContext) {

        HttpServerRequest request = routingContext.request();
        String uri = request.uri();
        log.info("request ==> uri = " + uri + " from = " + request.remoteAddress() + " body = " + routingContext.getBodyAsString());

        String phoneNumber = routingContext.request().getParam("phoneNumber");
        String pwd = routingContext.request().getParam("pwd");

        if(configHandle.getServiceState() != ServiceState.STARTED)
        {
            routingContext.response().end("service is not started");
            return;
        }

        log.info("login service " + phoneNumber + ":" + pwd);
        if(phoneNumber == null || phoneNumber.length() == 0)
        {
            routingContext.response().end("phoneNumber not provided");
            return;
        }
        if(pwd == null || pwd.length() == 0)
        {
            routingContext.response().end("pwd not provided");
            return;
        }

        List list = DbHandle.getInstance().getUserByPhoneNumber(phoneNumber, "test");
        log.info("list: " + list);

        Gson gson = new Gson();
        PatientResp patientResp = new PatientResp();
        patientResp.setPhoneNumber(phoneNumber);

        if(list.size() == 0)
        {
            patientResp.setError("no user found with phone number " + phoneNumber);
            routingContext.response().end(gson.toJson(patientResp));
            return;
        }
        if(list.size() > 1)
        {
            patientResp.setError("multiple users found with phone number " + phoneNumber);
            routingContext.response().end(gson.toJson(patientResp));
            return;
        }

        User user = (User)list.get(0);
        patientResp.setFirstName(user.getFirstName());
        patientResp.setLastName(user.getLastName());

        if(!user.getPassword().contentEquals(pwd))
        {
            patientResp.setError("password provided for user with phone number " + phoneNumber + " is incorrect");
            routingContext.response().end(gson.toJson(patientResp));
            return;
        }

        byte[] encodedBytes = Base64.getEncoder().encode(phoneNumber.getBytes());
        patientResp.setKey(new String(encodedBytes));

        log.info("decoded to: "+ new String(Base64.getDecoder().decode(encodedBytes)));
        routingContext.response().end(gson.toJson(patientResp));
    }
}
