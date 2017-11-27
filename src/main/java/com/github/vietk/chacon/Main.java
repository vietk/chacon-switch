package com.github.vietk.chacon;

/**
 * Created on 07/02/2017.
 */
public class Main {

    public static void main(String[] args) {
        String gpio = args[0];
        Integer deviceId = Integer.parseInt(args[1]);
        Integer emitterId = Integer.parseInt(args[2]);
        String state = args[3];
        SendingHelper.send(gpio, deviceId, emitterId, state);
    }
}
