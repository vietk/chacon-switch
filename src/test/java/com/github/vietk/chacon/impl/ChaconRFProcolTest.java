package com.github.vietk.chacon.impl;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

/**
 * Created on 04/02/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ChaconRFProcolTest {

    @Mock
    GpioPinDigitalOutput pin;

    private ChaconRFProtocol chaconOperation;

    @Before
    public void setup() {
        when(pin.isLow()).thenReturn(true);
        chaconOperation = new ChaconRFProtocol(pin);
    }

    @Test
    public void sendTrue() throws Exception {
        chaconOperation = spy(chaconOperation);
        doNothing().when(chaconOperation).delayMicroseconds(Matchers.anyLong());

        chaconOperation.sendBit(true);

        Mockito.verify(pin).isLow();
        Mockito.verify(pin).high();
        Mockito.verify(chaconOperation).delayMicroseconds(Matchers.eq(310L));
        Mockito.verify(chaconOperation).delayMicroseconds(Matchers.eq(1340L));
        Mockito.verify(pin).low();
        Mockito.verifyNoMoreInteractions(pin);
    }

    @Test
    public void sendFalse() throws Exception {
        chaconOperation = spy(chaconOperation);
        doNothing().when(chaconOperation).delayMicroseconds(Matchers.anyLong());

        chaconOperation.sendBit(false);

        Mockito.verify(pin).isLow();
        Mockito.verify(pin).high();
        Mockito.verify(chaconOperation, times(2)).delayMicroseconds(Matchers.eq(310L));
        Mockito.verify(pin).low();
        Mockito.verifyNoMoreInteractions(pin);
    }

    @Test
    public void sendPair() throws Exception {
        ChaconRFProtocol spy = Mockito.spy(chaconOperation);
        doNothing().when(spy).sendBit(Matchers.anyBoolean());
        InOrder inOrder = inOrder(spy);
        spy.sendPair(true);
        inOrder.verify(spy).sendBit(Matchers.eq(true));
        inOrder.verify(spy).sendBit(Matchers.eq(false));
    }

    @Test
    public void sendDimmerbit() throws Exception {

    }

}