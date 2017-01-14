package com.techstomach.justdental.application.handlers.user;

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

public class UserUnRegister implements Handler<RoutingContext> {

    private Vertx vertx;
    private ConfigHandle configHandle;
    private static Logger log = LoggerFactory.getLogger(UserUnRegister.class);

    public UserUnRegister(Vertx vertx) {
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

        try
        {
            String uid = routingContext.request().getParam("userId");
            Integer userId = Integer.valueOf(uid);

            String result = DbHandle.getInstance().deleteUser(userId, "");
            if(result == null)
            {
                DbResponse dbResponse = new DbResponse();
                UserResp userResp = new UserResp();
                userResp.setUserId(userId);

                dbResponse.setBody(new Body().setUser(userResp));
                dbResponse.setHeader(new Header(200, "OK"));
                routingContext.response().end(dbResponse.toString());
            }
            else
            {
                DbResponse dbResponse = new DbResponse();
                UserResp userResp = new UserResp();
                userResp.setUserId(userId);

                dbResponse.setBody(new Body()
                        .setUser(userResp)
                        .setError(new com.techstomach.justdental.model.service.Error("USER UN-REGISTRATION FAILED", result)));
                dbResponse.setHeader(new Header(400, "REQUEST FAILED"));
                routingContext.response().end(dbResponse.toString());
            }
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
            DbResponse dbResponse = new DbResponse();
            UserResp userResp = new UserResp();

            dbResponse.setBody(new Body()
                    .setUser(userResp)
                    .setError(new Error("USER UN-REGISTRATION FAILED", e.toString())));
            dbResponse.setHeader(new Header(400, "REQUEST FAILED"));
            routingContext.response().end(dbResponse.toString());
        }
    }
}
