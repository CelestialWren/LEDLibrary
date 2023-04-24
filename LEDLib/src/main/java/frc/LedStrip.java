// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc;


import edu.wpi.first.util.CircularBuffer;
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
      } catch (Exception e) {}
  }

  public void setEntireStripToColor(Color color) {
    for (var i = 0; i < ledBuffer.getLength(); i++) {
      ledBuffer.setLED(i, color);
    }
    led.setData(ledBuffer);
  }

  public void setStripToMultipleColors(CircularBuffer buffer) {
      for(int c = 0; c < getStripLength(); c++)
        ledBuffer.setLED(c, LLColor.getFromHex((int)buffer.get(c)));
      led.setData(ledBuffer);
    }

  public void mannualSetStripToMultipleColors(CircularBuffer buffer){
    setStripToMultipleColors(buffer);
    stopAnimation();
  }

  public void mannualSetEntireStripToColor(Color color){
    setEntireStripToColor(color);
    stopAnimation();
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

  public boolean isRunningAutomation() {
      return runningAutomation;
  }

  public void hi(){
    System.out.println("hi");
  }

  public int getStripLength() {
    return ledBuffer.getLength();
  }
}
