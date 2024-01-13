// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.simulation.AddressableLEDSim;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.Animations.Animation;
import frc.Animations.SolidColorPattern;

public class LedStrip extends SubsystemBase {
	private final AddressableLEDSim ledSim;
	private final AddressableLED led;
	private AddressableLEDBuffer ledBuffer;
	public Animation currentAnimation = new SolidColorPattern(this, PredefinedColors.kBlack);
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
			}
			// to prevent a crash when the LED animation returns null since it has not had
			// enough time to pass to update
			catch (Exception e) {
			}

	}

	public void setEntireStripToColor(Color color) {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setLED(i, color);
		}
		led.setData(ledBuffer);
	}

	public void setStripToMultipleColors(AddressableLEDBuffer buffer) {
		ledBuffer = buffer;
		led.setData(ledBuffer);
	}

	public ArrayList<Integer> sortStripValues(HashMap<Integer, LLColor> pattern) {
		ArrayList<Integer> colorKeysSorted = new ArrayList<Integer>();
		for (int colorEndToSort : pattern.keySet())
			colorKeysSorted.add(colorEndToSort);

		colorKeysSorted.sort(Comparator.naturalOrder());
		return colorKeysSorted;

	}

	public void setAnimation(Animation animation) {
		currentAnimation = animation;
		startAnimation();
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

	public LLColor getAllianceColor() {
		if (DriverStation.getAlliance().isPresent()) {

			if (DriverStation.getAlliance().get() == Alliance.Blue) {
				return PredefinedColors.kFirstBlue;
			} else if (DriverStation.getAlliance().get() == Alliance.Red) {
				return PredefinedColors.kFirstRed;
			}
		}

		return PredefinedColors.kBlack;

	}
}
