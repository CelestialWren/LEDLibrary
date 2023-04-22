// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.Animations;


import edu.wpi.first.util.CircularBuffer;
import frc.LLColor;
import frc.LedStrip;

/** Add your docs here. */
public class RaceAnimation extends Animation {
    private final LLColor backgroundColor, raceColor;
    private final int raceLength, raceGap;
    private int cyclesTillEval = 0;
    private LLColor currentColor;

    public RaceAnimation(LedStrip ledStrip, LLColor backgroundColor, LLColor raceColor, int raceLength, int raceGap) {
        super(ledStrip);
        this.backgroundColor = backgroundColor;
        this.raceColor = raceColor;
        this.raceLength = raceLength;
        this.raceGap = raceGap;
        buffer = new CircularBuffer(LEDStripLength);
        if ((raceLength > LEDStripLength) || (raceGap > LEDStripLength))
            throw new IllegalArgumentException("The length of the pattern to display is longer than the LED Strip.");
        else {
            initializeCircleBuffer();
        }
    }

    @Override
    public void reset() {
        super.reset();
        initializeCircleBuffer();
    }

    @Override
    public void generatePattern() {
        if(cyclesTillEval <= 0){
        if (buffer.get(raceLength) != raceColor.getAsHex()){
            currentColor = raceColor;
            cyclesTillEval = raceLength;}
        else{
            currentColor = backgroundColor;
            cyclesTillEval = raceGap;
        }}

        else{
            buffer.addFirst(currentColor.getAsHex());
            cyclesTillEval--;
        }
    }

    @Override
    protected void initializeCircleBuffer() {
        for (int c = 0; c < LEDStripLength - raceLength; c++)
            buffer.addFirst(backgroundColor.getAsHex());
        for (int c = 0; c < raceLength; c++)
            buffer.addFirst(raceColor.getAsHex());
    }
}
