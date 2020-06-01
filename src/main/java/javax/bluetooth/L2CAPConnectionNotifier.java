package javax.bluetooth;

import java.io.Closeable;

public interface L2CAPConnectionNotifier extends Closeable {

  L2CAPConnection acceptAndOpen();
}
