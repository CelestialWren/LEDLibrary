package frc;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import frc.Animations.Animation;

public abstract class LedScheduler {
    LedStrip ledStrip;

    public LedScheduler(LedStrip ledStrip) {
        this.ledStrip = ledStrip;
    }

    public abstract Animation getAnimation();

    public LLColor getAllianceColor() {
        if (DriverStation.getAlliance() == Alliance.Blue) {
            return  PredefinedColors.kFirstBlue;
        }

        else if (DriverStation.getAlliance() == Alliance.Red) {
            return  PredefinedColors.kFirstRed;
        }

        else {
            //TODO: make this throw exception (Curently ureachable since WPILib is dumb and defualts to blue in not connected)
            return PredefinedColors.kBlack;
        }
    }
}
