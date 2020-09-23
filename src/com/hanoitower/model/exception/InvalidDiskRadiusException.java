package com.hanoitower.model.exception;

import com.hanoitower.model.Disk;

@SuppressWarnings("unused")
public final class InvalidDiskRadiusException extends DiscException {

    private final double radius;

    public InvalidDiskRadiusException(final Disk disk, final double radius) {
        super(disk,
                radius + " is not a valid radius for a Disk! " +
                "Please make sure that the value is a positive number.");

        this.radius = radius;
    }

    public double getRadius() { return radius; }
}
