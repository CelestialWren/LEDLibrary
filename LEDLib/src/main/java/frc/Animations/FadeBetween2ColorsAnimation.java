package frc.Animations;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import frc.LLColor;
import frc.LedStrip;

public class FadeBetween2ColorsAnimation extends Animation {
    private LLColor firstColor;
    private LLColor secondColor;
   private LLColor currentColor;
    private LLColor target;
    private int increment;

    /**
     * 
     * @param ledStrip    The LED strip this is displaying on.
     * @param firstColor  The color it starts the fade on.
     * @param secondColor The color it initially fades to.
     */
    public FadeBetween2ColorsAnimation(LedStrip ledStrip, LLColor firstColor, LLColor secondColor) {
        super(ledStrip);
        this.firstColor = firstColor;
        this.secondColor = secondColor;
        increment = 3;
    }

    /**
     * @param ledStrip        The LED strip this is displaying on.
     * @param firstColor      The color it starts the fade on.
     * @param secondColor     The color it initially fades to.
     * @param minUpdatePeriod the number of seconds it takes to change the color one
     *                        step
     */
    public FadeBetween2ColorsAnimation(LedStrip ledStrip, LLColor firstColor, LLColor secondColor,
            double minUpdatePeriod) {
        super(ledStrip, minUpdatePeriod);

        this.firstColor = firstColor;
        this.secondColor = secondColor;
        increment = 3;
    }

    public void reset() {
        super.reset();
    }

    @Override
    public AddressableLEDBuffer generatePattern() {
        if (atTarget(currentColor) && target == secondColor)
            setTarget(firstColor);
        else if (atTarget(currentColor) && target == firstColor)
            setTarget(secondColor);
       currentColor = advanceTowardTarget(currentColor);
       generatedBuffer = fillBufferWithOneColor(currentColor);
        return generatedBuffer;
    }

    private void setTarget(LLColor color){
        target = color;
    }

    private boolean atTarget(LLColor currentColor){
        return target.equals(currentColor);
    }
    
    private LLColor advanceTowardTarget(LLColor currentColor){
        return LLColor.fromHSV(incrementTowards(currentColor.getHue(), target.getHue(), LLColor.HUE_MAX),
        incrementTowards(currentColor.getSaturation(), target.getSaturation(), LLColor.SATURATION_MAX), 
        incrementTowards(currentColor.getValue(), target.getValue(), LLColor.VALUE_MAX));
    }

    /**
     * Finds the direction to increment from the first to the second value in the
     * fastest way. It wraps the value around the range 0 to maxValue.
     * It then increments the colour in that direction and wraps it, if nessecary
     * Evaluation method is inspiered by WPIlib PID controller value wrapping.
     * 
     * @param firstValue  The value you are starting at.
     * @param secondValue The value are going to.
     * @param maxValue    The maximum value for the object the values correspond to;
     *                    the point at which they wrap around.
     * @return The increment or decrement, as 0, 1, or -1.
     */
    private int incrementTowards(int firstValue, int secondValue, int maxValue) {
        int valBeforeWrapping = firstValue + (int) (increment*Math.signum(MathUtil.inputModulus(firstValue - secondValue, -(maxValue / 2), (maxValue / 2))));
        return (int)MathUtil.inputModulus(valBeforeWrapping, 0, maxValue);
    }

    @Override
    public AddressableLEDBuffer initializePattern() {
        currentColor = firstColor;
        setTarget(secondColor);
        generatedBuffer = fillBufferWithOneColor(currentColor);
        return generatedBuffer;
    }
}
