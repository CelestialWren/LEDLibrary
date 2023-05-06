package frc.PrideFlags.FlagAnimations;

import frc.LedStrip;
import frc.Animations.MultiColorPattern;
import frc.PrideFlags.Flag;

public class DisplayFlagAnimation extends MultiColorPattern{
    public DisplayFlagAnimation(LedStrip strip, Flag flag){
        super(strip, flag.generateProportions(strip));
    } 
}
