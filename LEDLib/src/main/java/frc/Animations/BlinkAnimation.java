package frc.Animations;

import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import frc.LLColor;
import frc.LedStrip;

public class BlinkAnimation extends Animation {
    private LLColor firstColor;
    private LLColor secondColor;
    private LLColor currentColor;
    
    public BlinkAnimation(LedStrip led, LLColor firstColor, LLColor secondColor){
        super(led, .3);
        this.firstColor = firstColor;
        this.secondColor = secondColor;
    }

    public BlinkAnimation(LedStrip led, double minUpdatePeriod, LLColor firstColor, LLColor secondColor){
        super(led, minUpdatePeriod);
        this.firstColor = firstColor;
        this.secondColor = secondColor;
    }

    @Override
    public AddressableLEDBuffer generatePattern() {
        if(currentColor == firstColor)
            currentColor = secondColor;
        else
            currentColor = firstColor;

        generatedBuffer = fillBufferWithOneColor(currentColor);
        return generatedBuffer;
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    public AddressableLEDBuffer initializePattern() {
        currentColor = firstColor;
        return fillBufferWithOneColor(currentColor);
    }
    
}
