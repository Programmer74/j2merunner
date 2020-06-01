package javax.microedition.lcdui;

import com.programmer74.j2merunner.CanvasImpl;
import java.util.HashSet;

public abstract class Canvas extends Displayable {

  public static final int UP = 1;

  public static final int DOWN = 6;

  public static final int LEFT = 2;

  public static final int RIGHT = 5;

  public static final int FIRE = 8;

  public final CanvasImpl impl;

  private final HashSet<Command> currentCommands = new HashSet<>();

  private CommandListener commandListener;

  public Canvas() {
    impl = new CanvasImpl(this);
  }

  public void repaint() {
    impl.repaint();
  }

  public void serviceRepaints() {
    // todo waiting repaint finish
  }

  public void pressedEsc() {
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

  public void removeCommand(Command command) {
    currentCommands.remove(command);
    System.out.println("removeCommand(" + command + ")");
  }

  public void addCommand(Command command) {
    currentCommands.add(command);
    System.out.println("addCommand(" + command + ")");
  }

  public int getWidth() {
    return impl.width;
  }

  public int getHeight() {
    return impl.height;
  }

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
  public int getGameAction(int keyCode) {
    switch (keyCode) {
      case UP:
      case DOWN:
      case LEFT:
      case RIGHT:
      case FIRE:
        return keyCode;
      default:
        throw new RuntimeException("getGameAction(" + keyCode + ") isn't implemented!");
    }
  }

  public void setCommandListener(CommandListener listener) {
    this.commandListener = listener;
  }
}
