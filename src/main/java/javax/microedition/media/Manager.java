package javax.microedition.media;

import java.io.InputStream;

public class Manager {
  public static Player createPlayer(InputStream is, String string) {
    System.out.println("createPlayer at " + string);
    return new DummyPlayer();
  }
}
