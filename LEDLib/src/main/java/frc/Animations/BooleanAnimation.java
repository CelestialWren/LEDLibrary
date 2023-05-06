package frc.Animations;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import frc.LLColor;
import frc.LedStrip;

public class BooleanAnimation extends Animation {
    private LLColor colorOnFalse;
    private LLColor colorOnTrue;
    private Supplier<Boolean> condition;
    public BooleanAnimation(LedStrip led, LLColor colorOnFalse, LLColor colorOnTrue, Supplier<Boolean> condition) {
        super(led);
        this.colorOnFalse = colorOnFalse;
        this.colorOnTrue = colorOnTrue;
        this.condition = condition;
    }

    @Override
    public AddressableLEDBuffer generatePattern() {
        if(condition.get()){
            generatedBuffer = fillBufferWithOneColor(colorOnTrue);
        }
        else{
            generatedBuffer = fillBufferWithOneColor(colorOnFalse);
        }
        return generatedBuffer;
    }

    @Override
    public AddressableLEDBuffer initializePattern() {
        return generatePattern();
    }

}
