package com.hanoitower.exception;

import com.hanoitower.HanoiTower;

@SuppressWarnings("unused")
public final class NegativeDisksCountException extends HanoiTowerException {

    private final long discsCount;

    public NegativeDisksCountException(final HanoiTower hanoiTower, final long discsCount) {
        super(hanoiTower,
                discsCount + " is not a valid number of discs for a Hanoi Tower! "
                + "Please make sure that the value is not negative.");

        this.discsCount = discsCount;
    }

    public long getDiscsCount() {
        return discsCount;
    }
}
