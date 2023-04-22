package frc;

import org.junit.Test;

import frc.Utils.PredefinedColors;

import static org.junit.Assert.*;

public class LLColorTest {
    @Test
    public void testHex(){
        assertEquals(PredefinedColors.kRed.getAsHex(), 0xff0000);
        assertEquals(PredefinedColors.kLime.getAsHex(), 0x00ff00);
        assertEquals(PredefinedColors.kBlue.getAsHex(), 0x0000ff);
        assertTrue(LLColor.getFromHex(0xFF0000).equals(PredefinedColors.kRed));
        assertTrue(LLColor.getFromHex(0x00FF00).equals(PredefinedColors.kLime));
        assertTrue(LLColor.getFromHex(0x0000FF).equals(PredefinedColors.kBlue));
    }
}
