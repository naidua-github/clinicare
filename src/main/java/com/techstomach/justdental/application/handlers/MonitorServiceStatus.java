package com.techstomach.justdental.application.handlers;

import com.techstomach.justdental.application.vertx.AppStarterVerticle;
import com.techstomach.justdental.application.vertx.InitVerticle;
import com.techstomach.justdental.handles.ConfigHandle;
import com.techstomach.justdental.model.enums.ServiceState;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;

public class MonitorServiceStatus implements Handler<RoutingContext> {

    private Vertx vertx;
    private ConfigHandle configHandle;
    private static Logger log = LoggerFactory.getLogger(MonitorServiceStatus.class);

    public MonitorServiceStatus(Vertx vertx) {
        this.vertx = vertx;
        this.configHandle = ConfigHandle.getInstance();
    }

    @Override
    public void handle(RoutingContext routingContext) {

        HttpServerRequest request = routingContext.request();
        String uri = request.uri();
        log.info("request ==> uri = " + uri + " from = " + request.remoteAddress() + " body = " + routingContext.getBodyAsString());

        if(configHandle.getServiceState() == ServiceState.STARTED)
            routingContext.response().end("service is started");
        else
            routingContext.response().end("service is stopped");

    }
}
