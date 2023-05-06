package frc.Animations;

import java.util.ArrayList;
import java.util.HashMap;

import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import frc.LLColor;
import frc.LedStrip;
import java.lang.IllegalArgumentException;

public class MultiColorPattern extends Animation {
    private HashMap<Integer, LLColor> colorPatterns;
    private ArrayList<Integer> positions;
    private int currentStripPosition = 0;
    /**Intended only for use in a scheduler, which requires the usage of automations for consisentcy */
    public MultiColorPattern(LedStrip ledStrip, HashMap<Integer, LLColor> colorPatterns){
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
    public AddressableLEDBuffer generatePattern() {
        currentStripPosition = 0;
        for(int section : positions){
            for(; currentStripPosition<section; currentStripPosition++){
                generatedBuffer.setLED(currentStripPosition, colorPatterns.get(section));
            }
        }
        return generatedBuffer;
    }
    @Override
    public AddressableLEDBuffer initializePattern() {
        return generatePattern();
    }
    
}
