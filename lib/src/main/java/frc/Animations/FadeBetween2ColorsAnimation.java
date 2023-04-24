package frc.Animations;

import java.util.HashMap;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.util.Color;
import frc.LLColor;
import frc.LedStrip;

public class FadeBetween2ColorsAnimation extends Animation {
    LLColor firstColor;
    LLColor secondColor;
    LLColor currentColor;

    boolean isgoingToSecond = true;

    /**
     * 
     * @param ledStrip    The LED strip this is displaying on.
     * @param firstColor  The color it starts the fade on.
     * @param secondColor The color it initially fades to.
     */
    public FadeBetween2ColorsAnimation(LedStrip ledStrip, LLColor firstColor, LLColor secondColor) {
        super(ledStrip);
        generatedHashmap.put(super.LEDStripLength, firstColor);

        this.firstColor = firstColor;
        this.secondColor = secondColor;
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
        generatedHashmap.put(super.LEDStripLength, firstColor);

        this.firstColor = firstColor;
        this.secondColor = secondColor;
    }

    public void reset() {
        super.reset();
        currentColor = firstColor;
        isgoingToSecond = true;

        generatedHashmap.put(LEDStripLength, currentColor);
    }

    @Override
    public HashMap<Integer, Color> generatePattern() {
        if (currentColor == secondColor)
            isgoingToSecond = false;
        else if (currentColor == firstColor)
            isgoingToSecond = true;
        if (isgoingToSecond) {
            currentColor = LLColor.fromHSV((currentColor.getHue() +
                    evalIncrementDirection(currentColor.getHue(), secondColor.getHue(), LLColor.HUE_MAX)),
                    (currentColor.getSaturation() +
                            evalIncrementDirection(currentColor.getSaturation(), secondColor.getSaturation(),
                                    LLColor.SATURATION_MAX)),
                    (currentColor.getValue() +
                            evalIncrementDirection(currentColor.getValue(), secondColor.getValue(),
                                    LLColor.VALUE_MAX)));
        } else {
            currentColor = LLColor.fromHSV((currentColor.getHue() +
                    evalIncrementDirection(currentColor.getHue(), firstColor.getHue(), LLColor.HUE_MAX)),
                    (currentColor.getSaturation() +
                            evalIncrementDirection(currentColor.getSaturation(), firstColor.getSaturation(),
                                    LLColor.SATURATION_MAX)),
                    (currentColor.getValue() +
                            evalIncrementDirection(currentColor.getValue(), firstColor.getValue(),
                                    LLColor.VALUE_MAX)));
        }
        generatedHashmap.clear();
        generatedHashmap.put(LEDStripLength, currentColor);
        return generatedHashmap;
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
        return (int) Math.signum(MathUtil.inputModulus(firstValue - secondValue, -(maxValue / 2), (maxValue / 2)));
    }
}
