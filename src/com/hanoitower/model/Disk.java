package com.hanoitower.model;

import com.hanoitower.model.exception.InvalidDiskRadiusException;

import java.util.UUID;

public final class Disk {

    private final UUID id;
    private final double radius;

    public Disk(final double radius) throws InvalidDiskRadiusException {
        this.id = UUID.randomUUID();

        if (radius <= 0)
            throw new InvalidDiskRadiusException(this, radius);

        this.radius = radius;
    }

    public UUID getId() { return id; }

    public double getRadius() { return radius; }
}
