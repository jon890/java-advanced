package thread.solve;

import static util.MyLogger.log;

public class Problem2 {

  public static void main(String[] args) {
    CounterRunnable counterRunnable = new CounterRunnable();
    Thread thread = new Thread(counterRunnable, "counter");
    thread.start();
  }

  static class CounterRunnable implements Runnable {

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
