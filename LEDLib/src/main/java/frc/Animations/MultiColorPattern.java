package frc.Animations;

import frc.LedStrip;
import frc.Pattern;

import java.lang.IllegalArgumentException;

public class MultiColorPattern extends Animation {
    private Pattern[] patterns;

    /**
     * Intended only for use in a scheduler, which requires the usage of automations
     * for consisentcy
     */
    public MultiColorPattern(LedStrip ledStrip, Pattern... patterns) {
        super(ledStrip);

        int totalLength = 0;
        for(Pattern pattern : patterns){
            totalLength += pattern.getEndPoint();
        }

        if(totalLength > ledStrip.getStripLength())
            throw new IllegalArgumentException("The pattern's total length is longer than the led Strip");
        else
            this.patterns = patterns;   
    }

    @Override
    public void generatePattern() {
        initializeCircleBuffer();
    }

    @Override
    protected void initializeCircleBuffer() {
        for (Pattern pattern : patterns) {
            for (int c = 0; c < pattern.getEndPoint(); c++)
                buffer.addFirst(pattern.getColor().getAsHex());

        }

    }
}
