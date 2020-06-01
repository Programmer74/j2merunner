package javax.bluetooth;

public class DiscoveryAgent {
  public void startInquiry(int i, Object o) {
    System.out.println("DiscoveryAgent startInquiry");
  }

  public int searchServices(int[] ints, UUID[] auuid, RemoteDevice remoteDevice, Object o) {
    System.out.println("DiscoveryAgent searchServices");
    return 0;
  }

  public void cancelServiceSearch(final int i) {
    System.out.println("DiscoveryAgent cancelServiceSearch");
  }

  public void cancelInquiry(final Object o) {
    System.out.println("DiscoveryAgent cancelInquiry");
  }
}
