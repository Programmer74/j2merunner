package javax.microedition.lcdui;

import com.programmer74.j2merunner.CanvasImpl;
import javax.microedition.midlet.MIDlet;

public class Display {

  private static IPrepareCanvas onPrepareCanvas;

  private Display() {
  }

  public void setCurrent(Displayable displayable) {
    if (displayable instanceof Canvas) {
      Canvas canvas = (Canvas) displayable;
      onPrepareCanvas.initCanvas(canvas.impl);
      return;
    }
    if (displayable instanceof Alert) {
      Alert alert = (Alert) displayable;
      System.out.println("set alert = " + alert);
      return;
    }
    assert false;
  }

  public static Display getDisplay(MIDlet midlet) {
    return new Display();
  }

  public static void setCallback(IPrepareCanvas callback) {
    onPrepareCanvas = callback;
  }

  public interface IPrepareCanvas {
    void initCanvas(CanvasImpl canvas);
  }

  public int numColors() {
    return 256 * 256 * 256;
  }
}
