package com.techstomach.justdental.handles;


import com.fasterxml.jackson.databind.deser.std.ObjectArrayDeserializer;
import com.techstomach.justdental.application.vertx.InitVerticle;
import com.techstomach.justdental.model.config.ServiceConfig;
import com.techstomach.justdental.model.enums.ServiceState;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class ConfigHandle {

    private static ConfigHandle instance;
    private static Object sync = new Object();

    private ServiceConfig serviceConfig;
    private ServiceState serviceState;
    private static Logger log = LoggerFactory.getLogger(ConfigHandle.class);

    private ConfigHandle() {
    }

    public static ConfigHandle getInstance(){
        if(instance != null)  return instance;

        synchronized (sync)
        {
            if(instance != null)  return instance;

            instance = new ConfigHandle();
            return instance;
        }
    }

    public boolean initialize(){
        return true;
    }
    public boolean validate(){
        if(serviceConfig == null || serviceConfig.validate() == false )
        {
            return false;
        }

        return true;
    }

    public ServiceState getServiceState() {
        return serviceState;
    }

    public void setServiceState(ServiceState serviceState) {
        this.serviceState = serviceState;
    }
}
