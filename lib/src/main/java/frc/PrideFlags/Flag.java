package frc.PrideFlags;

import java.util.ArrayList;
import java.util.HashMap;

import edu.wpi.first.wpilibj.util.Color;
import frc.LLColor;
import frc.LedStrip;

public class Flag {
    private ArrayList<FlagSection> flagSections = new ArrayList<>();

    /** This will put the sections, reval */
    public Flag(FlagSection... sections) {
        double flagSectionFractionSum = 0;
        for (FlagSection section : sections) {
            flagSections.add(section);
            flagSectionFractionSum += section.getSectionFraction();
        }
        if (flagSectionFractionSum != 1) {
            flagSections = null;
            throw new IllegalArgumentException(
                    "The flag sections do not sum to one \n Double check the parameters passed in.");
        }
    }

    public HashMap<Integer, Color> generateProportions(LedStrip strip) {
        HashMap<Integer, Color> generatedProportions = new HashMap<>();
        int totalLength = 0;
        for (FlagSection section : flagSections) {
            totalLength += (int) (section.getSectionFraction() * strip.getStripLength());
            generatedProportions.put(totalLength, section.getSectionColor());
        }
        return generatedProportions;
    }

    public static Flag prideFlag = new Flag(FlagSection.makeFlagSection(new LLColor(229, 0, 0), 1.0 / 6.0),
            FlagSection.makeFlagSection(new LLColor(255, 141, 0), 1.0 / 6.0),
            FlagSection.makeFlagSection(new LLColor(229, 238, 0), 1.0 / 6.0),
            FlagSection.makeFlagSection(new LLColor(2, 129, 33), 1.0 / 6.0),
            FlagSection.makeFlagSection(new LLColor(0, 76, 255), 1.0 / 6.0),
            FlagSection.makeFlagSection(new LLColor(119, 0, 136), 1.0 / 6.0));

    public static Flag panFlag = new Flag(FlagSection.makeFlagSection(new LLColor(255, 28, 141), 1.0 / 3.0),
            FlagSection.makeFlagSection(new LLColor(255, 215, 0), 1.0 / 3.0),
            FlagSection.makeFlagSection(new LLColor(26, 179, 255), 1.0 / 3.0));

    public static Flag transFlag = new Flag(FlagSection.makeFlagSection(new LLColor(91, 207, 251), 1.0 / 5.0),
            FlagSection.makeFlagSection(new LLColor(245, 171, 185), 1.0 / 5.0),
            FlagSection.makeFlagSection(new LLColor(255, 255, 255), 1.0 / 5.0),
            FlagSection.makeFlagSection(new LLColor(245, 171, 185), 1.0 / 5.0),
            FlagSection.makeFlagSection(new LLColor(91, 207, 251), 1.0 / 5.0));

    public static Flag biFlag = new Flag(FlagSection.makeFlagSection(new LLColor(214, 2, 112), 2.0 / 5.0),
            FlagSection.makeFlagSection(new LLColor(155, 79, 150), 1.0 / 5.0),
            FlagSection.makeFlagSection(new LLColor(0, 56, 168), 2.0 / 5.0));

    public static Flag nonBinaryFlag = new Flag(FlagSection.makeFlagSection(new LLColor(252, 244, 49), 1.0 / 4.0),
            FlagSection.makeFlagSection(new LLColor(157, 89, 210), 1.0 / 4.0),
            FlagSection.makeFlagSection(new LLColor(252, 252, 252), 1.0 / 4.0),
            FlagSection.makeFlagSection(new LLColor(40, 40, 40), 1.0 / 4.0));

    public static Flag lesbianFlag = new Flag(FlagSection.makeFlagSection(new LLColor(214, 40, 0), 1.0 / 5.0),
            FlagSection.makeFlagSection(new LLColor(255, 155, 86), 1.0 / 5.0),
            FlagSection.makeFlagSection(new LLColor(255, 255, 255), 1.0 / 5.0),
            FlagSection.makeFlagSection(new LLColor(212, 98, 166), 1.0 / 5.0),
            FlagSection.makeFlagSection(new LLColor(164, 0, 98), 1.0 / 5.0));

    public static Flag agenderFlag = new Flag(FlagSection.makeFlagSection(new LLColor(0, 0, 0), 1.0 / 7.0),
            FlagSection.makeFlagSection(new LLColor(186, 186, 186), 1.0 / 7.0),
            FlagSection.makeFlagSection(new LLColor(255, 255, 255), 1.0 / 7.0),
            FlagSection.makeFlagSection(new LLColor(186, 244, 132), 1.0 / 7.0),
            FlagSection.makeFlagSection(new LLColor(255, 255, 255), 1.0 / 7.0),
            FlagSection.makeFlagSection(new LLColor(186, 186, 186), 1.0 / 7.0),
            FlagSection.makeFlagSection(new LLColor(0, 0, 0), 1.0 / 7.0));

    public static Flag asexualFlag = new Flag(FlagSection.makeFlagSection(new LLColor(0, 0, 0), 1.0 / 4.0),
            FlagSection.makeFlagSection(new LLColor(164, 164, 164), 1.0 / 4.0),
            FlagSection.makeFlagSection(new LLColor(255, 255, 255), 1.0 / 4.0),
            FlagSection.makeFlagSection(new LLColor(129, 0, 129), 1.0 / 4.0));

    public static Flag genderqueerFlag = new Flag(FlagSection.makeFlagSection(new LLColor(181, 127, 253), 1.0 / 3.0),
            FlagSection.makeFlagSection(new LLColor(255, 255, 255), 1.0 / 3.0),
            FlagSection.makeFlagSection(new LLColor(73, 130, 30), 1.0 / 3.0));

    public static Flag genderfluidFlag = new Flag(FlagSection.makeFlagSection(new LLColor(254, 118, 162), 1.0 / 5.0),
            FlagSection.makeFlagSection(new LLColor(255, 255, 255), 1.0 / 5.0),
            FlagSection.makeFlagSection(new LLColor(191, 18, 215), 1.0 / 5.0),
            FlagSection.makeFlagSection(new LLColor(0, 0, 0), 1.0 / 5.0),
            FlagSection.makeFlagSection(new LLColor(48, 60, 190), 1.0 / 5.0));

    public static Flag aromanticFlag = new Flag(FlagSection.makeFlagSection(new LLColor(59, 167, 64), 1.0 / 5.0),
            FlagSection.makeFlagSection(new LLColor(168, 212, 122), 1.0 / 5.0),
            FlagSection.makeFlagSection(new LLColor(255, 255, 255), 1.0 / 5.0),
            FlagSection.makeFlagSection(new LLColor(171, 171, 171), 1.0 / 5.0),
            FlagSection.makeFlagSection(new LLColor(48, 60, 190), 1.0 / 5.0));

}
