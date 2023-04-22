// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.Animations;

import frc.LLColor;
import frc.LedStrip;

/** Add your docs here. */
public class BounceAnimation extends Animation {
    LLColor backgroundColor;
    LLColor bouncingColor;
    int bounceLength;
    boolean isGoingUp = true;

    public BounceAnimation(LedStrip ledStrip, LLColor backgroundColor, LLColor bouncingColor, int bounceLength) {
        super(ledStrip);
        this.backgroundColor = backgroundColor;
        this.bouncingColor = bouncingColor;
        this.bounceLength = bounceLength;
        if(bounceLength > LEDStripLength)
            throw new IllegalArgumentException("The length of the pattern to display is longer than the LED Strip.");
        else
            initializeCircleBuffer();
    }

    @Override
    public void reset() {
        super.reset();
        isGoingUp = true;
    }

    @Override
    public void generatePattern() {
        if (buffer.getLast() == bouncingColor.getAsHex())
            isGoingUp = false;
        else if (buffer.getFirst() == bouncingColor.getAsHex())
            isGoingUp = true;
        if (isGoingUp) {
            buffer.addFirst(backgroundColor.getAsHex());
        } else {
            buffer.addLast(backgroundColor.getAsHex());
        }
    }

    @Override
    protected void initializeCircleBuffer() {
       fillBufferWithOneColor(backgroundColor);
       for(int c = 0; c<bounceLength; c++)
        buffer.addFirst(bouncingColor.getAsHex());
        
    }
}
