package frc;

import java.util.HashMap;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.util.Color;

public class LLColor extends Color {
  public static final int HUE_MAX = 180;
  public static final int SATURATION_MAX = 255;
  public static final int VALUE_MAX = 255;
  public static final int RGB_MAX = 255;

  public LLColor(int red, int green, int blue) {
    super(red, green, blue);
  }

  public LLColor(double red, double green, double blue) {
    super(red, green, blue);
  }

  /**
   * @return
   *         This method returns the color as a hashmap with keys R, G, and B.
   *         <p>
   *         R is the red value and ranges from 0-255.
   *         </p>
   *         <p>
   *         G is the green value and ranges from 0-255.
   *         </p>
   *         B is the blue value and ranges from 0-255.
   */
  public HashMap<Character, Integer> toRGB() {
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    map.put('R', (int) (red * 255));
    map.put('G', (int) (green * 255));
    map.put('B', (int) (blue * 255));
    return map;
  }

  /** Returns the red value of a color, converted to 0 to 255 */
  public int getRed() {
    return (int) (red * 255);
  }

  /** Returns the green value of a color, converted to 0 to 255 */
  public int getGreen() {
    return (int) (green * 255);
  }

  /** Returns the blue value of a color, converted to 0 to 255 */
  public int getBlue() {
    return (int) (blue * 255);
  }

  /**
   * @return
   *         This method returns the color as a hashmap with keys H, S, and V.
   *         <p>
   *         H is the hue and ranges from 0-180.
   *         </p>
   *         <p>
   *         S is the saturation and ranges from 0-255.
   *         </p>
   *         V is the value or brightness and ranges from 0-255.
   */
  public HashMap<Character, Integer> toHSV() {
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    float[] hsv = java.awt.Color.RGBtoHSB(getRed(), getGreen(), getBlue(), null);
    map.put('H', (int) MathUtil.inputModulus((double) ((hsv[0]) * 180), 0, HUE_MAX));
    map.put('S', (int) MathUtil.inputModulus((double) ((hsv[1]) * 255), 0, SATURATION_MAX));
    map.put('V', (int) MathUtil.inputModulus((double) ((hsv[2]) * 255), 0, VALUE_MAX));
    return map;
  }

  public int getHue() {
    return (int) (java.awt.Color.RGBtoHSB(getRed(), getGreen(), getBlue(), null)[0] * 180);
  }

  public int getSaturation() {
    return (int) (java.awt.Color.RGBtoHSB(getRed(), getGreen(), getBlue(), null)[1] * 255);
  }

  public int getValue() {
    return (int) (java.awt.Color.RGBtoHSB(getRed(), getGreen(), getBlue(), null)[2] * 255);
  }

  public static HashMap<Character, Integer> replaceHSVElement(HashMap<Character, Integer> hsv, Character key,
      int value) {
    switch (key) {
      case 'H':
        hsv.remove(key);
        hsv.put(key, (int) MathUtil.inputModulus(value, 0, HUE_MAX));
        break;
      case 'S':
        hsv.remove(key);
        hsv.put(key, (int) MathUtil.inputModulus(value, 0, SATURATION_MAX));
        break;
      case 'V':
        hsv.remove(key);
        hsv.put(key, (int) MathUtil.inputModulus(value, 0, VALUE_MAX));
        break;
      default:
        throw new IllegalArgumentException(
            "Character passed was not H, S, or V; those correspond to hue, saturation, and value.");
    }

    hsv.put(key, value);
    return hsv;
  }

  public int getAsHex(){
    int rV = (int) red * RGB_MAX;
    int gV = (int) green * RGB_MAX;
    int bV = (int) blue * RGB_MAX;
   return rV << 16 | gV << 8 | bV;
  }

  public static LLColor getFromHex(int hexCode){
    int rV = (hexCode & (255 << 16)) >> 16;
    int gV = (hexCode & (255 << 8)) >> 8;
    int bV = hexCode & 255;
    return new LLColor(rV, gV, bV);
  }

  public static LLColor getFromColor(Color color){
    return new LLColor(color.red, color.green, color.blue);
  }

  /**
   * Creates a LLColor from HSV values.
   * Copied from the method of the same name in WPIlib's color class to enable
   * compatability with my other custom methods.
   *
   * @param h The h value [0-180)
   * @param s The s value [0-255]
   * @param v The v value [0-255]
   * @return The color
   */
  public static LLColor fromHSV(int h, int s, int v) {
    // Wrap hue, saturation, and value to proper range to prevent need for overflow
    // management code in the individual animations
    h = (int) MathUtil.inputModulus(h, 0, HUE_MAX);
    s = (int) MathUtil.inputModulus(s, 0, SATURATION_MAX);
    v = (int) MathUtil.inputModulus(v, 0, VALUE_MAX);

    // Loosely based on
    // https://en.wikipedia.org/wiki/HSL_and_HSV#HSV_to_RGB
    // The hue range is split into 60 degree regions where in each region there
    // is one rgb component at a low value (m), one at a high value (v) and one
    // that changes (X) from low to high (X+m) or high to low (v-X)

    // Difference between highest and lowest value of any rgb component
    final int chroma = (s * v) / 255;

    // Because hue is 0-180 rather than 0-360 use 30 not 60
    final int region = (h / 30) % 6;

    // Remainder converted from 0-30 to 0-255
    final int remainder = (int) Math.round((h % 30) * (255 / 30.0));

    // Value of the lowest rgb component
    final int m = v - chroma;

    // Goes from 0 to chroma as hue increases
    final int X = (chroma * remainder) >> 8;

    switch (region) {
      case 0:
        return new LLColor(v, X + m, m);
      case 1:
        return new LLColor(v - X, v, m);
      case 2:
        return new LLColor(m, v, X + m);
      case 3:
        return new LLColor(m, v - X, v);
      case 4:
        return new LLColor(X + m, m, v);
      default:
        return new LLColor(v, m, v - X);
    }
  }

  public static LLColor fromHSV(HashMap<Character, Integer> hashMap) {
    int hue = hashMap.get('H');
    int saturation = hashMap.get('S');
    int value = hashMap.get('V');
    return fromHSV(hue, saturation, value);
  }

  public static LLColor fromWPILibColor(Color color){
    return new LLColor(color.red, color.green, color.blue);
  }

}