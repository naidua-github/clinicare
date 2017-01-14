package com.techstomach.justdental.application.handlers.user;

import com.google.gson.Gson;
import com.techstomach.justdental.handles.ConfigHandle;
import com.techstomach.justdental.handles.DbHandle;
import com.techstomach.justdental.model.enums.ServiceState;
import com.techstomach.justdental.model.schema.AppUsers;
import com.techstomach.justdental.model.service.Body;
import com.techstomach.justdental.model.service.DbResponse;
import com.techstomach.justdental.model.service.Header;
import com.techstomach.justdental.model.service.UserResp;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;

public class UserShow implements Handler<RoutingContext> {

    private Vertx vertx;
    private ConfigHandle configHandle;
    private static Logger log = LoggerFactory.getLogger(UserShow.class);

    public UserShow(Vertx vertx) {
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

            AppUsers appUser = DbHandle.getInstance().showUser(userId, "");
            if(appUser != null)
            {
                DbResponse dbResponse = new DbResponse();
                Gson gson = new Gson();
                String userJson = gson.toJson(appUser);

                dbResponse.setBody(new Body().setUser(userJson));
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
                        .setError(new com.techstomach.justdental.model.service.Error("USER SHOW FAILED", "entry not found")));
                dbResponse.setHeader(new Header(200, "OK"));
                routingContext.response().end(dbResponse.toString());
            }
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
            DbResponse dbResponse = new DbResponse();
            UserResp userResp = new UserResp();

            dbResponse.setBody(new Body()
                    .setUser(userResp)
                    .setError(new com.techstomach.justdental.model.service.Error("USER SHOW FAILED", e.toString())));
            dbResponse.setHeader(new Header(400, "REQUEST FAILED"));
            routingContext.response().end(dbResponse.toString());
        }
    }
}
