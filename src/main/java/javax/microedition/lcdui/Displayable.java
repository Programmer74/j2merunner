package javax.microedition.lcdui;

public abstract class Displayable {

  public abstract boolean isShown();

  public abstract void removeCommand(Command command);

  public abstract void addCommand(Command command);

  public abstract int getWidth();

  public abstract int getHeight();

  public abstract void setCommandListener(CommandListener listener);
}
