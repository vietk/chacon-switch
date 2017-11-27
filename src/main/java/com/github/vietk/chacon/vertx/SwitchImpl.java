package com.github.vietk.chacon.vertx;

import com.github.vietk.chacon.Switch;
import com.github.vietk.chacon.impl.ChaconRFProtocol;
import com.github.vietk.chacon.impl.DataProtocol;
import io.vertx.core.Vertx;
import me.escoffier.vertx.completablefuture.VertxCompletableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * Created on 05/02/2017.
 */
public class SwitchImpl implements Switch {

    private final ChaconRFProtocol protocol;
    private int deviceId;
    private int emitterId;
    private Vertx vertx;

    public SwitchImpl(ChaconRFProtocol protocol, int deviceId, int emitterId, Vertx vertx) {
        this.protocol = protocol;
        this.deviceId = deviceId;
        this.emitterId = emitterId;
        this.vertx = vertx;
    }

    @Override
    public CompletableFuture<Void> on() {
        CompletableFuture<Void> future = VertxCompletableFuture.runAsync(() -> {
            protocol.transmit(DataProtocol.powerCommand(emitterId, deviceId, true));
        });
        return future;
    }

    @Override
    public CompletableFuture<Void> off() {
        CompletableFuture<Void> future = VertxCompletableFuture.runAsync(() -> {
            protocol.transmit(DataProtocol.powerCommand(emitterId, deviceId, false));
        });
        return future;
    }

    @Override
    public CompletableFuture<Void> dim(double value) {

        return null;
    }
}
