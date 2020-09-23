package com.hanoitower.model.exception;

import com.hanoitower.model.Disk;
import com.hanoitower.model.Rod;

import java.util.Stack;

@SuppressWarnings("unused")
public final class MalformedDisksStackException extends RodException {

    private final Stack<Disk> disksStack;

    public MalformedDisksStackException(final Rod rod, final Stack<Disk> disksStack) {
        super(rod,
                "The Disk of this stack are not ordered correctly! " +
                "Please note that the largest disk should be at the bottom, " +
                "whereas the smalled disk should be at the top.");

        this.disksStack = disksStack;
    }

    public Stack<Disk> getDisksStack() { return disksStack; }
}
