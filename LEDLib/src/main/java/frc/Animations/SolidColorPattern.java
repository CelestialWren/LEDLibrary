package frc.Animations;

import frc.LLColor;
import frc.LedStrip;


/**Intended to only be used in an LED scheduler for use in their pattern scripting, otherwise set the color directly. */
public class SolidColorPattern extends Animation {
    private LLColor color;
    private boolean gotOvewritten;
    public SolidColorPattern(LedStrip ledStrip, LLColor color){
        super(ledStrip);
        this.color = color;
        initializeCircleBuffer();
    }
    @Override
    public void generatePattern() {
        for(int c = 0; c < buffer.size(); c++) {
            gotOvewritten = (buffer.get(c) == color.getAsHex());
        }
        if(gotOvewritten)
            fillBufferWithOneColor(color);
    }
    @Override
    protected void initializeCircleBuffer() {
        fillBufferWithOneColor(color);
        
    }
    
}
