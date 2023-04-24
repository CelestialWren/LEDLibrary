package frc.PrideFlags.FlagAnimations;

import java.util.ArrayList;
import java.util.HashMap;

import edu.wpi.first.wpilibj.util.Color;
import frc.LedStrip;
import frc.Animations.Animation;
import frc.PrideFlags.Flag;
import frc.PrideFlags.Section.FlagSection;
import frc.PrideFlags.Section.ScaledFlagSection;

public class RaceFlagAnimation extends Animation{
    private Flag flag;
    private ArrayList<ScaledFlagSection> scaledSections = new ArrayList<>();
    public RaceFlagAnimation(LedStrip ledStrip, Flag flag){
        super(ledStrip);
        this.flag = flag;
        scaledSections = flag.generateSectionsScaled(ledStrip);
    }

    @Override
    public HashMap<Integer, Color> generatePattern() {
        if (generatedHashmap.size() == 0) {
            generatedHashmap.putAll(flag.generateProportions(getLedStrip()));
        } else {
           
            for (int stripEnd : generatedHashmap.keySet()) {
                 // Increment existing strips
                replaceElementKey(stripEnd, stripEnd + 1);

                for(ScaledFlagSection section : scaledSections){
                //generate new strip of color if nessacary
                if ((stripEnd == LEDStripLength + 1) && (generatedHashmap.get(stripEnd) == section.getSectionColor()))
                    generatedHashmap.put(1, section.getSectionColor());
                
                //Remove strips that are not visable
                 if(stripEnd == LEDStripLength + section.getSectionEndPoint() && (generatedHashmap.get(stripEnd) == section.getSectionColor()))
                    generatedHashmap.remove(stripEnd);
            }
        }
        }
        return generatedHashmap;
    }
}
