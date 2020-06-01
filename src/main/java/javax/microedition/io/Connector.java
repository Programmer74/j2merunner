package javax.microedition.io;

import javax.bluetooth.L2CAPConnection;

public class Connector {
  public static L2CAPConnection open(final String url) {
    return new L2CAPConnection();
  }
}
