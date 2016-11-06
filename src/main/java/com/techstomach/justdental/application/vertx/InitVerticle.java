package com.techstomach.justdental.application.vertx;

import com.techstomach.justdental.application.handlers.MonitorServiceStatus;
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
        router.get("/monitor/service/status").handler(monitorServiceStatus);

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
