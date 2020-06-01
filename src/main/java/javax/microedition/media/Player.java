package javax.microedition.media;

public interface Player {
  void prefetch();
  void start();
  void stop();
  int getState();
}
