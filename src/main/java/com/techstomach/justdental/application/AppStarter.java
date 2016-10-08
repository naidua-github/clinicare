package com.techstomach.justdental.application;


import io.vertx.core.Vertx;

public class AppStarter {

    private Vertx vertx;

    public static void main(String[] arg)
    {
        AppStarter appStarter = new AppStarter();
        appStarter.setup();
    }

    public void setup()
    {
        vertx = Vertx.vertx();
        vertx.deployVerticle(AppStarterVerticle.class.getName());

        vertx.createHttpClient().getNow(8080, "localhost", "/",
                response -> {
                    response.handler(body -> {
                        System.out.println(body.toString());
                    });
                });
    }
}
