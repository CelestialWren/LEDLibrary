// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.Animations;


import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import frc.LLColor;
import frc.LedStrip;

/** Add your docs here. */
public class RaceAnimation extends Animation {
    private final LLColor backgroundColor, raceColor;
    private final int raceLength, raceGap;
    private int currentStripPosition = 0;
    private LLColor endOfStripColor;
    private AddressableLEDBuffer bufferCopy;

    public RaceAnimation(LedStrip ledStrip, LLColor backgroundColor, LLColor raceColor, int raceLength, int raceGap) {
        super(ledStrip, 0.075);
        bufferCopy = new AddressableLEDBuffer(LEDStripLength);
        this.backgroundColor = backgroundColor;
        this.raceColor = raceColor;
        this.raceLength = raceLength;
        this.raceGap = raceGap;
        if ((raceLength + raceGap)> LEDStripLength)
            throw new IllegalArgumentException("The length of the pattern to display is longer than the LED Strip.");
    }

    public RaceAnimation(LedStrip ledStrip, double minUpdatePeriod, LLColor backgroundColor, LLColor raceColor, int raceLength, int raceGap) {
        super(ledStrip, minUpdatePeriod);
        bufferCopy = new AddressableLEDBuffer(LEDStripLength);
        this.backgroundColor = backgroundColor;
        this.raceColor = raceColor;
        this.raceLength = raceLength;
        this.raceGap = raceGap;
        if ((raceLength + raceGap)> LEDStripLength)
            throw new IllegalArgumentException("The length of the pattern to display is longer than the LED Strip.");
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
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
        int cycleStripPosition;
        while(currentStripPosition < LEDStripLength){
            for(cycleStripPosition = currentStripPosition; currentStripPosition<(cycleStripPosition+raceLength); currentStripPosition++){
                generatedBuffer.setLED(currentStripPosition, raceColor);
            }

            for(cycleStripPosition = currentStripPosition; currentStripPosition<(cycleStripPosition+raceGap); currentStripPosition++){
                generatedBuffer.setLED(currentStripPosition, backgroundColor);
            }
        }
        return generatedBuffer;
    }

}
