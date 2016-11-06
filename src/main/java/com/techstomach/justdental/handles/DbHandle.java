package com.techstomach.justdental.handles;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class DbHandle {

    private static DbHandle instance;
    private static Object sync = new Object();

    private static Logger log = LoggerFactory.getLogger(DbHandle.class);

    private DbHandle() {
    }

    public static DbHandle getInstance(){
        if(instance != null)  return instance;

        synchronized (sync)
        {
            if(instance != null)  return instance;

            instance = new DbHandle();
            if(initialize() == false)
            {
                instance = null;
                return null;
            }
            return instance;
        }
    }

    private static boolean initialize(){
        return true;
    }
}
