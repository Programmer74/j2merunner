package javax.microedition.midlet;

import java.util.HashMap;

public class PropertyStore {

  private static HashMap<String, String> map = new HashMap<>();

  public static void setAppProperty(String key, String value) {
    map.put(key, value);
  }

  public static String getAppProperty(final String key) {
    return map.get(key);
  }
}
