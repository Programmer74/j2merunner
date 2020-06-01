package javax.microedition.lcdui;

import java.util.Objects;

public class Command {

  // https://docs.oracle.com/javame/config/cldc/ref-impl/midp2.0/jsr118/javax/microedition/lcdui/Command.html

  public static final int SCREEN = 1;

  public static final int BACK = 2;

  public static final int CANCEL = 3;

  public static final int OK = 4;

  public static final int HELP = 5;

  public static final int STOP = 6;

  public static final int EXIT = 7;

  public static final int ITEM = 8;

  public final String name;

  public final int type;

  public final int priority;

  public Command(String name, int type, int priority) {
    this.name = name;
    this.type = type;
    this.priority = priority;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Command command = (Command) o;
    return type == command.type &&
        priority == command.priority &&
        Objects.equals(name, command.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type, priority);
  }

  @Override
  public String toString() {
    return "Command(\"" + name + "\", " + type + ", " + priority + ")";
  }
}
