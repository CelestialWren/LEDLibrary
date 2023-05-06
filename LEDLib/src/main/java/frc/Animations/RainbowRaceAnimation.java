// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.Animations;

import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import frc.LLColor;
import frc.LedStrip;
import frc.PredefinedColors;

/** Add your docs here. */
public class RainbowRaceAnimation extends Animation {
    private LLColor startingColor;
    private LLColor color;
    private int increment = 8;

    public RainbowRaceAnimation(LedStrip ledStrip) {
        super(ledStrip);
        startingColor = color = PredefinedColors.kBlue;
    }
    /**
     * 
     * @param ledStrip
     * @param increment This can be negative to flip the direction of travel
     */
    public RainbowRaceAnimation(LedStrip ledStrip, int increment) {
        super(ledStrip);
        startingColor = color = PredefinedColors.kBlue;
        this.increment = increment;
    }
    /**
     * 
     * @param ledStrip
     * @param color
     */
    public RainbowRaceAnimation(LedStrip ledStrip, LLColor color) {
        super(ledStrip);
        startingColor = this.color = color;
    }
    /**
     * 
     * @param ledStrip
     * @param color
     * @param increment This can be negative to flip the direction of travel
     */
    public RainbowRaceAnimation(LedStrip ledStrip, LLColor color, int increment) {
        super(ledStrip);
        startingColor = this.color = color;
        this.increment = increment;
    }

    public RainbowRaceAnimation(LedStrip ledStrip, double minUpdatePeriod) {
        super(ledStrip, minUpdatePeriod);
        startingColor = color = PredefinedColors.kBlue;
    }
    /**
     * 
     * @param ledStrip
     * @param minUpdatePeriod
     * @param increment This can be negative to flip the direction of travel
     */
    public RainbowRaceAnimation(LedStrip ledStrip, double minUpdatePeriod, int increment) {
        super(ledStrip, minUpdatePeriod);
        startingColor = color = PredefinedColors.kBlue;
        this.increment = increment;
    }

    public RainbowRaceAnimation(LedStrip ledStrip, LLColor color, double minUpdatePeriod) {
        super(ledStrip, minUpdatePeriod);
        startingColor = this.color = color;
    }
    /**
     * 
     * @param ledStrip
     * @param color
     * @param minUpdatePeriod
     * @param increment This can be negative to flip the direction of travel
     */
    public RainbowRaceAnimation(LedStrip ledStrip, LLColor color, double minUpdatePeriod, int increment) {
        super(ledStrip, minUpdatePeriod);
        startingColor = this.color = color;
        this.increment = increment;
    }

    @Override
    public void reset() {
        super.reset();
        color = startingColor;
    }

    @Override
    public AddressableLEDBuffer generatePattern() {
        for (int i = 0; i < LEDStripLength; i += 1) {
            color = LLColor.fromHSV(LLColor.replaceHSVElement(LLColor.getFromColor(generatedBuffer.getLED(i)).toHSV(),
                    'H', LLColor.getFromColor(generatedBuffer.getLED(i)).getHue() + increment));
            generatedBuffer.setLED(i, color);
        }
        return generatedBuffer;
    }

    @Override
    public AddressableLEDBuffer initializePattern() {
        for (int i = 0; i < LEDStripLength; i += 1) {
            color = LLColor.fromHSV(LLColor.replaceHSVElement(color.toHSV(), 'H', color.getHue() + Math.abs(increment)));

            generatedBuffer.setLED(i, color);
        }
        return generatedBuffer;
    }

}
