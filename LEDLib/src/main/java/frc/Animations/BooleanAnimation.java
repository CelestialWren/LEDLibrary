package frc.Animations;

import java.util.function.Supplier;

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
        initializeCircleBuffer();
    }

    @Override
    public void generatePattern() {
        if(condition.get()){
            fillBufferWithOneColor(colorOnTrue);
        }
        else{
           fillBufferWithOneColor(colorOnFalse);
        }
    }

    @Override
    protected void initializeCircleBuffer() {
        if(condition.get()){
            fillBufferWithOneColor(colorOnTrue);
        }
        else{
           fillBufferWithOneColor(colorOnFalse);
        }
        
    }

}
