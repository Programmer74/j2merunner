package javax.microedition.lcdui;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Graphics {

  public final static int HCENTER = 1;

  public final static int VCENTER = 2;

  public final static int LEFT = 4;

  public final static int RIGHT = 8;

  public final static int TOP = 16;

  public final static int BOTTOM = 32;

  public final static int BASELINE = 64;

  private final java.awt.Graphics graphics;

  private final FontRenderContext frc;

  public Graphics(java.awt.Graphics g) {
    graphics = g;
    frc = new FontRenderContext(new AffineTransform(), false, false);
  }

  public void drawChar(char c, int x, int y, int anchor) {
    drawString("" + c, x, y, anchor);
  }

  public void drawString(String s, int x, int y, int anchor) {
    Rectangle2D bounds = graphics.getFont().getStringBounds(s, frc);
    x = getAnchorX(x, (int) bounds.getWidth(), anchor);
    y = getAnchorY(y, (int) bounds.getHeight(), anchor) + (int) (bounds.getHeight());
    graphics.drawString(s, x, y);
  }

  private static int getAnchorX(int x, int size, int anchor) {
    if ((anchor & LEFT) != 0) {
      return x;
    }
    if ((anchor & RIGHT) != 0) {
      return x - size;
    }
    if ((anchor & HCENTER) != 0) {
      return x - size / 2;
    }
//    System.out.println("unknown anchor = " + anchor);
    return x;
  }

  private static int getAnchorY(int y, int size, int anchor) {
    if ((anchor & TOP) != 0) {
      return y;
    }
    if ((anchor & BOTTOM) != 0) {
      return y - size;
    }
    if ((anchor & VCENTER) != 0) {
      return y - size / 2;
    }
//    System.out.println("unknown anchor = " + anchor);
    return y;
  }

  public void drawRect(int x, int y, int w, int h) {
    graphics.drawRect(x, y, w, h);
  }

  public void fillRect(int x, int y, int w, int h) {
    graphics.fillRect(x, y, w, h);
  }

  public void fillArc(int x, int y, int w, int h, int startAngle, int arcAngle) {
    graphics.fillArc(x, y, w, h, startAngle, arcAngle);
  }

  public void drawArc(int x, int y, int w, int h, int startAngle, int arcAngle) {
    graphics.drawArc(x, y, w, h, startAngle, arcAngle);
  }

  public void drawLine(int x, int y, int x2, int y2) {
    graphics.drawLine(x, y, x2, y2);
  }

  public void drawImage(Image image, int x, int y, int anchor) {
    x = getAnchorX(x, image.getWidth(), anchor);
    y = getAnchorY(y, image.getHeight(), anchor);
    graphics.drawImage(image.image, x, y, null);
  }

  public Font getFont() {
    return new Font(graphics.getFont());
  }

  public void setColor(int red, int green, int blue) {
    graphics.setColor(new Color(red, green, blue));
  }

  public void setFont(Font font) {
    graphics.setFont(font.font);
  }

  public void setClip(int x, int y, int width, int height) {
    graphics.setClip(x, y, width, height);
  }

  public void translate(int x, int y) {
//    System.out.println("translate " + x + " " + y);
    graphics.translate(x, y);
  }

  public void setColor(int argb) {
    int r = (argb>>16)&0xFF;
    int g = (argb>>8)&0xFF;
    int b = (argb>>0)&0xFF;
    graphics.setColor(new Color(r, g, b));
  }

  public void drawRegion(Image src, int x_src, int y_src, int width, int height, int transform, int x_dst, int y_dst, int anchor) {
    final Image image = src.getSubImage(x_src, y_src, width, height, transform);
    x_dst = getAnchorX(x_dst, image.getWidth(), anchor);
    y_dst = getAnchorY(y_dst, image.getHeight(), anchor);
    graphics.drawImage(image.image, x_dst, y_dst, null);
  }

  public int getClipX() {
    return 0;
  }

  public int getClipY() {
    return 0;
  }

  public int getClipWidth() {
    return 0;
  }

  public int getClipHeight() {
    return 0;
  }
}
