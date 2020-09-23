package com.hanoitower.model.exception;

import com.hanoitower.model.Disk;

@SuppressWarnings("unused")
public class DiscException extends Exception {

    private final Disk disk;

    public DiscException(final Disk disk) {
        super("This Disk has experienced an exception!");
        this.disk = disk;
    }

    DiscException(final Disk disk, final String message) {
        super(message);
        this.disk = disk;
    }

    public Disk getDisk() { return disk; }
}
