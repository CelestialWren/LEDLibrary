// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.Animations;

import java.util.HashMap;

import edu.wpi.first.wpilibj.util.Color;
import frc.LedStrip;

/** Add your docs here. */
public class RaceAnimation extends Animation {
    private final Color backgroundColor, raceColor;
    private final int raceLength, raceGap;

    public RaceAnimation(LedStrip ledStrip, Color backgroundColor, Color raceColor, int raceLength, int raceGap) {
        super(ledStrip);
        this.backgroundColor = backgroundColor;
        this.raceColor = raceColor;
        this.raceLength = raceLength;
        this.raceGap = raceGap;
        if ((raceLength > LEDStripLength) || (raceGap > LEDStripLength))
            throw new IllegalArgumentException("The length of the pattern to display is longer than the LED Strip.");
        else {
            generatedHashmap.put(raceLength, raceColor);
            generatedHashmap.put(LEDStripLength, backgroundColor);
        }
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    public HashMap<Integer, Color> generatePattern() {
        if (generatedHashmap.size() == 0) {
            generatedHashmap.put(raceLength, raceColor);
            generatedHashmap.put(LEDStripLength, backgroundColor);
        } else {
            // Increment existing strips
            for (int stripEnd : generatedHashmap.keySet()) {
                replaceElementKey(stripEnd, stripEnd + 1);

                // Need to Generate a new background strip
                if ((stripEnd == raceLength + 1) && (generatedHashmap.get(stripEnd) == raceColor))
                    generatedHashmap.put(1, backgroundColor);

                // Need to generate new race strip
                else if ((stripEnd == raceGap + 1) && (generatedHashmap.get(stripEnd) == backgroundColor))
                    generatedHashmap.put(1, raceColor);

                // Remove strips that are not visable
                if (stripEnd == LEDStripLength + raceLength && (generatedHashmap.get(stripEnd) == raceColor))
                    generatedHashmap.remove(stripEnd);
                if (stripEnd == LEDStripLength + raceGap && (generatedHashmap.get(stripEnd) == backgroundColor))
                    generatedHashmap.remove(stripEnd);
            }
        }
        return generatedHashmap;
    }

}
