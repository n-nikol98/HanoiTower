package com.hanoitower;

import com.hanoitower.exception.DisksDistributedException;
import com.hanoitower.exception.DisksNotDistributedException;
import com.hanoitower.exception.NegativeDisksCountException;
import com.hanoitower.model.Disk;
import com.hanoitower.model.Rod;
import com.hanoitower.model.exception.EmptyDisksStackException;
import com.hanoitower.model.exception.IncompatibleDiskException;
import com.hanoitower.model.exception.InvalidDiskRadiusException;
import com.hanoitower.model.exception.MalformedDisksStackException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@SuppressWarnings("Convert2Diamond")
public final class HanoiTower {

    /**
    *
    * Nested model for a distribution step, which is used and return only by the Hanoi Tower class.
    *
    */
    @SuppressWarnings("unused")
    public static final class DiskDistributionStep {

        private final Disk disk;
        private final Rod originRod;
        private final Rod destinationRod;

        private DiskDistributionStep(final Disk disk, final Rod originRod, final Rod destinationRod) {
            this.disk = disk;
            this.originRod = originRod;
            this.destinationRod = destinationRod;
        }

        public Disk getDisk() { return disk; }

        public Rod getOriginRod() { return originRod.clone(); }

        public Rod getDestinationRod() { return destinationRod.clone(); }

        @Override
        public String toString() {
            return "Moved Disk: \"" + disk.getId() + "\"" +
                    " from Rod: \"" + originRod.getId() + "\"" +
                    " to Rod: \"" + destinationRod.getId() + "\".";
        }
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final List<Rod> rods;
    private final List<DiskDistributionStep> diskDistributionSteps;

    public HanoiTower(final long disksCount) throws NegativeDisksCountException {
        if (disksCount < 0)
            throw new NegativeDisksCountException(this, disksCount);

        rods = new ArrayList<Rod>(3) {{
            IntStream.range(0, 3).forEach(
                    rodIndex -> {
                        if(rodIndex == 0) {
                            try {
                                add(new Rod(new Stack<Disk>() {{
                                    LongStream.range(1, disksCount + 1).forEach(radius -> {
                                        try {
                                            add(new Disk((radius - disksCount -1) * (-1) ));
                                        } catch (InvalidDiskRadiusException ignored) {}
                                    });
                                }}));
                            } catch (MalformedDisksStackException ignored) {}
                        }
                        else
                            add(new Rod());
                    }
            );
        }};

        this.diskDistributionSteps = new ArrayList<DiskDistributionStep>();
    }

    public List<DiskDistributionStep> getDiskDistributionSteps() throws DisksNotDistributedException {
        if (!initialRod().isEmpty())
            throw new DisksNotDistributedException(this);
        else
            return diskDistributionSteps;
    }

    public void distributeDisks() throws DisksDistributedException {
        if (initialRod().isEmpty())
            throw new DisksDistributedException(this);
        else
            distributeDisksInternal(initialRod().getDisksCount(),
                    initialRod(), auxiliaryRod(), destinationRod());
    }

    private void distributeDisksInternal(final long disksCount, final Rod originRod,
                                         final Rod auxiliaryRod, final Rod destinationRod) {
        Runnable distributionStep = () -> diskDistributionSteps.add(
                formatStep(transferDiskBetweenRods(originRod, destinationRod),
                        originRod, destinationRod));

        if (disksCount == 1) {
            distributionStep.run();

            return;
        }

        distributeDisksInternal(disksCount - 1, originRod, destinationRod, auxiliaryRod);

        distributionStep.run();

        distributeDisksInternal(disksCount - 1, auxiliaryRod, originRod, destinationRod);
    }

    private static Disk transferDiskBetweenRods(final Rod originRod, final Rod destinationRod) {
        Disk disk;

        try {
            destinationRod.addDiskToTop(disk = originRod.removeDiskFromTop());

            return disk;
        } catch (IncompatibleDiskException | EmptyDisksStackException ignored) { return null; }
    }

    private static DiskDistributionStep formatStep(final Disk disk, final Rod originRod, final Rod destinationRod) {
        return new DiskDistributionStep(disk, originRod, destinationRod);
    }

    private Rod initialRod() {
        return rods.get(0);
    }

    private Rod auxiliaryRod() {
        return rods.get(1);
    }

    private Rod destinationRod() {
        return rods.get(2);
    }
}
