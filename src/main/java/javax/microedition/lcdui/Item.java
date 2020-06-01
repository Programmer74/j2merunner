package javax.microedition.lcdui;

public abstract class Item {
  public abstract void addCommand(Command cmd);

  public abstract void setItemCommandListener(ItemCommandListener icl);
}
