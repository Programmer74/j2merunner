package javax.microedition.lcdui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Image {
  public final java.awt.Image image;

  private Image(java.awt.Image image) {
    this.image = image;
  }

  public Graphics getGraphics() {
    if (image instanceof BufferedImage) {
      final BufferedImage bi = (BufferedImage) image;
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

  public static Image createImage(byte[] imageData, int imageOffset, int imageLength)
  throws IOException {
    byte[] bytearray = Arrays.copyOfRange(imageData, imageOffset, imageOffset + imageLength);
    InputStream in = new ByteArrayInputStream(bytearray);
    BufferedImage image = ImageIO.read(in);
    return new Image(image);
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

  public Image getSubImage(int x_src, int y_src, int width, int height, int transform) {
    if (image instanceof BufferedImage) {
      final BufferedImage bi = (BufferedImage) image;
      BufferedImage sub = bi.getSubimage(x_src, y_src, width, height);

      if (transform != TRANS_NONE) {
        if ((transform == TRANS_MIRROR) || (transform == TRANS_MIRROR_ROT90) || (transform
            == TRANS_MIRROR_ROT180) || (transform == TRANS_MIRROR_ROT270)) {
          sub = createFlippedHorizontally(sub);
        }
        double angle = 0.0;
        if ((transform == TRANS_MIRROR_ROT90) || (transform == TRANS_ROT90)) {
          angle = -0.5;
        }
        if ((transform == TRANS_MIRROR_ROT180) || (transform == TRANS_ROT180)) {
          angle = -1.0;
        }
        if ((transform == TRANS_MIRROR_ROT270) || (transform == TRANS_ROT270)) {
          angle = -1.5;
        }
        if (angle != 0.0) {
          sub = createRotated(sub, angle);
        }
      }

      return new Image(sub);
    } else {
      System.out.println("GET SUB IMAGE FAIL");
      throw new IllegalStateException("getSubImage");
    }
  }

  private static BufferedImage createFlippedHorizontally(BufferedImage image) {
    AffineTransform at = new AffineTransform();
    at.concatenate(AffineTransform.getScaleInstance(-1, 1));
    at.concatenate(AffineTransform.getTranslateInstance(-image.getWidth(), 0));
    return createTransformed(image, at);
  }

  private static BufferedImage createRotated(BufferedImage image, double angle) {
    AffineTransform at = AffineTransform.getRotateInstance(
        angle * Math.PI, image.getWidth() / 2, image.getHeight() / 2.0);
    return createTransformed(image, at);
  }

  private static BufferedImage createTransformed(
      BufferedImage image, AffineTransform at
  ) {
    BufferedImage newImage = new BufferedImage(
        image.getWidth(), image.getHeight(),
        BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = newImage.createGraphics();
    g.transform(at);
    g.drawImage(image, 0, 0, null);
    g.dispose();
    return newImage;
  }

  public int TRANS_NONE = 0;

  public int TRANS_MIRROR = 2;

  public int TRANS_MIRROR_ROT90 = 7;

  public int TRANS_MIRROR_ROT180 = 1;

  public int TRANS_MIRROR_ROT270 = 4;

  public int TRANS_ROT90 = 5;

  public int TRANS_ROT180 = 3;

  public int TRANS_ROT270 = 6;
}
