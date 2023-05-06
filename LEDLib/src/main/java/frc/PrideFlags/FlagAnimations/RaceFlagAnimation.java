package frc.PrideFlags.FlagAnimations;

import java.util.ArrayList;
import java.util.HashMap;

import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import frc.LLColor;
import frc.LedStrip;
import frc.Animations.Animation;
import frc.PrideFlags.Flag;

public class RaceFlagAnimation extends Animation{
    private HashMap<Integer, LLColor> scaledSections = new HashMap<>();
    private ArrayList<Integer> sortedSections = new ArrayList<>();
    private AddressableLEDBuffer bufferCopy;
    private LLColor endOfStripColor;
    public RaceFlagAnimation(LedStrip ledStrip, Flag flag){
        super(ledStrip);
        bufferCopy = new AddressableLEDBuffer(LEDStripLength);
        scaledSections.putAll(flag.generateProportions(ledStrip));
        sortedSections.addAll(ledStrip.sortStripValues(scaledSections));
    }

    public RaceFlagAnimation(LedStrip ledStrip, Flag flag, double minUpdatePeriod){
        super(ledStrip, minUpdatePeriod);
        bufferCopy = new AddressableLEDBuffer(LEDStripLength);
        scaledSections.putAll(flag.generateProportions(ledStrip));
        sortedSections.addAll(ledStrip.sortStripValues(scaledSections));
    }

    @Override
    /**S */
    public AddressableLEDBuffer generatePattern() {
        for (int c = 0; c<LEDStripLength; c++) {
            bufferCopy.setLED(c, generatedBuffer.getLED(c));
        }
            endOfStripColor = LLColor.fromWPILibColor(generatedBuffer.getLED(LEDStripLength-1));
            for (int c = 1; c<LEDStripLength; c++) {
                generatedBuffer.setLED(c, bufferCopy.getLED((c-1)));
        }
        generatedBuffer.setLED(0, endOfStripColor);

        return generatedBuffer;
    }

    @Override
    public AddressableLEDBuffer initializePattern() {
        int currentStripPosition = 0;
        for(int section : sortedSections){
            for(; currentStripPosition<section; currentStripPosition++){
                generatedBuffer.setLED(currentStripPosition, scaledSections.get(section));
            }
        }
        return generatedBuffer;
    }
}
