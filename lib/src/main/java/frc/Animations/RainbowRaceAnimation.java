// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.Animations;

import java.util.HashMap;

import edu.wpi.first.wpilibj.util.Color;
import frc.LLColor;
import frc.LedStrip;

/** Add your docs here. */
public class RainbowRaceAnimation extends Animation {
    private LLColor startingColor;
    private LLColor color;
    private int increment = 1;

    public RainbowRaceAnimation(LedStrip ledStrip) {
        super(ledStrip);
        startingColor = color = (LLColor) Color.kBlue;
    }

    public RainbowRaceAnimation(LedStrip ledStrip, int increment) {
        super(ledStrip);
        startingColor = color = (LLColor) Color.kBlue;
        this.increment = increment;
    }

    public RainbowRaceAnimation(LedStrip ledStrip, LLColor color) {
        super(ledStrip);
        startingColor = this.color = color;
    }

    public RainbowRaceAnimation(LedStrip ledStrip, LLColor color, int increment) {
        super(ledStrip);
        startingColor = this.color = color;
        this.increment = increment;
    }

    @Override
    public void reset() {
        super.reset();
        color = startingColor;
    }

    @Override
    public HashMap<Integer, Color> generatePattern() {
        generatedHashmap.clear();

        for (int i = 1; i <= LEDStripLength; i += 1) {
            color = LLColor.fromHSV(LLColor.replaceHSVElement(color.toHSV(), 'H', color.getHue() + increment));

            generatedHashmap.put(i, color);
        }
        return generatedHashmap;
    }

}
