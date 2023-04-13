// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.Animations;

import java.util.HashMap;

import edu.wpi.first.wpilibj.util.Color;
import frc.Led;

/** Add your docs here. */
public class BounceAnimation extends Animation {
    Color backgroundColor;
    Color bouncingColor;
    int bouncePositionUpper;
    int bouncePositionLower = 0;
    int bounceLength;
    boolean isGoingUp = true;
    //TODO: add illegal arguement protections if bounce length is bigger that the strip
    public BounceAnimation(Led ledStrip, Color backgroundColor, Color bouncingColor, int bounceLength){
        super(ledStrip);
        this.backgroundColor = backgroundColor;
        this.bouncingColor = bouncingColor;
        this.bounceLength = bounceLength;
        bouncePositionUpper = bounceLength;
    }

    @Override
    public void reset(){
        super.reset();
        bouncePositionUpper = bounceLength;
        bouncePositionLower = 0;
        isGoingUp = true;
    }

    @Override
    public HashMap<Integer, Color> generatePattern(){
        generatedHashmap.clear();
        if(bouncePositionUpper == LEDStripLength)
            isGoingUp = false;
        if(isGoingUp){
            bouncePositionLower++;
            bouncePositionUpper++;
        }
        else{
            bouncePositionLower--;
            bouncePositionUpper--;
        }
        generatedHashmap.put(bouncePositionLower, backgroundColor);
        generatedHashmap.put(bouncePositionUpper, bouncingColor);
        generatedHashmap.put(LEDStripLength, backgroundColor);
        return generatedHashmap;
    }
}