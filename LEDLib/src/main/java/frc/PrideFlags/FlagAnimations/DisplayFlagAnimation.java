package frc.PrideFlags.FlagAnimations;

import frc.LedStrip;
import frc.Animations.Animation;
import frc.PrideFlags.Flag;

public class DisplayFlagAnimation extends Animation{
    private Flag flag;
    public DisplayFlagAnimation(LedStrip strip, Flag flag){
        super(strip);
        this.flag = flag;
    }
    @Override
    public void generatePattern() {
        buffer = flag.generateProportions(getLedStrip());
    }
    @Override
    protected void initializeCircleBuffer() {
        buffer = flag.generateProportions(getLedStrip());
    }
    
}
