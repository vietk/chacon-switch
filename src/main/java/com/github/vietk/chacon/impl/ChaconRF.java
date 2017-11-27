package com.github.vietk.chacon.impl;

import java.util.concurrent.CompletableFuture;

/**
 * Created on 03/02/2017.
 */
public interface ChaconRF {

    public CompletableFuture<Void> transmit(String command);
}