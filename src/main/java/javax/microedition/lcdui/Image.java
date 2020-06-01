package javax.microedition.lcdui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Image {
  public final java.awt.Image image;

  private Image(java.awt.Image image) {
    this.image = image;
  }

  public Graphics getGraphics() {
    if (image instanceof BufferedImage) {
      final BufferedImage bi = (BufferedImage)image;
      return new Graphics(bi.createGraphics());
    } else return null;
  }

  public int getWidth() {
    return image.getWidth(null);
  }

  public int getHeight() {
    return image.getHeight(null);
  }

  public static Image createImage(int w, int h) {
    return new Image(new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB));
  }

  public static Image createImage(String path) throws IOException {
    System.out.println("load image " + path);

    try (InputStream is = new Object().getClass().getResourceAsStream(path)) {
      return new Image(ImageIO.read(is));
    } catch (Exception ex) {
      System.exit(0);
      return null;
    }
  }
}
