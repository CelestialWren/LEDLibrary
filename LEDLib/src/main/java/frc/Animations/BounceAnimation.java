// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.Animations;

import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;
import frc.LedStrip;

/** Add your docs here. */
public class BounceAnimation extends Animation {
    Color backgroundColor;
    Color bouncingColor;
    int bouncePositionUpper;
    int bouncePositionLower = 0;
    int bounceLength;
    private int currentStripPosition = 0;
    boolean isGoingUp = true;

    public BounceAnimation(LedStrip ledStrip, Color backgroundColor, Color bouncingColor, int bounceLength) {
        super(ledStrip);
        this.backgroundColor = backgroundColor;
        this.bouncingColor = bouncingColor;
        this.bounceLength = bounceLength;
        bouncePositionUpper = bounceLength;
        if (bounceLength > LEDStripLength)
            throw new IllegalArgumentException("The length of the pattern to display is longer than the LED Strip.");
    }

    @Override
    public void reset() {
        super.reset();
        bouncePositionUpper = bounceLength;
        bouncePositionLower = 0;
        isGoingUp = true;
    }

    @Override
    public AddressableLEDBuffer generatePattern() {
        currentStripPosition = 0;
        if (bouncePositionUpper == LEDStripLength)
            isGoingUp = false;
        else if (bouncePositionLower == 0)
            isGoingUp = true;
        if (isGoingUp) {
            bouncePositionLower++;
            bouncePositionUpper++;
        } else {
            bouncePositionLower--;
            bouncePositionUpper--;
        }
        for (; currentStripPosition < bouncePositionLower; currentStripPosition++)
            generatedBuffer.setLED(currentStripPosition, backgroundColor);
        for (; currentStripPosition < bouncePositionUpper; currentStripPosition++)
            generatedBuffer.setLED(currentStripPosition, bouncingColor);
        for (; currentStripPosition < LEDStripLength; currentStripPosition++)
            generatedBuffer.setLED(currentStripPosition, backgroundColor);
        return generatedBuffer;
    }

    @Override
    public AddressableLEDBuffer initializePattern() {
        currentStripPosition = 0;
        for (; currentStripPosition < bouncePositionUpper; currentStripPosition++)
            generatedBuffer.setLED(currentStripPosition, bouncingColor);

        for (; currentStripPosition < LEDStripLength; currentStripPosition++)
            generatedBuffer.setLED(currentStripPosition, backgroundColor);

        return generatedBuffer;
    }
}
