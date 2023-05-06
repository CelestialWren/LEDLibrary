package frc;

 import org.junit.Test;

 import static frc.PredefinedColors.*;

 import static org.junit.Assert.*;

 public class LLColorTest {
     @Test
     public void testHex(){
         assertEquals(kRed.getAsHex(), 0xff0000);
         assertEquals(kLime.getAsHex(), 0x00ff00);
         assertEquals(kBlue.getAsHex(), 0x0000ff);
         assertTrue(LLColor.getFromHex(0xFF0000).equals(kRed));
         assertTrue(LLColor.getFromHex(0x00FF00).equals(kLime));
         assertTrue(LLColor.getFromHex(0x0000FF).equals(kBlue));
     }
 }
