package frc.Animations;

import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import frc.LLColor;
import frc.LedStrip;


/**Intended to only be used in an LED scheduler for use in their pattern scripting, otherwise set the color directly. */
public class SolidColorPattern extends Animation {
    private LLColor color;
    public SolidColorPattern(LedStrip ledStrip, LLColor color){
        super(ledStrip);
        this.color = color;
    }
    @Override
    public AddressableLEDBuffer generatePattern() {
        generatedBuffer = fillBufferWithOneColor(color);
        return generatedBuffer;
    }
    @Override
    public AddressableLEDBuffer initializePattern() {
        generatedBuffer = fillBufferWithOneColor(color);
        return generatedBuffer;
    }
    
}
