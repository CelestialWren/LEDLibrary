package frc;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.util.Color;
import frc.Animations.Animation;

public abstract class LedScheduler {
    LedStrip ledStrip;

    public LedScheduler(LedStrip ledStrip) {
        this.ledStrip = ledStrip;
    }

    public abstract Animation getAnimation();

    public Color getAllianceColor() {
        if (DriverStation.getAlliance() == Alliance.Blue) {
            return  Color.kFirstBlue;
        }

        else if (DriverStation.getAlliance() == Alliance.Red) {
            return  Color.kFirstRed;
        }

        else {
            return Color.kBlack;
        }
    }
}
