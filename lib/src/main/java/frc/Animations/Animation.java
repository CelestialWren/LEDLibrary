// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.Animations;

import java.util.HashMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.util.Color;
import frc.LedStrip;

/** Add your docs here. */
public abstract class Animation {
    private final Timer time = new Timer();
    protected double minUpdatePeriod = 0.01;
    private int cyclesRan = 0;
    private int maxCyclesToRun = -1;
    protected final int LEDStripLength;
    private final LedStrip ledStrip;
    protected HashMap<Integer, Color> generatedHashmap;

    public Animation(LedStrip ledStrip) {
        this.ledStrip = ledStrip;
        time.start();
        LEDStripLength = ledStrip.getStripLength();
        generatedHashmap.clear();
    }

    public Animation(LedStrip ledStrip, double minUpdatePeriod) {
        this.ledStrip = ledStrip;
        time.start();
        this.minUpdatePeriod = minUpdatePeriod;
        LEDStripLength = ledStrip.getStripLength();
        generatedHashmap.clear();
    }

    public Animation(LedStrip ledStrip, int maxCyclesToRun) {
        this.ledStrip = ledStrip;
        time.start();
        this.maxCyclesToRun = maxCyclesToRun;
        LEDStripLength = ledStrip.getStripLength();
        generatedHashmap.clear();
    }

    public Animation(LedStrip ledStrip, double minUpdatePeriod, int maxCyclesToRun) {
        this.ledStrip = ledStrip;
        time.start();
        this.minUpdatePeriod = minUpdatePeriod;
        this.maxCyclesToRun = maxCyclesToRun;
        LEDStripLength = ledStrip.getStripLength();
        generatedHashmap.clear();
    }

    public HashMap<Integer, Color> update() {
        if (time.advanceIfElapsed(minUpdatePeriod)) {
            cyclesRan++;
            if (cyclesRan == maxCyclesToRun) {
                ledStrip.stopAnimation();
                return null;
            }
            return generatePattern();
        }
        return null;
    }

    public abstract HashMap<Integer, Color> generatePattern();

    public void reset() {
        cyclesRan = 0;
        generatedHashmap.clear();
    }

    public int getCyclesRan() {
        return cyclesRan;
    }

    protected void replaceElementKey(int key, int newKey) {
        Color storedColor = generatedHashmap.get(key);
        generatedHashmap.remove(key);
        generatedHashmap.put(newKey, storedColor);
    }

    public LedStrip getLedStrip() {
        return ledStrip;
    }

}
