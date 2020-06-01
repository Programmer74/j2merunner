package javax.microedition.lcdui;

import com.programmer74.j2merunner.CanvasImpl;
import java.util.HashSet;
import java.util.Set;

public abstract class Canvas extends Displayable {

  /**
   * UP 1
   * DOWN 6
   * LEFT 2
   * RIGHT 5
   * FIRE 8
   * GAME_A 9
   * GAME_B 10
   * GAME_C 11
   * GAME_D 12
   * https://docs.oracle.com/javame/config/cldc/ref-impl/midp2.0/jsr118/javax/microedition/lcdui/Canvas.html#UP
   */

  public static final int UP = 1;

  public static final int DOWN = 6;

  public static final int LEFT = 2;

  public static final int RIGHT = 5;

  public static final int FIRE = 8;

  public static final int GAME_A = 9;

  public static final int GAME_B = 10;

  public static final int GAME_C = 11;

  public static final int GAME_D = 12;

  public static final int LEFT_SCREEN_BUTTON_NOKIA_S40 = -6;

  public static final int RIGHT_SCREEN_BUTTON_NOKIA_S40 = -7;

  public static final int CENTER_SCREEN_BUTTON_NOKIA_S40 = -5;

  public static final int KEY_NUM0 = 48;

  public static final int KEY_NUM1 = 49;

  public static final int KEY_NUM2 = 50;

  public static final int KEY_NUM3 = 51;

  public static final int KEY_NUM4 = 52;

  public static final int KEY_NUM5 = 53;

  public static final int KEY_NUM6 = 54;

  public static final int KEY_NUM7 = 55;

  public static final int KEY_NUM8 = 56;

  public static final int KEY_NUM9 = 57;

  public static final int KEY_STAR = 42;

  public static final int KEY_POUND = 35;

  public final CanvasImpl impl;

  private final HashSet<Command> currentCommands = new HashSet<>();

  private CommandListener commandListener;

  public Canvas() {
    impl = new CanvasImpl(this);
  }

  public void repaint() {
    impl.repaint();
  }

  public void repaint(int x, int y, int w, int h) {
    impl.repaint();
  }

  public void serviceRepaints() {
    // todo waiting repaint finish
  }

  public void pressedBack() {
    for (Command cmd : currentCommands) {
      if (cmd.type == Command.BACK || currentCommands.size() == 1) {
        commandListener.commandAction(cmd, this);
        return;
      }
    }
  }

  public abstract void paint(Graphics graphics);

  public boolean isShown() {
    return true;
  }

  public void publicKeyPressed(int keyCode) {
    keyPressed(keyCode);
  }

  protected abstract void keyPressed(int keyCode);

  public void publicKeyReleased(int keyCode) {
    keyReleased(keyCode);
  }

  protected abstract void keyReleased(int keyCode);

  protected abstract void keyRepeated(int keyCode);

  // todo call them
  public abstract void pointerPressed(int x, int y);

  public abstract void pointerReleased(int x, int y);

  public abstract void pointerDragged(int x, int y);

  public boolean hasPointerEvents() {
    return false;
    // todo true it possible too
  }

  public int getGameAction(int keyCode) {
    return keyCode;
  }

  public void removeCommand(Command command) {
    currentCommands.remove(command);
    System.out.println("removeCommand(" + command + ")");
  }

  public void addCommand(Command command) {
    currentCommands.add(command);
    System.out.println("addCommand(" + command + ")");
  }

  public void setFullScreenMode(boolean b) {

  }

  public int getWidth() {
    return impl.width;
  }

  public int getHeight() {
    return impl.height;
  }

  public void setCommandListener(CommandListener listener) {
    this.commandListener = listener;
  }

  public Set<Command> getCommands() {
    return currentCommands;
  }

  public String getKeyName(int key) {
    System.out.println("getKeyName at key " + key);
    return "FIRE";
  }
}
