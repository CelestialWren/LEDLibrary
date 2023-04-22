package frc.PrideFlags.FlagAnimations;

import frc.LedStrip;
import frc.Animations.Animation;
import frc.PrideFlags.Flag;

public class RaceFlagAnimation extends Animation {
    private Flag flag;

    public RaceFlagAnimation(LedStrip ledStrip, Flag flag) {
        super(ledStrip);
        this.flag = flag;
    }

    @Override
    public void generatePattern() {
        buffer.addFirst(buffer.getLast());
    }

    @Override
    protected void initializeCircleBuffer() {
        buffer = flag.generateProportions(getLedStrip());

    }
}
