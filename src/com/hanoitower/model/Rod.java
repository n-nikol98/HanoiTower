package com.hanoitower.model;

import com.hanoitower.model.exception.EmptyDisksStackException;
import com.hanoitower.model.exception.IncompatibleDiskException;
import com.hanoitower.model.exception.MalformedDisksStackException;

import java.util.Stack;
import java.util.UUID;

@SuppressWarnings("Convert2Diamond")
public final class Rod {

    private final UUID id;
    private final Stack<Disk> disksStack;

    public Rod() {
        this.id = UUID.randomUUID();
        this.disksStack = new Stack<Disk>();
    }

    public Rod(final Stack<Disk> disksStack) throws MalformedDisksStackException {
        this();

        for (Disk disk : disksStack)
            try {
                addDiskToTop(disk);
            } catch (IncompatibleDiskException ex) {
                throw new MalformedDisksStackException(this, disksStack);
            }
    }

    private Rod(final UUID id, final Stack<Disk> disksStack) {
        this.id = id;
        this.disksStack = new Stack<Disk>() {{
            this.addAll(disksStack);
        }};
    }

    public UUID getId() { return id; }

    public Stack<Disk> getDisksStack() { return new Stack<Disk>() {{
        this.addAll(disksStack);
    }}; }

    public boolean isEmpty() { return disksStack.empty(); }

    public int getDisksCount() { return disksStack.size(); }

    public void addDiskToTop(Disk disk) throws IncompatibleDiskException {
        //noinspection SimplifiableConditionalExpression
        if (!disksStack.empty() ?
                (disksStack.lastElement().getRadius() < disk.getRadius()) :
                false)
             throw new IncompatibleDiskException(this, disk);
        else
            disksStack.add(disk);
    }

    public Disk removeDiskFromTop() throws EmptyDisksStackException {
        if (disksStack.empty())
            throw new EmptyDisksStackException(this);
        else
            return disksStack.pop();
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Rod clone() {
        return new Rod(id, disksStack);
    }
}
