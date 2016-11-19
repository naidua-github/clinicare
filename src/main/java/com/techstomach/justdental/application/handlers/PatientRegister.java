package com.techstomach.justdental.application.handlers;

import com.google.gson.Gson;
import com.techstomach.justdental.handles.ConfigHandle;
import com.techstomach.justdental.handles.DbHandle;
import com.techstomach.justdental.model.enums.ServiceState;
import com.techstomach.justdental.model.service.*;
import com.techstomach.justdental.model.service.Error;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;

public class PatientRegister implements Handler<RoutingContext> {

    private Vertx vertx;
    private ConfigHandle configHandle;
    private static Logger log = LoggerFactory.getLogger(PatientRegister.class);

    public PatientRegister(Vertx vertx) {
        this.vertx = vertx;
        this.configHandle = ConfigHandle.getInstance();
    }

    @Override
    public void handle(RoutingContext routingContext) {

        HttpServerRequest request = routingContext.request();
        String uri = request.uri();
        log.info("request ==> uri = " + uri + " from = " + request.remoteAddress() + " body = " + routingContext.getBodyAsString());

        if(configHandle.getServiceState() != ServiceState.STARTED) {
            routingContext.response().end("service is stopped");
        }

        String payload = routingContext.getBodyAsString();
        log.info("received payload: " + payload);
        Gson gson = new Gson();
        DbRequest dbRequest = gson.fromJson(payload, DbRequest.class);
        log.info("successfully parsed json payload: " + dbRequest);

        String result = DbHandle.getInstance().registerPatient(dbRequest.getFirstName(), dbRequest.getLastName(), dbRequest.getPhoneNumber(), "");
        if(result == null)
        {
            DbResponse dbResponse = new DbResponse();
            dbResponse.setBody(new Body().setPatient(new Patient(dbRequest)));
            dbResponse.setHeader(new Header(200, "OK"));
            routingContext.response().end(dbResponse.toString());
        }
        else
        {
            DbResponse dbResponse = new DbResponse();
            dbResponse.setBody(new Body()
                    .setPatient(new Patient(dbRequest))
                    .setError(new Error("PATIENT REGISTRATION FAILED", result)));
            dbResponse.setHeader(new Header(400, "REQUEST FAILED"));
            routingContext.response().end(dbResponse.toString());
        }
    }
}
