package com.github.vietk.chacon.forkjoinpool;

import com.github.vietk.chacon.Switch;
import com.github.vietk.chacon.impl.ChaconRFProtocol;
import com.github.vietk.chacon.impl.DataProtocol;

import java.util.concurrent.CompletableFuture;

/**
 * Created on 05/02/2017.
 */
public class SwitchImpl implements Switch {

    private final ChaconRFProtocol protocol;
    private int deviceId;
    private int emitterId;

    public SwitchImpl(ChaconRFProtocol protocol, int deviceId, int emitterId) {
        this.protocol = protocol;
        this.deviceId = deviceId;
        this.emitterId = emitterId;
    }

    @Override
    public CompletableFuture<Void> on() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            protocol.transmit(DataProtocol.powerCommand(emitterId, deviceId, true));
        });
        return future;
    }

    @Override
    public CompletableFuture<Void> off() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            protocol.transmit(DataProtocol.powerCommand(emitterId, deviceId, false));
        });
        return future;
    }

    @Override
    public CompletableFuture<Void> dim(double value) {

        return null;
    }
}
