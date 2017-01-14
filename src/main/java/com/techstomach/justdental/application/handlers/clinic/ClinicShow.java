package com.techstomach.justdental.application.handlers.clinic;

import com.google.gson.Gson;
import com.techstomach.justdental.handles.ConfigHandle;
import com.techstomach.justdental.handles.DbHandle;
import com.techstomach.justdental.model.enums.ServiceState;
import com.techstomach.justdental.model.schema.Clinic;
import com.techstomach.justdental.model.service.Body;
import com.techstomach.justdental.model.service.ClinicResp;
import com.techstomach.justdental.model.service.DbResponse;
import com.techstomach.justdental.model.service.Header;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;

public class ClinicShow  implements Handler<RoutingContext> {

    private Vertx vertx;
    private ConfigHandle configHandle;
    private static Logger log = LoggerFactory.getLogger(ClinicShow.class);

    public ClinicShow(Vertx vertx) {
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
            String cid = routingContext.request().getParam("clinicId");
            Integer clinicId = Integer.valueOf(cid);

            Clinic clinic = DbHandle.getInstance().showClinic(clinicId, "");
            if(clinic != null)
            {
                DbResponse dbResponse = new DbResponse();
                Gson gson = new Gson();
                String clinicJson = gson.toJson(clinic);

                dbResponse.setBody(new Body().setClinic(clinicJson));
                dbResponse.setHeader(new Header(200, "OK"));
                routingContext.response().end(dbResponse.toString());
            }
            else
            {
                DbResponse dbResponse = new DbResponse();
                ClinicResp clinicResp = new ClinicResp();
                clinicResp.setClinicId(clinicId);

                dbResponse.setBody(new Body()
                        .setClinic(clinicResp)
                        .setError(new com.techstomach.justdental.model.service.Error("CLINIC SHOW FAILED", "entry not found")));
                dbResponse.setHeader(new Header(200, "OK"));
                routingContext.response().end(dbResponse.toString());
            }
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
            DbResponse dbResponse = new DbResponse();
            ClinicResp clinicResp = new ClinicResp();

            dbResponse.setBody(new Body()
                    .setClinic(clinicResp)
                    .setError(new com.techstomach.justdental.model.service.Error("CLINIC SHOW FAILED", e.toString())));
            dbResponse.setHeader(new Header(400, "REQUEST FAILED"));
            routingContext.response().end(dbResponse.toString());
        }
    }
}

