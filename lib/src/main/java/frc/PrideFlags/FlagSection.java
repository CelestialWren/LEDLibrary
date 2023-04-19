package frc.PrideFlags;

import frc.LLColor;

public class FlagSection {
    private final LLColor sectionColor;
    private final double sectionFraction;

    private FlagSection(LLColor sectionColor, double sectionFraction){
        this.sectionColor = sectionColor;
        this.sectionFraction = sectionFraction;
    }

    public LLColor getSectionColor() {
        return sectionColor;
    }

    public double getSectionFraction() {
        return sectionFraction;
    }

    public static FlagSection makeFlagSection(LLColor sectionColor, double sectionFraction){
        if((sectionFraction > 1) || (sectionFraction <= 0))
            return new FlagSection(sectionColor, sectionFraction);
        else{
            throw new IllegalArgumentException("The fraction for this section is not a valid section length.");
        }
    }
}
