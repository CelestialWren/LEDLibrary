package frc.Animations;

import java.util.HashMap;

import edu.wpi.first.wpilibj.util.Color;
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
    public HashMap<Integer, Color> generatePattern() {
        generatedHashmap.clear();
        generatedHashmap.put(LEDStripLength, color);
        return generatedHashmap;
    }
    
}
