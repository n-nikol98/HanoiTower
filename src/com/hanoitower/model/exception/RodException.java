package com.hanoitower.model.exception;

import com.hanoitower.model.Rod;

@SuppressWarnings("unused")
public class RodException extends Exception {

    private final Rod rod;

    public RodException(final Rod rod) {
        super("This Rod has experienced an exception!");
        this.rod = rod;
    }

    RodException(final Rod rod, final String message) {
        super(message);
        this.rod = rod;
    }

    public Rod getRod() { return rod; }
}
