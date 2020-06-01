package javax.microedition.midlet;

public abstract class MIDlet {
  public MIDlet() {
  }

  public void runApp() {
    startApp();
  }

  protected void startApp() {
  }

  protected void pauseApp() {
  }

  public void notifyDestroyed() {
    System.out.println("notifyDestroyed()");
  }
}
