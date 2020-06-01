package javax.microedition.lcdui;

public class StringItem extends Item {
  @Override
  public void addCommand(final Command cmd) {
    System.out.println("StringItem COMMAND ADDED");
  }

  @Override
  public void setItemCommandListener(final ItemCommandListener icl) {
    System.out.println("StringItem setItemCommandListener");
  }
}
