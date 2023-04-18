package frc.Animations;

import java.util.ArrayList;
import java.util.HashMap;

import edu.wpi.first.wpilibj.util.Color;
import frc.LedStrip;
import java.lang.IllegalArgumentException;

public class MultiColorPattern extends Animation {
    private HashMap<Integer, Color> colorPatterns;
    private ArrayList<Integer> positions;
    /**Intended only for use in a scheduler, which requires the usage of automations for consisentcy */
    public MultiColorPattern(LedStrip ledStrip, HashMap<Integer, Color> colorPatterns){
        super(ledStrip);
        positions = ledStrip.sortStripValues(colorPatterns);
        for(int value : positions){
            if(value > LEDStripLength){
                throw new IllegalArgumentException("The length of the pattern to display is longer than the LED Strip");
            }
        }

        this.colorPatterns = colorPatterns;
    }
    @Override
    public HashMap<Integer, Color> generatePattern() {
        return colorPatterns;
    }
    
}
