package com.techstomach.justdental.application.vertx;


import com.techstomach.justdental.model.enums.ServiceState;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class AppStarterVerticle extends AbstractVerticle {


    private static Logger log = LoggerFactory.getLogger(AppStarterVerticle.class);

    @Override
    public void start(Future<Void> future) throws Exception{

        // Set configuration handle
        if(!setConfigurationHandle())
        {
            future.fail("cannot initialize configuration");
            return;
        }

        DeploymentOptions deploymentOptions = new DeploymentOptions();
        deploymentOptions.setInstances(1);
        deploymentOptions.setWorker(true);
        vertx.deployVerticle(InitVerticle.class.getCanonicalName(), deploymentOptions, handler ->
        {
            if(handler.succeeded())
            {
                log.info("init-verticle deployed successfully");
                submitEventBusRequest("init", future);
            }
            else
            {
                log.error("init-verticle cannot be deployed, reason = " + handler.cause());
                future.fail("cannot deploy init-verticle, reason = " + handler.cause());
                return;
            }
        });
    }

    public boolean setConfigurationHandle(){

        return true;
    }
    public void submitEventBusRequest(String message, Future future){
        log.info("internal message => " + message);
        DeliveryOptions deliveryOptions = new DeliveryOptions();
        deliveryOptions.setSendTimeout(3000);
        vertx.eventBus().send(InitVerticle.class.getCanonicalName(), message, deliveryOptions, messageAsyncResult -> {
            if(messageAsyncResult.succeeded())
            {
                log.info("internal message <= " + messageAsyncResult.result().body());
                future.complete();
            }
            else
            {
                log.error("internal message <= " + messageAsyncResult.cause());
                future.fail("cannot initialize init-verticle, reason = " + messageAsyncResult.cause());
                return;
            }
        });

        return;
    }

    @Override
    public void stop() throws Exception {
        log.warn("stop: stopping the service");
        vertx.deploymentIDs().stream().forEach(deploymentID -> {
            vertx.undeploy(deploymentID);
        });
        super.stop();
    }

    public static void stopService(Vertx vertx, String reason){
        log.warn("stopService: stopping the service, reason = " + reason);
        vertx.close();
    }
}
