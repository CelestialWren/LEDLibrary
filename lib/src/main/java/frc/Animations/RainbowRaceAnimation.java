// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.Animations;

import java.util.HashMap;

import edu.wpi.first.wpilibj.util.Color;
import frc.Led;

/** Add your docs here. */
public class RainbowRaceAnimation extends Animation {
    private int hue;
    private int startingHue;
    private int saturation;
    private int value;

    public RainbowRaceAnimation(Led ledStrip) {
        super(ledStrip);
        hue = 181;
        saturation = 255;
        value = 128;
    }

    public RainbowRaceAnimation(Led ledStrip, int hue) {
        super(ledStrip);
        this.hue = hue;
        startingHue = hue;
        saturation = 255;
        value = 128;
    }

    public RainbowRaceAnimation(Led ledStrip, int hue, int saturation, int value) {
        super(ledStrip);
        this.hue = hue = startingHue;
        this.saturation = saturation;
        this.value = value;
    }

    @Override
    public void reset() {
        super.reset();
        hue = startingHue;
    }

    @Override
    public HashMap<Integer, Color> generatePattern() {
        generatedHashmap.clear();
        for (int i = 1; i <= LEDStripLength; i += 1) {
            hue += 3;
            // 180 is the max value for a hue in HSV
            if (hue == 180)
                hue = 0;
            generatedHashmap.put(i, Color.fromHSV(hue, saturation, value));
        }
        return generatedHashmap;
    }

}
