package frc.Animations;

import java.util.HashMap;

import edu.wpi.first.wpilibj.util.Color;
import frc.LLLColor;
import frc.Led;

public class FadeBetween2ColorsAnimation extends Animation {
    LLLColor firstColor;
    LLLColor secondColor;
    LLLColor currentColor;

    int deltaHue;
    int deltaSaturation;
    int deltaValue;

    boolean isgoingToSecond = true;

    HashMap<String, Integer> firstColorHSV;
    HashMap<String, Integer> secondColorHSV;
    HashMap<String, Integer> currentColorHSV;

    /**
     * 
     * @param ledStrip The LED strip this is displaying on.
     * @param firstColor The color it starts the fade on.
     * @param secondColor The color it initially fades to.
     */
    public FadeBetween2ColorsAnimation(Led ledStrip, LLLColor firstColor, LLLColor secondColor) {
        super(ledStrip);
        currentColorHSV = firstColor.toHSV();
        generatedHashmap.put(super.LEDStripLength, firstColor);

        this.firstColor = firstColor;
        this.secondColor = secondColor;

        firstColorHSV = firstColor.toHSV();
        secondColorHSV = secondColor.toHSV();

        deltaHue = evalIncrementDirection(firstColorHSV.get("H"), firstColorHSV.get("H"), LEDStripLength);
        deltaSaturation = evalIncrementDirection(firstColorHSV.get("S"), firstColorHSV.get("S"), LEDStripLength);
        deltaValue = evalIncrementDirection(firstColorHSV.get("V"), firstColorHSV.get("V"), LEDStripLength);
    }
    /**
     * @param ledStrip The LED strip this is displaying on.
     * @param firstColor The color it starts the fade on.
     * @param secondColor The color it initially fades to.
     * @param minUpdatePeriod the number of seconds it takes to change the color one step
     */
    public FadeBetween2ColorsAnimation(Led ledStrip, LLLColor firstColor, LLLColor secondColor, double minUpdatePeriod) {
        super(ledStrip, minUpdatePeriod);
        currentColorHSV = firstColor.toHSV();
        generatedHashmap.put(super.LEDStripLength, firstColor);

        this.firstColor = firstColor;
        this.secondColor = secondColor;

        firstColorHSV = firstColor.toHSV();
        secondColorHSV = secondColor.toHSV();

        deltaHue = evalIncrementDirection(firstColorHSV.get("H"), firstColorHSV.get("H"), LEDStripLength);
        deltaSaturation = evalIncrementDirection(firstColorHSV.get("S"), firstColorHSV.get("S"), LEDStripLength);
        deltaValue = evalIncrementDirection(firstColorHSV.get("V"), firstColorHSV.get("V"), LEDStripLength);
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
            currentColor = LLLColor.fromHSV(firstColorHSV.get("H") + deltaHue, firstColorHSV.get("S") + deltaSaturation,
                    firstColorHSV.get("V") + deltaValue);
            if (currentColor == secondColor)
                isgoingToSecond = false;
        } else {
            currentColor = LLLColor.fromHSV(firstColorHSV.get("H") - deltaHue, firstColorHSV.get("S") - deltaSaturation,
                    firstColorHSV.get("V") - deltaValue);
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
     * fastest way
     * 
     * @param firstValue
     * @param secondValue
     * @param maxValue
     * @return The the smallest delta.
     */
    public int evalIncrementDirection(int firstValue, int secondValue, int maxValue) {
        if (Math.abs(firstValue - secondValue) > Math.abs((maxValue - firstValue) - secondValue)) {
            return -(int) Math.signum(firstValue - secondValue);
        } else
            return -(int) Math.signum((maxValue - firstValue) - secondValue);
    }
}
