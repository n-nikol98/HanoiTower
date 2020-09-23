package com.hanoitower.exception;

import com.hanoitower.HanoiTower;

public final class DisksDistributedException extends HanoiTowerException {

    public DisksDistributedException(final HanoiTower hanoiTower) {
        super(hanoiTower,
                "The disks of this Hanoi Tower have already been distributed!");
    }
}
