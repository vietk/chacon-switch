package com.github.vietk.chacon;

import com.github.vietk.chacon.forkjoinpool.SwitchImpl;
import com.github.vietk.chacon.impl.ChaconRFProtocol;
import com.pi4j.io.gpio.*;

public class SendingHelper {

    private SendingHelper() {

    }

    public static void send(String gpio, int deviceId, int emitterId, String state) {

        GpioPinDigitalOutput pin = GpioFactory.getInstance().provisionDigitalOutputPin(
                RaspiPin.getPinByName(gpio), "RadioTransmission", PinState.LOW);
        ChaconRFProtocol protocol = new ChaconRFProtocol(pin);
        Switch zwitch = new SwitchImpl(protocol, deviceId, emitterId);
        if (state.equals("on")) {
            zwitch.on();
        } else {
            zwitch.off();
        }
        pin.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
    }
}
