package javax.microedition.media;

public interface Player {
  void prefetch();
  void start();
  void stop();
  int getState();
  void setLoopCount(int count);
  void addPlayerListener(PlayerListener pl);
}
