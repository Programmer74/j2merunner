package com.programmer74.j2merunner;

import javax.microedition.lcdui.Command;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Set;

public class CanvasImpl extends JPanel {
  public final int width = 240;

  public final int height = 320;

  public final int buttonsHeight = 20;

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
    return new Dimension(width * upscale, height * upscale + buttonsHeight);
  }

  @Override
  public void paintComponent(Graphics g) {
    if (upscale == 1) {
      canvas.paint(new javax.microedition.lcdui.Graphics(g));
    } else {
      canvas.paint(new javax.microedition.lcdui.Graphics(screen.getGraphics()));
      g.drawImage(screen, 0, 0, width * upscale, height * upscale, Color.WHITE, null);
    }

    g.setColor(Color.WHITE);
    g.fillRect(0, height * upscale, width * upscale, height * upscale + buttonsHeight);
    g.setColor(Color.BLACK);

    final Set<Command> commands = canvas.getCommands();
    final int y = height * upscale + 15;
    final int offsetX = 10 * upscale;
    g.setFont(new Font("default", Font.BOLD, 16));
    commands.forEach(cmd -> {
      int x = offsetX + width * upscale / 3;

      if (cmd.type == Command.OK) {
        x = width * upscale / 2 - offsetX / 2;
      }

      if (cmd.type == Command.BACK) {
        x = width * upscale * 2 / 3;
      }

      g.drawString(cmd.name, x, y);
    });
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
