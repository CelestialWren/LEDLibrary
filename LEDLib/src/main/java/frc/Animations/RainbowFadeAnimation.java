// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.Animations;

import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import frc.LLColor;
import frc.LedStrip;
import frc.PredefinedColors;

/** Add your docs here. */
public class RainbowFadeAnimation extends Animation {
   private LLColor startingColor;
   private LLColor color;
   private int increment = 3;
    public RainbowFadeAnimation(LedStrip ledStrip){
        super(ledStrip);
        color = startingColor = PredefinedColors.kBlue;
    }

    public RainbowFadeAnimation(LedStrip ledStrip, LLColor color){
        super(ledStrip);
        this.color = startingColor = color;
    }

    public RainbowFadeAnimation(LedStrip ledStrip, LLColor color, int increment){
        super(ledStrip);
        this.color = startingColor = color;
        this.increment = increment;
    }

    public RainbowFadeAnimation(LedStrip ledStrip, int increment){
        super(ledStrip);
        color = startingColor = PredefinedColors.kBlue;
        this.increment = increment;
    }


    @Override
    public void reset(){
        super.reset();
        color = startingColor;
    }

    @Override
    public AddressableLEDBuffer generatePattern() {
       color = LLColor.fromHSV(LLColor.replaceHSVElement(color.toHSV(), 'H', color.getHue()+increment));
        generatedBuffer =fillBufferWithOneColor(color);
        return generatedBuffer;
    }

    @Override
    public AddressableLEDBuffer initializePattern() {
        generatedBuffer = fillBufferWithOneColor(startingColor);
        return generatedBuffer;
    }

    

}
