package com.techstomach.justdental.application.vertx;

import com.techstomach.justdental.application.handlers.clinic.ClinicRegister;
import com.techstomach.justdental.application.handlers.clinic.ClinicShow;
import com.techstomach.justdental.application.handlers.clinic.ClinicUnRegister;
import com.techstomach.justdental.application.handlers.clinic.ClinicUpdate;
import com.techstomach.justdental.application.handlers.monitor.MonitorServiceStatus;
import com.techstomach.justdental.application.handlers.other.CreatePatientCalendar;
import com.techstomach.justdental.application.handlers.other.Login;
import com.techstomach.justdental.application.handlers.patient.PatientRegister;
import com.techstomach.justdental.application.handlers.patient.PatientShow;
import com.techstomach.justdental.application.handlers.patient.PatientUnRegister;
import com.techstomach.justdental.application.handlers.patient.PatientUpdate;
import com.techstomach.justdental.application.handlers.role.RoleRegister;
import com.techstomach.justdental.application.handlers.role.RoleShow;
import com.techstomach.justdental.application.handlers.role.RoleUnRegister;
import com.techstomach.justdental.application.handlers.role.RoleUpdate;
import com.techstomach.justdental.application.handlers.user.UserRegister;
import com.techstomach.justdental.application.handlers.user.UserShow;
import com.techstomach.justdental.application.handlers.user.UserUnRegister;
import com.techstomach.justdental.application.handlers.user.UserUpdate;
import com.techstomach.justdental.handles.ConfigHandle;
import com.techstomach.justdental.handles.DbHandle;
import com.techstomach.justdental.model.enums.ServiceState;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class InitVerticle extends AbstractVerticle {

    private String hostname = "localhost";
    private int port = 8080;
    private Router router;
    private ConfigHandle configHandle;
    private static Logger log = LoggerFactory.getLogger(InitVerticle.class);

    @Override
    public void start() throws Exception{

        vertx.eventBus().consumer(InitVerticle.class.getCanonicalName(), handler ->{

            try
            {
                if(handler.body().toString().contentEquals("init"))
                {
                    handler.reply("ok");
                    if(initialize() == false)
                    {
                        AppStarterVerticle.stopService(vertx, "cannot initialize the service");
                        return;
                    }
                }
                else
                {
                    log.error("request not supported");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }

    private boolean initialize(){
        log.info("------------------------------------------------");
        log.info("APPLICATION INITIALIZATION IS STARTED");
        log.info("------------------------------------------------");

        if(DbHandle.getInstance() == null)
        {
            return false;
        }

        if(startHttpService() == false)
        {
            return false;
        }
        log.info("------------------------------------------------");
        log.info("APPLICATION INITIALIZATION IS COMPLETED");
        log.info("------------------------------------------------");

        return true;
    }
    private boolean startHttpService(){

        configHandle = ConfigHandle.getInstance();
        router = Router.router(vertx);
        router.route().handler(BodyHandler.create());

        MonitorServiceStatus monitorServiceStatus = new MonitorServiceStatus(vertx);
        CreatePatientCalendar createPatientCalendar = new CreatePatientCalendar(vertx);
        Login login = new Login(vertx);

        //-----PATIENT------------------------------------------------------
        PatientRegister patientRegister = new PatientRegister(vertx);
        PatientUnRegister patientUnRegister = new PatientUnRegister(vertx);
        PatientUpdate patientUpdate = new PatientUpdate(vertx);
        PatientShow patientShow = new PatientShow(vertx);

        //-----USER------------------------------------------------------
        UserRegister userRegister = new UserRegister(vertx);
        UserUnRegister userUnRegister = new UserUnRegister(vertx);
        UserUpdate userUpdate = new UserUpdate(vertx);
        UserShow userShow = new UserShow(vertx);

        //-----CLINIC------------------------------------------------------
        ClinicRegister clinicRegister = new ClinicRegister(vertx);
        ClinicUnRegister clinicUnRegister = new ClinicUnRegister(vertx);
        ClinicUpdate clinicUpdate = new ClinicUpdate(vertx);
        ClinicShow clinicShow = new ClinicShow(vertx);

        //-----ROLE------------------------------------------------------
        RoleRegister roleRegister = new RoleRegister(vertx);
        RoleUnRegister roleUnRegister = new RoleUnRegister(vertx);
        RoleUpdate roleUpdate = new RoleUpdate(vertx);
        RoleShow roleShow = new RoleShow(vertx);

        router.get("/monitor/service/status").handler(monitorServiceStatus);
//        router.get("/login").handler(login);
//        router.post("/calendar/patient/create").handler(createPatientCalendar);


        router.post("/patient/register").handler(patientRegister);
        router.delete("/patient/unregister").handler(patientUnRegister);
        router.get("/patient/show").handler(patientShow);
        router.post("/patient/update").handler(patientUpdate);

        router.post("/user/register").handler(userRegister);
        router.delete("/user/unregister").handler(userUnRegister);
        router.get("/user/show").handler(userShow);
        router.post("/user/update").handler(userUpdate);

        router.post("/clinic/register").handler(clinicRegister);
        router.delete("/clinic/unregister").handler(clinicUnRegister);
        router.get("/clinic/show").handler(clinicShow);
        router.post("/clinic/update").handler(clinicUpdate);

        router.post("/role/register").handler(roleRegister);
        router.delete("/role/unregister").handler(roleUnRegister);
        router.get("/role/show").handler(roleShow);
        router.post("/role/update").handler(roleUpdate);

        log.info("### starting http server on hostname " + hostname + " and port " + port);
        vertx.createHttpServer().requestHandler(router::accept).listen(port, handler ->{
            if(handler.succeeded())
            {
                log.info("successfully started http server on " + hostname + " and port " + port);
                configHandle.setServiceState(ServiceState.STARTED);

                log.info("------------------------------------------------");
                log.info("SERVICE IS STARTED");
                log.info("------------------------------------------------");
            }
            else
            {
                log.info("cannot start http server on " + hostname + " and port " + port);
                AppStarterVerticle.stopService(vertx, "cannot start http server on " + hostname + " and port " + port);
                return;
            }
        });

        return true;
    }
}
