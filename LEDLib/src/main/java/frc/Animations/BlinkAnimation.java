package frc.Animations;

import frc.LLColor;
import frc.LedStrip;

public class BlinkAnimation extends Animation {
    private LLColor firstColor;
    private LLColor secondColor;
    private LLColor currentColor;

    public BlinkAnimation(LedStrip led, LLColor firstColor, LLColor secondColor) {
        super(led);
        this.firstColor = firstColor;
        this.secondColor = secondColor;
        currentColor = firstColor;
        initializeCircleBuffer();
    }

    public BlinkAnimation(LedStrip led, double minUpdatePeriod, LLColor firstColor, LLColor secondColor) {
        super(led, minUpdatePeriod);
        this.firstColor = firstColor;
        this.secondColor = secondColor;
        currentColor = firstColor;
        initializeCircleBuffer();
    }

    @Override
    public void generatePattern() {
        if (buffer.getFirst() == firstColor.getAsHex()) {
            fillBufferWithOneColor(firstColor);
        } else {
            fillBufferWithOneColor(secondColor);
        }
    }

    @Override
    public void reset() {
        super.reset();
    }
 
    @Override
    protected void initializeCircleBuffer() {
        fillBufferWithOneColor(currentColor);    
    }

}
