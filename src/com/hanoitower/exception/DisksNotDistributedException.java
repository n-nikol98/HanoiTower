package com.hanoitower.exception;

import com.hanoitower.HanoiTower;

public final class DisksNotDistributedException extends HanoiTowerException {

    public DisksNotDistributedException(final HanoiTower hanoiTower) {
        super(hanoiTower,
                "The disks of this Hanoi Tower have not been distributed yet!");
    }
}
