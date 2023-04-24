package frc.PrideFlags.FlagAnimations;

import java.util.HashMap;

import edu.wpi.first.wpilibj.util.Color;
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
    public HashMap<Integer, Color> generatePattern() {
        return flag.generateProportions(getLedStrip());
    }
    
}
