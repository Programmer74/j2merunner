package javax.microedition.lcdui;

import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class Font {
  public static final int SIZE_SMALL = 8;

  public static final int SIZE_MEDIUM = 0;

  public static final int SIZE_LARGE = 16;

  public final java.awt.Font font;

  private final int height;

  public Font(java.awt.Font font) {
    assert font != null;
    this.font = font;
    this.height = font.getSize();
  }

  public int charWidth(char c) {
    return stringWidth("" + c);
  }

  public int stringWidth(String s) {
    return (int) font.getStringBounds(s, new FontRenderContext(new AffineTransform(), false, true))
        .getWidth();
  }

  public int substringWidth(String string, int offset, int len) {
    return stringWidth(string.substring(offset, offset + len));
  }

  public int getBaselinePosition() {
    return height;
  }

  public int getHeight() {
    return height;
  }

  public static Font getDefaultFont() {
    return getFont(0, java.awt.Font.PLAIN, Font.SIZE_MEDIUM);
  }

  public static Font getFont(int face, int style, int size) {
    return new Font(new java.awt.Font(java.awt.Font.SANS_SERIF, style, getRealFontSize(size)));
  }

  private static int getRealFontSize(int size) {
    switch (size) {
      case SIZE_LARGE:
        return 32;
      case SIZE_MEDIUM:
        return 16;
      case SIZE_SMALL:
        return 12;
      default:
        System.out.println("unknown font size: " + size);
        throw new RuntimeException("unknown font size: " + size);
    }
  }
}
