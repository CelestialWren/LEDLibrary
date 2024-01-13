package frc;

import frc.Animations.Animation;

public abstract class LedScheduler {
    LedStrip ledStrip;

    public LedScheduler(LedStrip ledStrip) {
        this.ledStrip = ledStrip;
    }

    public abstract Animation getAnimation();
    
}
    
