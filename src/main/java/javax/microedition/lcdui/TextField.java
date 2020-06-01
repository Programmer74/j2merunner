package javax.microedition.lcdui;

public class TextField extends Item {
  String a1;

  String a2;

  int x;

  int y;

  public TextField(final String a1, final String a2, final int x, final int y) {
    this.a1 = a1;
    this.a2 = a2;
    this.x = x;
    this.y = y;
    System.out.println("TextField CONSTRUCTED " + a1 + " " + a2 + " " + x + " " + y);
  }

  @Override
  public void addCommand(final Command cmd) {
    System.out.println("TextField COMMAND ADDED");
  }

  @Override
  public void setItemCommandListener(final ItemCommandListener icl) {
    System.out.println("TextField setItemCommandListener");
  }
}
