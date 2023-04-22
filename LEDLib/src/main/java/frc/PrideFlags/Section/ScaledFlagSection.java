package frc.PrideFlags.Section;

import frc.LLColor;

public class ScaledFlagSection {
    private final LLColor sectionColor;
    private final double sectionEndPoint;

    /**Only for use in animations to handle end point of the LED Section */
    public ScaledFlagSection(FlagSection section,int scaledEndPoint){
        sectionColor = section.getSectionColor();
        sectionEndPoint = scaledEndPoint;
    }
    
    public LLColor getSectionColor() {
        return sectionColor;
    }

    public double getSectionEndPoint() {
        return sectionEndPoint;
    }
}
