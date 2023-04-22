package frc.Animations;

import edu.wpi.first.math.MathUtil;
import frc.LLColor;
import frc.LedStrip;

public class FadeBetween2ColorsAnimation extends Animation {
    LLColor firstColor;
    LLColor secondColor;
    LLColor currentColor;
    int increment = 2;

    boolean isgoingToSecond = true;

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
    }

     /**
     * 
     * @param ledStrip    The LED strip this is displaying on.
     * @param firstColor  The color it starts the fade on.
     * @param secondColor The color it initially fades to.
     * @param increment The amount of RGB to fade by
     *      */
    public FadeBetween2ColorsAnimation(LedStrip ledStrip, LLColor firstColor, LLColor secondColor, int increment) {
        super(ledStrip);
        this.firstColor = firstColor;
        this.secondColor = secondColor;
        this.increment = increment;
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
        initializeCircleBuffer();
    }
    @Override
    public void reset() {
        super.reset();
    }

    @Override
    protected void initializeCircleBuffer() {
        currentColor = firstColor;
        isgoingToSecond = true;
        fillBufferWithOneColor(currentColor);
        
    }

    @Override
    public void generatePattern() {
        if (currentColor == secondColor)
            isgoingToSecond = false;
        else if (currentColor == firstColor)
            isgoingToSecond = true;
        if (isgoingToSecond) {
            currentColor = new LLColor(
                    (currentColor.getRed() + evalIncrementDirection(currentColor.getRed(), secondColor.getRed(), 255)),
                    currentColor.getGreen() + evalIncrementDirection(currentColor.getGreen(), secondColor.getGreen(), 255),
                    currentColor.getBlue() + evalIncrementDirection(currentColor.getBlue(), secondColor.getBlue(), 255));
            System.out.println("Going to 2");
        } else {
            currentColor = new LLColor(
                (currentColor.getRed() + evalIncrementDirection(currentColor.getRed(), firstColor.getRed(), 255)),
                currentColor.getGreen() + evalIncrementDirection(currentColor.getGreen(), firstColor.getGreen(), 255),
                currentColor.getBlue() + evalIncrementDirection(currentColor.getBlue(), firstColor.getBlue(), 255));
            System.out.println("Going to 1");
        }
      fillBufferWithOneColor(currentColor);
    }

    /**
     * Finds the direction to increment from the first to the second value in the
     * fastest way. It wraps the value around the range 0 to maxValue.
     * Evaluation method is inspiered by WPIlib PID controller value wrapping.
     * 
     * @param firstValue  The value you are starting at.
     * @param secondValue The value are going to.
     * @param maxValue    The maximum value for the object the values correspond to;
     *                    the point at which they wrap around.
     * @return The increment or decrement, as 0, 1, or -1.
     */
    private int evalIncrementDirection(int firstValue, int secondValue, int maxValue) {
        return (int) Math.signum(MathUtil.inputModulus(firstValue - secondValue, -(maxValue / 2), (maxValue / 2)))
                * increment;
    }
}
