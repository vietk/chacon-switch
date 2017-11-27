package com.github.vietk.chacon.impl;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created on 05/02/2017.
 */
public class DataProtocolTest {

    @Test
    public void withCommandPowerOn() throws Exception {

        int emitterId = 12345678;
        int deviceId = 1;
        String command = DataProtocol.powerCommand(emitterId, deviceId, true);
        Assert.assertEquals("00101111000110000101001110" + "0" + "1" + "0001", command);
    }

    @Test
    public void withCommandPowerOff() throws Exception {

        int emitterId = 12345678;
        int deviceId = 2;
        String command = DataProtocol.powerCommand(emitterId, deviceId, false);
        Assert.assertEquals("00101111000110000101001110" + "0" + "0" + "0010", command);
    }

}