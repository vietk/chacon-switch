package com.github.vietk.chacon.impl;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.wiringpi.Gpio;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created on 05/02/2017.
 */
public class ChaconRFProtocol {

    private Logger logger = Logger.getLogger(ChaconRFProtocol.class.getName());

    private final GpioPinDigitalOutput pin;

    public ChaconRFProtocol(GpioPinDigitalOutput pin) {
        this.pin = pin;
    }

    public void sendBit(boolean trueFalse) {
        assert(pin.isLow());
        long pulse = trueFalse?1340:310;
        pin.high();
        delayMicroseconds(310);
        pin.low();
        delayMicroseconds(pulse);
    }

    public void sendPair(boolean bool) {
        sendBit(bool);
        sendBit(!bool);
    }

    public void sendDimmerbit() {

    }

    public void transmit(String command) {

        logger.fine("Transmitting " + command + " on " + pin.getName());

        transmitOnce(command);
        delayMicroseconds(10);
        transmitOnce(command);
        delayMicroseconds(10);
        transmitOnce(command);
        delayMicroseconds(10);
        transmitOnce(command);
        delayMicroseconds(10);
        transmitOnce(command);
        delayMicroseconds(10);

        logger.fine("End transmission");
    }

    public void transmitOnce(String command) {
        startTransmission();
        sendCommand(command);
        endTransmission();
    }

    private void sendCommand(String command) {
        command.chars().forEach(c -> sendPair(withBoolean(c)));
    }

    protected void delayMicroseconds(long micros) {
        Gpio.delayMicroseconds(micros);
    }

    private void startTransmission() {
        pin.high();
        delayMicroseconds(275);
        pin.low();
        delayMicroseconds(9900);
        pin.high();
        delayMicroseconds(275);
        pin.low();
        delayMicroseconds(2675);
        pin.high();
    }

    private void endTransmission() {
        pin.high();
        delayMicroseconds(275);
        pin.low();
    }

    private boolean withBoolean(int c) {
        return ((char) c) == '1'?true:false;
    }
}
