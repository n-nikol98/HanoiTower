package com.hanoitower.model.exception;

import com.hanoitower.model.Disk;
import com.hanoitower.model.Rod;

@SuppressWarnings("unused")
public final class IncompatibleDiskException extends RodException {

    private final Disk disk;

    public IncompatibleDiskException(final Rod rod, final Disk disk) {
        super(rod,
                "The Disk: \"" + disk.getId() +
                        "\" has a radius of: " + disk.getRadius() +
                        ", whereas the disk, which is on top of this rod, has a radius of: " +
                        rod.getDisksStack().get(rod.getDisksCount() - 1) + "!");

        this.disk = disk;
    }

    public Disk getDisk() {
        return disk;
    }
}
