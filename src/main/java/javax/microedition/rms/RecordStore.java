package javax.microedition.rms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class RecordStore {
  private static final String recordStoreDir = "recordStore";

  private static final HashMap<String, RecordStore> opened = new HashMap<>();

  private final File file;

  private final RecordEnumerationImpl records;

  private RecordStore(File file, RecordEnumerationImpl records) throws RecordStoreException {
    assert (file.exists());
    this.file = file;
    this.records = records;
  }

  public RecordEnumeration enumerateRecords(
      RecordFilter filter,
      RecordComparator comparator,
      boolean keepUpdated
  )
  throws RecordStoreNotOpenException {
    assert (filter == null);
    assert (comparator == null);
    assert (!keepUpdated);
    log("enumerateRecords()");
    return records;
  }

  private static void log(String s) {
    System.out.println("RecordStore." + s);
  }

  public void closeRecordStore() throws RecordStoreException {
    // nothing
  }

  public int addRecord(byte[] arr, int offset, int numBytes) throws RecordStoreException {
    log("addRecord()");
    assert (arr.length == numBytes);
    assert (offset == 0);
    int id = records.addRecord(arr);
    save();
    return id;
  }

  private void save() throws RecordStoreException {
    try (
        FileOutputStream fout = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
    ) {
      oos.writeObject(records);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RecordStoreException();
    }
  }

  public void deleteRecord(final int nextRecordId) {

  }

  public void setRecord(int recordId, byte[] arr, int offset, int numBytes)
  throws RecordStoreException {
    records.setRecord(recordId, arr);
    save();
  }

  public byte[] getRecord(int recordId)
  throws RecordStoreException {
    return records.data.get(recordId);
  }


  public int getNumRecords() {
    return this.records.numRecords();
  }

  public static RecordStore openRecordStore(String name, boolean createIfNecessary)
  throws RecordStoreException {
    if (!opened.containsKey(name)) {
      opened.put(name, createRecordStore(name, createIfNecessary));
    }
    return opened.get(name);
  }

  private static RecordStore createRecordStore(String name, boolean createIfNecessary)
  throws RecordStoreException {
    log("createRecordStore(" + name + ", " + createIfNecessary + ")");
    File file = new File(recordStoreDir + "/" + name);

    if (file.exists()) {
      return new RecordStore(file, load(file));
    }

    if (createIfNecessary) {
      try {
        file.getParentFile().mkdirs();
        file.createNewFile();
        RecordStore rs = new RecordStore(file, new RecordEnumerationImpl());
        rs.save();
        return rs;
      } catch (IOException e) {
        RecordStoreException ex = new RecordStoreException();
        ex.addSuppressed(e);
        throw ex;
      }
    } else {
      throw new RecordStoreException();
    }
  }

  private static RecordEnumerationImpl load(File file) {
    try (
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
    ) {
      return (RecordEnumerationImpl) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  public static String[] listRecordStores() {
    File[] files = new File(recordStoreDir).listFiles();
    String[] result = new String[files.length];
    for (int i = 0; i < result.length; ++i) {
      result[i] = files[i].getName();
    }
    System.out.println("listRecordStores() = {" + String.join(", ", result) + "}");
    return result;
  }

  public static void deleteRecordStore(String name) throws RecordStoreException {
    log("deleteRecordStore(" + name + ")");
    throw new RuntimeException();
  }
}
