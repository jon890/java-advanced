package thread.start.solve;

import static util.MyLogger.log;

public class Problem4 {

  public static void main(String[] args) {
    Thread threadA = new Thread(new CounterRunnable(1_000), "Thread-A");
    Thread threadB = new Thread(new CounterRunnable(500), "Thread-B");

    threadA.start();
    threadB.start();
  }

  static class CounterRunnable implements Runnable {

    private final int duration;

    CounterRunnable(int duration) {
      this.duration = duration;
    }

    @Override
    public void run() {
      try {
        int i = 1;
        while (true) {
          log("value: " + i++);
          Thread.sleep(duration);
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
