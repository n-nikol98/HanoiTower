package com.hanoitower.model.exception;

import com.hanoitower.model.Rod;

public final class EmptyDisksStackException extends RodException {

    public EmptyDisksStackException(final Rod rod) {
        super(rod,
                "The Disks Stack of this Rod is empty!");
    }
}
