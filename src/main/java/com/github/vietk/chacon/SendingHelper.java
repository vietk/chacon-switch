package com.github.vietk.chacon;

import com.github.vietk.chacon.forkjoinpool.SwitchImpl;
import com.github.vietk.chacon.impl.ChaconRFProtocol;
import com.pi4j.io.gpio.*;

import java.util.concurrent.CompletableFuture;

public class SendingHelper {

    private SendingHelper() {

    }

    public static void send(String gpio, int deviceId, int emitterId, String state) {

        GpioPinDigitalOutput pin = GpioFactory.getInstance().provisionDigitalOutputPin(
                RaspiPin.getPinByName(gpio), "RadioTransmission", PinState.LOW);
        ChaconRFProtocol protocol = new ChaconRFProtocol(pin);
        Switch zwitch = new SwitchImpl(protocol, deviceId, emitterId);

        CompletableFuture<Void> future;
        if (state.equals("on")) {
            future = zwitch.on();
        } else {
            future = zwitch.off();
        }
        future.join();
        pin.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
    }
}
