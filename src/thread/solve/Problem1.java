package thread.solve;

import static util.MyLogger.log;

public class Problem1 {

  public static void main(String[] args) {
    CounterThread counterThread = new CounterThread();
    counterThread.start();
  }

  static class CounterThread extends Thread {

    @Override
    public void run() {
      try {
        for (int i = 0 ; i < 5; i++) {
          log("value: " + (i + 1));
          Thread.sleep(1_000);
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

}
