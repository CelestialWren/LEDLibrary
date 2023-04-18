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

    HashMap<Character, Integer> firstColorHSV;
    HashMap<Character, Integer> secondColorHSV;
    HashMap<Character, Integer> currentColorHSV;

    boolean isgoingToSecond = true;
    /**
     * 
     * @param ledStrip    The LED strip this is displaying on.
     * @param firstColor  The color it starts the fade on.
     * @param secondColor The color it initially fades to.
     */
    public FadeBetween2ColorsAnimation(LedStrip ledStrip, LLColor firstColor, LLColor secondColor) {
        super(ledStrip);
        currentColorHSV = firstColor.toHSV();
        generatedHashmap.put(super.LEDStripLength, firstColor);

        this.firstColor = firstColor;
        this.secondColor = secondColor;

        firstColorHSV = firstColor.toHSV();
        secondColorHSV = secondColor.toHSV();
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
        currentColorHSV = firstColor.toHSV();
        generatedHashmap.put(super.LEDStripLength, firstColor);

        this.firstColor = firstColor;
        this.secondColor = secondColor;

        firstColorHSV = firstColor.toHSV();
        secondColorHSV = secondColor.toHSV();
    }

    public void reset() {
        super.reset();
        currentColor = firstColor;
        currentColorHSV = currentColor.toHSV();
        isgoingToSecond = true;

        generatedHashmap.put(LEDStripLength, currentColor);
    }

    @Override
    public HashMap<Integer, Color> generatePattern() {
        if (isgoingToSecond) {
            currentColor = LLColor.fromHSV((currentColorHSV.get('H') +
                    evalIncrementDirection(currentColorHSV.get('H'), secondColorHSV.get('H'), LLColor.HUE_MAX)),
                    (currentColorHSV.get('S') +
                            evalIncrementDirection(currentColorHSV.get('S'), secondColorHSV.get('S'),
                                    LLColor.SATURATION_MAX)),
                    (currentColorHSV.get('H') +
                            evalIncrementDirection(currentColorHSV.get('V'), secondColorHSV.get('V'),
                                    LLColor.VALUE_MAX)));
            if (currentColor == secondColor)
                isgoingToSecond = false;
        } else {
            currentColor = LLColor.fromHSV((currentColorHSV.get('H') +
                    evalIncrementDirection(currentColorHSV.get('H'), firstColorHSV.get('H'), LLColor.HUE_MAX)),
                    (currentColorHSV.get('S') +
                            evalIncrementDirection(currentColorHSV.get('S'), firstColorHSV.get('S'),
                                    LLColor.SATURATION_MAX)),
                    (currentColorHSV.get('H') +
                            evalIncrementDirection(currentColorHSV.get('V'), firstColorHSV.get('V'),
                                    LLColor.VALUE_MAX)));
            if (currentColor == firstColor)
                isgoingToSecond = true;
        }
        currentColorHSV = currentColor.toHSV();
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
        return -(int) Math.signum(MathUtil.inputModulus(firstValue - secondValue, -(maxValue / 2), (maxValue / 2)));
    }
}
