package com.github.vietk.chacon.impl;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

import static com.pi4j.wiringpi.Gpio.delayMicroseconds;

/**
 * Created on 03/02/2017.
 */
public class DataProtocol {

    private DataProtocol() {
    }

    /**
     * | [0 - 25]  | [26]  |  [27] | [28 - 32] |
     * | emitterId | order | power | deviceId  |
     * @param emitterId
     * @param deviceId
     * @param powerOn
     * @return
     */
    public static String powerCommand(int emitterId, int deviceId, boolean powerOn) {
        String command = leftPadWith0(emitterId, 26);
        command += '0';
        command += powerOn?'1':'0';
        command += leftPadWith0(deviceId, 4);
        if (command.length() > 32) {
            throw new IllegalArgumentException("The command length is more than 32 bits, checks the arguments");
        }
        return command;
    }

    private static String leftPadWith0(int id, int n) {
        return String.format("%1$" + n + 's', Integer.toBinaryString(id)).replace(' ', '0');
    }
}
