package com.programmer74.j2merunner;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.swing.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Application {
  public static void main(String[] args) {
    //    final String className =
    //    addJarToClasspathAndGetClassName(args[0]);

    Display.setCallback(impl -> {
      JFrame f = new JFrame("J2ME Emulator");
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.add(impl);
      f.addKeyListener(impl.keyListener);
      f.pack();
      f.setVisible(true);
      f.setResizable(false);
    });

    SwingUtilities.invokeLater(() -> {
      MIDlet midlet = createMIDlet("Micro");
      System.setProperty("microedition.platform", "");
      midlet.runApp();
    });
  }

  private static MIDlet createMIDlet(final String mainClass) {
    try {
      Class<?> cls = Class.forName(mainClass);
      Constructor<?> constructor = cls.getConstructor();
      return (MIDlet) constructor.newInstance();
    } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}
