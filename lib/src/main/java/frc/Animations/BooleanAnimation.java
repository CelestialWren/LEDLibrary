package frc.Animations;

import java.util.HashMap;
import java.util.function.Supplier;

import edu.wpi.first.wpilibj.util.Color;
import frc.LedStrip;

public class BooleanAnimation extends Animation {
    private Color colorOnFalse;
    private Color colorOnTrue;
    private Supplier<Boolean> condition;
    public BooleanAnimation(LedStrip led, Color colorOnFalse, Color colorOnTrue, Supplier<Boolean> condition) {
        super(led);
        this.colorOnFalse = colorOnFalse;
        this.colorOnTrue = colorOnTrue;
        this.condition = condition;
    }

    @Override
    public HashMap<Integer, Color> generatePattern() {
        if(condition.get()){
            generatedHashmap.clear();
            generatedHashmap.put(LEDStripLength, colorOnTrue);
        }
        else{
            generatedHashmap.clear();
            generatedHashmap.put(LEDStripLength, colorOnFalse);
        }
        return generatedHashmap;
    }

}
