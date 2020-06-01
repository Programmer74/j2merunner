package com.programmer74.j2merunner;

import javax.microedition.lcdui.Command;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
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
        if ((keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) || (keyEvent.getKeyCode()
            == KeyEvent.VK_BACK_SPACE)) {
          canvas.pressedBack();
        }
      }
    }
  };

  public CanvasImpl(javax.microedition.lcdui.Canvas canvas) {
    this.canvas = canvas;
    fillKeyMappings();
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
    final Integer knownCode = keyCodeTwoMidletKeyMapping.get(keyEvent.getKeyCode());
    if (knownCode != null) {
      return knownCode;
    }
    System.out.println("unknown keyEvent: " + keyEvent);
    return 0;
  }

  private static HashMap<Integer, Integer> keyCodeTwoMidletKeyMapping = new HashMap<>();

  private static void fillKeyMappings() {
    keyCodeTwoMidletKeyMapping.put(
        KeyEvent.VK_ENTER,
        javax.microedition.lcdui.Canvas.FIRE);
    keyCodeTwoMidletKeyMapping.put(
        KeyEvent.VK_LEFT,
        javax.microedition.lcdui.Canvas.LEFT);
    keyCodeTwoMidletKeyMapping.put(
        KeyEvent.VK_RIGHT,
        javax.microedition.lcdui.Canvas.RIGHT);
    keyCodeTwoMidletKeyMapping.put(
        KeyEvent.VK_UP,
        javax.microedition.lcdui.Canvas.UP);
    keyCodeTwoMidletKeyMapping.put(
        KeyEvent.VK_DOWN,
        javax.microedition.lcdui.Canvas.DOWN);
    keyCodeTwoMidletKeyMapping.put(
        KeyEvent.VK_NUMPAD0,
        javax.microedition.lcdui.Canvas.KEY_NUM0);
    keyCodeTwoMidletKeyMapping.put(
        KeyEvent.VK_NUMPAD1,
        javax.microedition.lcdui.Canvas.KEY_NUM7);
    keyCodeTwoMidletKeyMapping.put(
        KeyEvent.VK_NUMPAD2,
        javax.microedition.lcdui.Canvas.KEY_NUM8);
    keyCodeTwoMidletKeyMapping.put(
        KeyEvent.VK_NUMPAD3,
        javax.microedition.lcdui.Canvas.KEY_NUM9);
    keyCodeTwoMidletKeyMapping.put(
        KeyEvent.VK_NUMPAD4,
        javax.microedition.lcdui.Canvas.KEY_NUM4);
    keyCodeTwoMidletKeyMapping.put(
        KeyEvent.VK_NUMPAD5,
        javax.microedition.lcdui.Canvas.KEY_NUM5);
    keyCodeTwoMidletKeyMapping.put(
        KeyEvent.VK_NUMPAD6,
        javax.microedition.lcdui.Canvas.KEY_NUM6);
    keyCodeTwoMidletKeyMapping.put(
        KeyEvent.VK_NUMPAD7,
        javax.microedition.lcdui.Canvas.KEY_NUM1);
    keyCodeTwoMidletKeyMapping.put(
        KeyEvent.VK_NUMPAD8,
        javax.microedition.lcdui.Canvas.KEY_NUM2);
    keyCodeTwoMidletKeyMapping.put(
        KeyEvent.VK_NUMPAD9,
        javax.microedition.lcdui.Canvas.KEY_NUM3);
    keyCodeTwoMidletKeyMapping.put(
        KeyEvent.VK_Q,
        javax.microedition.lcdui.Canvas.GAME_A);
    keyCodeTwoMidletKeyMapping.put(
        KeyEvent.VK_W,
        javax.microedition.lcdui.Canvas.GAME_B);
    keyCodeTwoMidletKeyMapping.put(
        KeyEvent.VK_E,
        javax.microedition.lcdui.Canvas.GAME_C);
    keyCodeTwoMidletKeyMapping.put(
        KeyEvent.VK_R,
        javax.microedition.lcdui.Canvas.GAME_D);

    keyCodeTwoMidletKeyMapping.put(
        KeyEvent.VK_A,
        javax.microedition.lcdui.Canvas.LEFT_SCREEN_BUTTON_NOKIA_S40);

    keyCodeTwoMidletKeyMapping.put(
        KeyEvent.VK_S,
        javax.microedition.lcdui.Canvas.CENTER_SCREEN_BUTTON_NOKIA_S40);

    keyCodeTwoMidletKeyMapping.put(
        KeyEvent.VK_D,
        javax.microedition.lcdui.Canvas.RIGHT_SCREEN_BUTTON_NOKIA_S40);
  }
}
