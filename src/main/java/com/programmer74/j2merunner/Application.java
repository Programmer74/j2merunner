package com.programmer74.j2merunner;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.PropertyStore;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Application {
  public static void main(String[] args) {
    final String className = addJarToClasspathAndGetClassName(args[0]);

    Display.setCallback(impl -> {
      JFrame f = new JFrame("J2ME Runner");
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.add(impl);
      f.addKeyListener(impl.keyListener);
      f.addMouseListener(impl.mouseAdapter);
      f.pack();
      f.setVisible(true);
      f.setResizable(false);
      f.setLocationRelativeTo(null);
    });

    SwingUtilities.invokeLater(() -> {
      MIDlet midlet = createMIDlet(className);
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

  private static String addJarToClasspathAndGetClassName(final String jarFile) {
    try {
      final File file = new File(jarFile);
      final ZipFile zip = new ZipFile(file.getAbsolutePath());
      List<String> manifest = null;
      for (Enumeration e = zip.entries(); e.hasMoreElements(); ) {
        ZipEntry entry = (ZipEntry) e.nextElement();
        if (!entry.isDirectory()) {
          if (entry.getName().equals("META-INF/MANIFEST.MF")) {
            final ArrayList<String> lines = new ArrayList(new BufferedReader(new InputStreamReader(
                zip.getInputStream(entry)))
                .lines()
                .collect(Collectors.toList()));
            manifest = new ArrayList<String>();
            for (String line : lines) {
              if (!line.startsWith(" ")) {
                manifest.add(line);
              } else {
                manifest.set(manifest.size() - 1, manifest.get(manifest.size() - 1) + line.trim());
              }
            }
          }
        }
      }
      if (manifest == null) {
        throw new IllegalStateException("Manifest not found!");
      }

      final Optional<String> midletOne = manifest.stream().filter(x -> x.startsWith("MIDlet-1:"))
          .findFirst();
      if (!midletOne.isPresent()) {
        throw new IllegalStateException("MIDlet description");
      }

      final String mainClass = midletOne.get().split(",")[2].trim();

      manifest.forEach(manifestLine -> {
        final String[] lineSplitted = manifestLine.split(": ");
        if (lineSplitted.length == 2) {
          final String key = lineSplitted[0];
          final String value = lineSplitted[1];
          PropertyStore.setAppProperty(key, value);
        }
      });

      final URL url = file.toURI().toURL();
      URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
      Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
      method.setAccessible(true);
      method.invoke(classLoader, url);

      return mainClass;
    } catch (Exception ex) {
      ex.printStackTrace();
      System.exit(-1);
    }
    return "";
  }
}
