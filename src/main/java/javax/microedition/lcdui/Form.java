package javax.microedition.lcdui;

import java.util.ArrayList;

public class Form {

  String a1;

  ArrayList<Item> items = new ArrayList<>();

  public Form(final String a1) {
    this.a1 = a1;
    System.out.println("FORM CONSTRUCTED " + a1);
  }

  public int append(Item item) {
    items.add(item);
    return 0;
  }
}
