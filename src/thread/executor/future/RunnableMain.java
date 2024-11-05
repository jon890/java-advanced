package thread.executor.future;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.Random;

public class RunnableMain {

  public static void main(String[] args) throws InterruptedException {
    MyRunnable myRunnable = new MyRunnable();
    Thread thread = new Thread(myRunnable);
    thread.start();
    thread.join();
    int result = myRunnable.value;
    log("result value = " + result);
  }

  static class MyRunnable implements Runnable {

    int value;

    @Override
    public void run() {
      log("Runnable 시작");
      value = new Random().nextInt(10);
      sleep(2000);
      log("Runnable 완료");
      log("result value = " + value);
    }
  }

}
