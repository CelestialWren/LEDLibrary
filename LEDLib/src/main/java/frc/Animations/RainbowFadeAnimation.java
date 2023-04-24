// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.Animations;

import frc.LLColor;
import frc.LedStrip;
import frc.Utils.PredefinedColors;

/** Add your docs here. */
public class RainbowFadeAnimation extends Animation {
   private LLColor startingColor;
   private LLColor color;
   private int increment = 3;
    public RainbowFadeAnimation(LedStrip ledStrip){
        super(ledStrip);
        color = startingColor = PredefinedColors.kBlue;
        initializeCircleBuffer();
    }

    public RainbowFadeAnimation(LedStrip ledStrip, LLColor color){
        super(ledStrip);
        this.color = startingColor = color;
        initializeCircleBuffer();
    }

    public RainbowFadeAnimation(LedStrip ledStrip, LLColor color, int increment){
        super(ledStrip);
        this.color = color;
        this.increment = increment;
        initializeCircleBuffer();
    }

    public RainbowFadeAnimation(LedStrip ledStrip, int increment){
        super(ledStrip);
        color = startingColor = PredefinedColors.kBlue;
        this.increment = increment;
        initializeCircleBuffer();
    }


    @Override
    public void reset(){
        super.reset();
    }

    @Override
    public void generatePattern() {
       color = LLColor.fromHSV(LLColor.replaceHSVElement(color.toHSV(), 'H', color.getHue()+increment));
        fillBufferWithOneColor(color);
    }

    @Override
    protected void initializeCircleBuffer() {
        color = startingColor;
        fillBufferWithOneColor(color);   
    }

    

}
