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
    System.exit(0);
  }

  public void platformRequest(final String rq) {
    System.out.println("PLATFORM REQUEST AT " + rq);
  }

  public String getAppProperty(final String key) {
    System.out.println("GET APP PROPERTY " + key);
    return PropertyStore.getAppProperty(key);
  }
}
