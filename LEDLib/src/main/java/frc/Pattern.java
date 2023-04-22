package frc;

public class Pattern {
    private final LLColor sectionColor;
    private final double sectionEndPoint;

    /**Only for use in animations to handle end point of the LED Section */
    public Pattern(LLColor color,int endPoint){
        sectionColor = color;
        sectionEndPoint = endPoint;
    }
    
    public LLColor getColor() {
        return sectionColor;
    }

    public double getEndPoint() {
        return sectionEndPoint;
    }
;
    }
