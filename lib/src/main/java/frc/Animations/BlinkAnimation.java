package frc.Animations;

import java.util.HashMap;

import edu.wpi.first.wpilibj.util.Color;
import frc.LLColor;
import frc.LedStrip;

public class BlinkAnimation extends Animation {
    private LLColor firstColor;
    private LLColor secondColor;
    private LLColor currentColor;
    
    public BlinkAnimation(LedStrip led, LLColor firstColor, LLColor secondColor){
        super(led);
        this.firstColor = firstColor;
        this.secondColor = secondColor;
    }

    public BlinkAnimation(LedStrip led, double minUpdatePeriod, LLColor firstColor, LLColor secondColor){
        super(led, minUpdatePeriod);
        this.firstColor = firstColor;
        this.secondColor = secondColor;
    }

    @Override
    public HashMap<Integer, Color> generatePattern() {
        if(currentColor == firstColor)
            currentColor = secondColor;
        else
            currentColor = firstColor;

        generatedHashmap.clear();
        generatedHashmap.put(LEDStripLength, currentColor);
        return generatedHashmap;
    }

    @Override
    public void reset() {
        super.reset();
        generatedHashmap.put(LEDStripLength, firstColor);
    }
    
}
