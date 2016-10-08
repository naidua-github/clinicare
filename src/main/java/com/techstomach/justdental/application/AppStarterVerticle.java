package com.techstomach.justdental.application;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class AppStarterVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> fut) {
        vertx
                .createHttpServer()
                .requestHandler(r -> {
                    System.out.println("request ==> " + r.remoteAddress());
                    r.response().end("<!DOCTYPE html>" +
                            "<html>" +
                            "<body><h1>Hello from TechStomach!</h1>" +
                            "<p>Our first online API :)</p></body>" +
                            "</html>");
                })
                .listen(8080, result -> {
                    if (result.succeeded()) {
                        fut.complete();
                    } else {
                        fut.fail(result.cause());
                    }
                });
    }
}
