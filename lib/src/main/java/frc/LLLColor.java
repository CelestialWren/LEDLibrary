package frc;

import java.util.HashMap;

import edu.wpi.first.wpilibj.util.Color;

public class LLLColor extends Color {
  public static int HUE_MAX = 180;
  public static int SATURATION_MAX = 255;
  public static int VALUE_MAX = 255;

  public LLLColor(int red, int green, int blue) {
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
  public HashMap<String, Integer> toRGB() {
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    map.put("R", (int) (red * 255));
    map.put("G", (int) (green * 255));
    map.put("B", (int) (blue * 255));
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
  public HashMap<String, Integer> toHSV() {
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    float[] hsv = java.awt.Color.RGBtoHSB(getRed(), getGreen(), getBlue(), null);
    map.put("H", (int) (hsv[0]) * 180);
    map.put("S", (int) (hsv[1]) * 255);
    map.put("V", (int) (hsv[2]) * 255);
    return map;

  }

  /**
   * Creates a LLLColor from HSV values.
   * Copied from the method of the same name in WPIlib's color class to enable
   * compatability with my other custom methods.
   *
   * @param h The h value [0-180)
   * @param s The s value [0-255]
   * @param v The v value [0-255]
   * @return The color
   */
  public static LLLColor fromHSV(int h, int s, int v) {
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
        return new LLLColor(v, X + m, m);
      case 1:
        return new LLLColor(v - X, v, m);
      case 2:
        return new LLLColor(m, v, X + m);
      case 3:
        return new LLLColor(m, v - X, v);
      case 4:
        return new LLLColor(X + m, m, v);
      default:
        return new LLLColor(v, m, v - X);
    }
  }
}
