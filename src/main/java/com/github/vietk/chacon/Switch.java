package com.github.vietk.chacon;

import java.util.concurrent.CompletableFuture;

/**
 * Created on 03/02/2017.
 */
public interface Switch {

    /**
     * Turn this switch on
     */
    public CompletableFuture<Void> on();

    /**
     * Turn this switch off
     */
    public CompletableFuture<Void> off();

    /**
     * Turn this switch on with dim value
     * or dim to the value
     * @param value
     */
    public CompletableFuture<Void> dim(double value);
}
