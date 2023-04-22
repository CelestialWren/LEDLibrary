// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.Animations;

import edu.wpi.first.util.CircularBuffer;
import edu.wpi.first.wpilibj.Timer;
import frc.LLColor;
import frc.LedStrip;

/** Add your docs here. */
public abstract class Animation {
    private final Timer time = new Timer();
    protected double minUpdatePeriod = 0.01;
    private int cyclesRan = 0;
    private int maxCyclesToRun = -1;
    protected final int LEDStripLength;
    private final LedStrip ledStrip;
    protected CircularBuffer buffer;

    public Animation(LedStrip ledStrip) {
        this.ledStrip = ledStrip;
        time.start();
        LEDStripLength = ledStrip.getStripLength();
        buffer = new CircularBuffer(LEDStripLength);
    }

    public Animation(LedStrip ledStrip, double minUpdatePeriod) {
        this.ledStrip = ledStrip;
        time.start();
        this.minUpdatePeriod = minUpdatePeriod;
        LEDStripLength = ledStrip.getStripLength();
        buffer = new CircularBuffer(LEDStripLength);
        initializeCircleBuffer();
    }

    public Animation(LedStrip ledStrip, int maxCyclesToRun) {
        this.ledStrip = ledStrip;
        time.start();
        this.maxCyclesToRun = maxCyclesToRun;
        LEDStripLength = ledStrip.getStripLength();
        buffer = new CircularBuffer(LEDStripLength);
        initializeCircleBuffer();
    }

    public Animation(LedStrip ledStrip, double minUpdatePeriod, int maxCyclesToRun) {
        this.ledStrip = ledStrip;
        time.start();
        this.minUpdatePeriod = minUpdatePeriod;
        this.maxCyclesToRun = maxCyclesToRun;
        LEDStripLength = ledStrip.getStripLength();
        buffer = new CircularBuffer(LEDStripLength);
        initializeCircleBuffer();
    }

    /** Use to set initial state for reset or boot */
    protected abstract void initializeCircleBuffer();

    public CircularBuffer update() {
        if (time.advanceIfElapsed(minUpdatePeriod)) {
            cyclesRan++;
            if (cyclesRan == maxCyclesToRun) {
                ledStrip.stopAnimation();
            }
            if (buffer.equals(new CircularBuffer(LEDStripLength)))
                 initializeCircleBuffer();
            else
                generatePattern();   
        }
        return buffer;
    }

    protected abstract void generatePattern();

    public void reset() {
        cyclesRan = 0;
        buffer.clear();
        initializeCircleBuffer();
    }

    protected void fillBufferWithOneColor(LLColor color) {
        for (int c = 0; c < buffer.size(); c++) {
            buffer.addFirst(color.getAsHex());
        }
    }

    public int getCyclesRan() {
        return cyclesRan;
    }

    public LedStrip getLedStrip() {
        return ledStrip;
    }

}
