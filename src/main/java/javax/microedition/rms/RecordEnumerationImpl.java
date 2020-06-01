package javax.microedition.rms;

import java.io.Serializable;
import java.util.ArrayList;

public class RecordEnumerationImpl implements RecordEnumeration, Serializable {

  private static final long serialVersionUID = 123;

  public final ArrayList<byte[]> data;

  private int currentPos = 0;

  public RecordEnumerationImpl() {
    this(new ArrayList<>());
  }

  public RecordEnumerationImpl(ArrayList<byte[]> data) {
    this.data = data;
  }

  public int numRecords() {
    return data.size();
  }

  public byte[] nextRecord() throws RecordStoreException {
    return data.get(currentPos++);
  }

  public int addRecord(byte[] bytes) {
    data.add(bytes);
    return data.size() - 1;
  }

  public void reset() {
    currentPos = 0;
  }

  public int nextRecordId() throws RecordStoreException {
    if (currentPos >= data.size()) {
      throw new RecordStoreException();
    }
    return currentPos;
  }

  public void destroy() {
    data.clear();
  }

  public void setRecord(int index, byte[] bytes) throws RecordStoreException {
    if (data.size() <= index) {
      throw new RecordStoreException();
    }
    data.set(index, bytes);
  }
}
