package frc.Examples;

import edu.wpi.first.wpilibj.DriverStation;
import frc.LedScheduler;
import frc.LedStrip;
import frc.PredefinedColors;
import frc.Animations.Animation;
import frc.Animations.BounceAnimation;
import frc.Animations.RaceAnimation;
import frc.Animations.RainbowFadeAnimation;
import frc.Animations.SolidColorPattern;

public class Scheduler extends LedScheduler {
    private Animation ifNotConnected;
    private Animation ifDisabled;
    private Animation ifAuton;
    private Animation ifTeleop;
    private Animation ifEStoped;
    private Animation ifAllElseFails;
    public Scheduler(LedStrip ledStrip){
        super(ledStrip);
        ifNotConnected = new SolidColorPattern(ledStrip, PredefinedColors.kPaleVioletRed);
        ifDisabled = new BounceAnimation(ledStrip, getAllianceColor(), PredefinedColors.kWhite, 10);
        ifAuton = new RainbowFadeAnimation(ledStrip);
        ifTeleop = new RaceAnimation(ledStrip, getAllianceColor(), PredefinedColors.kWhite, 5, 5);
        ifEStoped = new SolidColorPattern(ledStrip, PredefinedColors.kDarkRed);
        ifAllElseFails = new SolidColorPattern(ledStrip, PredefinedColors.kWhite);
    }

    @Override
    public Animation getAnimation() {
        if(!DriverStation.isDSAttached()){
            return ifNotConnected;
        }

        else if(DriverStation.isDisabled()){
            return ifDisabled;
        }

        else if(DriverStation.isTeleop()){
            return ifTeleop;
        }

        else if(DriverStation.isAutonomous()){
            return ifAuton;
        }
        else if(DriverStation.isEStopped()){
            return ifEStoped;
        }
        else{
            return ifAllElseFails;
        }
    }

    
    
}
