// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;


import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.simulation.AddressableLEDSim;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.Animations.Animation;

public class LedStrip extends SubsystemBase {
  private final AddressableLEDSim ledSim;
  private final AddressableLED led;
  private final AddressableLEDBuffer ledBuffer;
  private Animation currentAnimation;
  private boolean runningAutomation = false;
  private boolean usingScheduler = false;
  private LedScheduler scheduler;

  /** Creates a new Led. */
  public LedStrip(int DIO_Port, int stripLength) {
    ledBuffer = new AddressableLEDBuffer(stripLength);
    led = new AddressableLED(DIO_Port);
    ledSim = new AddressableLEDSim(led);

    led.setLength(ledBuffer.getLength());
    setEntireStripToColor(Color.kBlack);
    led.start();
    ledSim.setRunning(true);
  }

  public LedStrip(int DIO_Port, int stripLength, LedScheduler scheduler) {
    this.scheduler = scheduler;
    usingScheduler = true;

    ledBuffer = new AddressableLEDBuffer(stripLength);
    led = new AddressableLED(DIO_Port);
    ledSim = new AddressableLEDSim(led);

    led.setLength(ledBuffer.getLength());
    setEntireStripToColor(Color.kBlack);
    led.start();
    ledSim.setRunning(true);
  }

  public LedStrip(int DIO_Port, int stripLength, Color colorToIntializeTo) {
    ledBuffer = new AddressableLEDBuffer(stripLength);
    led = new AddressableLED(DIO_Port);
    ledSim = new AddressableLEDSim(led);

    led.setLength(ledBuffer.getLength());
    setEntireStripToColor(colorToIntializeTo);
    led.start();
    ledSim.setRunning(true);
  }

  public LedStrip(int DIO_Port, int stripLength, Color colorToIntializeTo, LedScheduler scheduler) {
    this.scheduler = scheduler;
    usingScheduler = true;

    ledBuffer = new AddressableLEDBuffer(stripLength);
    led = new AddressableLED(DIO_Port);
    ledSim = new AddressableLEDSim(led);

    led.setLength(ledBuffer.getLength());
    setEntireStripToColor(colorToIntializeTo);
    led.start();
    ledSim.setRunning(true);
  }

  @Override
  public void periodic() {
    if (usingScheduler) {
      setAnimation(scheduler.getAnimation());
    }
    // This method will be called once per scheduler run
    if (runningAutomation)
      try {
        setStripToMultipleColors(currentAnimation.update());
      } catch (Exception e) {
        System.out.println("The LED animation returned null");
      }
  }

  public void setEntireStripToColor(Color color) {
    runningAutomation = false;
    for (var i = 0; i < ledBuffer.getLength(); i++) {
      ledBuffer.setLED(i, color);
    }
    led.setData(ledBuffer);
  }

  public void setStripToMultipleColors(HashMap<Integer, Color> rangesToSetToColors) {
    runningAutomation = false;
    int stripPosition = 0;
    ArrayList<Integer> colorKeysSorted = sortStripValues(rangesToSetToColors);

    for (int colorEndPoint : colorKeysSorted) {
      for (; stripPosition < colorEndPoint; stripPosition++) {
        ledBuffer.setLED(stripPosition, rangesToSetToColors.get(colorEndPoint));
      }
      led.setData(ledBuffer);
    }
  }

  public ArrayList<Integer> sortStripValues(HashMap<Integer, Color> pattern) {
    ArrayList<Integer> colorKeysSorted = new ArrayList<Integer>();
    for (int colorEndToSort : pattern.keySet())
      colorKeysSorted.add(colorEndToSort);
      
    colorKeysSorted.sort(Comparator.naturalOrder());
    return colorKeysSorted;

  }

  public void setAnimation(Animation animation) {
    currentAnimation = animation;
    currentAnimation.reset();
    runningAutomation = true;
  }

  public void startAnimation() {
    runningAutomation = true;
    currentAnimation.reset();
  }

  public void stopAnimation() {
    runningAutomation = false;
    currentAnimation.reset();
  }

  public void enableScheduler() {
    if (scheduler != null)
      usingScheduler = true;
  }

  public void disableScheduler() {
    usingScheduler = false;
  }

  public int getStripLength() {
    return ledBuffer.getLength();
  }
}
