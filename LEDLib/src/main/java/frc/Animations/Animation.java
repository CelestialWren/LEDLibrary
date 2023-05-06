// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.Animations;


import edu.wpi.first.wpilibj.AddressableLEDBuffer;
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
    protected AddressableLEDBuffer generatedBuffer;

    public Animation(LedStrip ledStrip) {
        this.ledStrip = ledStrip;
        time.start();
        LEDStripLength = ledStrip.getStripLength();
        generatedBuffer = new AddressableLEDBuffer(LEDStripLength);
    }

    public Animation(LedStrip ledStrip, double minUpdatePeriod) {
       this(ledStrip);
       this.minUpdatePeriod = minUpdatePeriod;
    }

    public Animation(LedStrip ledStrip, int maxCyclesToRun) {
        this(ledStrip);
        this.maxCyclesToRun = maxCyclesToRun;
    }

    public Animation(LedStrip ledStrip, double minUpdatePeriod, int maxCyclesToRun) {
        this(ledStrip);
        this.minUpdatePeriod = minUpdatePeriod;
        this.maxCyclesToRun = maxCyclesToRun;
    }

    public AddressableLEDBuffer update() {
        if (time.advanceIfElapsed(minUpdatePeriod)) {
            if (isFinished()) {
                ledStrip.stopAnimation();
                return null;
            }
            cyclesRan++;
            if(cyclesRan == 1)
                return initializePattern();
            return generatePattern();
        }
        return null;
    }

    public abstract AddressableLEDBuffer generatePattern();

    public abstract AddressableLEDBuffer initializePattern();

    protected boolean isFinished(){
        return (cyclesRan == maxCyclesToRun);
    }

    public void reset() {
        cyclesRan = 0;
    }

    public int getCyclesRan() {
        return cyclesRan;
    }

    public AddressableLEDBuffer fillBufferWithOneColor(LLColor color){
        AddressableLEDBuffer buffer = new AddressableLEDBuffer(LEDStripLength);
        for(int c = 0; c < buffer.getLength(); c++){
            buffer.setLED(c, color);
        }
        return buffer;
    }


    public LedStrip getLedStrip() {
        return ledStrip;
    }

}
