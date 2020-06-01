package javax.bluetooth;

public class LocalDevice {

  public DiscoveryAgent getDiscoveryAgent() {
    return new DiscoveryAgent();
  }

  private int discoverable = 0;

  public boolean setDiscoverable(final int i) {
    discoverable = i;
    return true;
  }

  public static LocalDevice getLocalDevice() {
    return new LocalDevice();
  }

  public DeviceClass getDeviceClass() {
    return new DeviceClass();
  }

  public int getDiscoverable() {
    return discoverable;
  }
}
