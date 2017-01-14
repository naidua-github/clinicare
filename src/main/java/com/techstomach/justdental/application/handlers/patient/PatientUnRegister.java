package com.techstomach.justdental.application.handlers.patient;

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

public class PatientUnRegister implements Handler<RoutingContext> {

    private Vertx vertx;
    private ConfigHandle configHandle;
    private static Logger log = LoggerFactory.getLogger(PatientUnRegister.class);

    public PatientUnRegister(Vertx vertx) {
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
            String pid = routingContext.request().getParam("patientId");
            Integer patientId = Integer.valueOf(pid);

            String result = DbHandle.getInstance().deletePatient(patientId, "");
            if(result == null)
            {
                DbResponse dbResponse = new DbResponse();
                PatientResp patientResp = new PatientResp();
                patientResp.setPatientId(patientId);

                dbResponse.setBody(new Body().setPatient(patientResp));
                dbResponse.setHeader(new Header(200, "OK"));
                routingContext.response().end(dbResponse.toString());
            }
            else
            {
                DbResponse dbResponse = new DbResponse();
                PatientResp patientResp = new PatientResp();
                patientResp.setPatientId(patientId);

                dbResponse.setBody(new Body()
                        .setPatient(patientResp)
                        .setError(new Error("PATIENT UN-REGISTRATION FAILED", result)));
                dbResponse.setHeader(new Header(400, "REQUEST FAILED"));
                routingContext.response().end(dbResponse.toString());
            }
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
            DbResponse dbResponse = new DbResponse();
            PatientResp patientResp = new PatientResp();

            dbResponse.setBody(new Body()
                    .setPatient(patientResp)
                    .setError(new Error("PATIENT UN-REGISTRATION FAILED", e.toString())));
            dbResponse.setHeader(new Header(400, "REQUEST FAILED"));
            routingContext.response().end(dbResponse.toString());
        }
    }
}
