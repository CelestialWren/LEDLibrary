// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.Animations;

import frc.LLColor;
import frc.LedStrip;
import frc.Utils.PredefinedColors;

/** Add your docs here. */
public class RainbowRaceAnimation extends Animation {
    private LLColor startingColor;
    private LLColor color;
    private int increment = 3;

    public RainbowRaceAnimation(LedStrip ledStrip) {
        super(ledStrip);
        startingColor = PredefinedColors.kBlue;
        initializeCircleBuffer();
    }

    public RainbowRaceAnimation(LedStrip ledStrip, double minUpdatePeriod) {
        super(ledStrip, minUpdatePeriod);
        startingColor = PredefinedColors.kBlue;
        initializeCircleBuffer();
    }

    public RainbowRaceAnimation(LedStrip ledStrip, int increment) {
        super(ledStrip);
        startingColor = PredefinedColors.kBlue;
        this.increment = increment;
        initializeCircleBuffer();
    }

    public RainbowRaceAnimation(LedStrip ledStrip, LLColor color) {
        super(ledStrip);
        startingColor = this.color;
        initializeCircleBuffer();
    }

    public RainbowRaceAnimation(LedStrip ledStrip, LLColor color, int increment) {
        super(ledStrip);
        startingColor = this.color;
        this.increment = increment;
        initializeCircleBuffer();
    }

    @Override
    public void reset() {
        super.reset();
    }
    @Override
    public void generatePattern() {
        color = LLColor.fromHSV(LLColor.replaceHSVElement(color.toHSV(), 'H', color.getHue() - increment));
        buffer.addFirst(LEDStripLength);
    }

    @Override
    protected void initializeCircleBuffer() {
        color = startingColor;
        for(int i = 1; i <= LEDStripLength; i++){
            color = LLColor.fromHSV(LLColor.replaceHSVElement(color.toHSV(), 'H', color.getHue() - increment));
            buffer.addFirst(color.getAsHex());
        }
    }

}
