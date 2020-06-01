package com.programmer74.j2merunner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class CanvasImpl extends JPanel {
  public final int width = 240;

  public final int height = 320;

  public final int upscale = 2;

  public final javax.microedition.lcdui.Canvas canvas;

  private final BufferedImage screen = new BufferedImage(
      width,
      height,
      BufferedImage.TYPE_INT_ARGB);

  public final KeyListener keyListener = new KeyListener() {
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
      int code = convertKeyCharToKeyCode(keyEvent);
      if (code != 0) {
        canvas.publicKeyPressed(code);
      }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
      int code = convertKeyCharToKeyCode(keyEvent);
      if (code != 0) {
        canvas.publicKeyReleased(code);
      } else {
        if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
          canvas.pressedEsc();
        }
      }
    }
  };

  public CanvasImpl(javax.microedition.lcdui.Canvas canvas) {
    this.canvas = canvas;
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(width * upscale, height * upscale);
  }

  private static long prevFrame = 0;

  @Override
  public void paintComponent(Graphics g) {
    //        long start = System.currentTimeMillis();

    if (upscale == 1) {
      canvas.paint(new javax.microedition.lcdui.Graphics(g));
    } else {
      canvas.paint(new javax.microedition.lcdui.Graphics(screen.getGraphics()));
      g.drawImage(screen, 0, 0, width * upscale, height * upscale, Color.WHITE, null);
    }
    //        long finish = System.currentTimeMillis();
    //        System.out.println("paint graphics: " + (finish - start) + "ms" + ", delta ms: " + (finish - prevFrame));
    //        prevFrame = finish;
  }

  private static int convertKeyCharToKeyCode(KeyEvent keyEvent) {
    switch (keyEvent.getKeyCode()) {
      case KeyEvent.VK_ENTER:
        return javax.microedition.lcdui.Canvas.FIRE;
      case KeyEvent.VK_LEFT:
        return javax.microedition.lcdui.Canvas.LEFT;
      case KeyEvent.VK_RIGHT:
        return javax.microedition.lcdui.Canvas.RIGHT;
      case KeyEvent.VK_UP:
        return javax.microedition.lcdui.Canvas.UP;
      case KeyEvent.VK_DOWN:
        return javax.microedition.lcdui.Canvas.DOWN;
      default:
        System.out.println("unknown keyEvent: " + keyEvent);
        return 0;
    }
  }
}
