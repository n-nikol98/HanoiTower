package com.hanoitower.exception;

import com.hanoitower.HanoiTower;

@SuppressWarnings("unused")
public class HanoiTowerException extends Exception {

    private final HanoiTower hanoiTower;

    public HanoiTowerException(final HanoiTower hanoiTower) {
        super("This Hanoi Tower has experienced an exception!");
        this.hanoiTower = hanoiTower;
    }

    HanoiTowerException(final HanoiTower hanoiTower, final String message) {
        super(message);
        this.hanoiTower = hanoiTower;
    }

    public final HanoiTower getHanoiTower() { return hanoiTower; }
}
