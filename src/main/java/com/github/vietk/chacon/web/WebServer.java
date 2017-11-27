package com.github.vietk.chacon.web;

import com.github.vietk.chacon.Switch;
import com.github.vietk.chacon.forkjoinpool.SwitchImpl;
import com.github.vietk.chacon.impl.ChaconRFProtocol;
import com.pi4j.io.gpio.*;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class WebServer extends AbstractVerticle {

    private Pin gpio = RaspiPin.GPIO_00;
    private int emitterId = 12325261;

    @Override
    public void start(Future<Void> fut) {

        // initialize the GPIO
        GpioPinDigitalOutput pin = GpioFactory.getInstance().provisionDigitalOutputPin(
                gpio, "RadioTransmission", PinState.LOW);

        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        Route route = router.route(HttpMethod.POST, "/switch/:deviceId/:state");
        route.blockingHandler(routingContext -> {
            Integer deviceId = Integer.parseInt(routingContext.request().getParam("deviceId"));
            String state = routingContext.request().getParam("state");

            Switch zwitch = new SwitchImpl(new ChaconRFProtocol(pin), deviceId, emitterId);
            if (state.equals("on")) {
                zwitch.on();
            }
            else {
                zwitch.off();
            }
        });
        server.requestHandler(router::accept).listen(80);
    }
}
