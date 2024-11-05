package thread.executor.future;

import static util.MyLogger.log;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SumTaskV2 {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    final SumTask sumTask1 = new SumTask(1, 50);
    final SumTask sumTask2 = new SumTask(51, 100);

    ExecutorService es = Executors.newFixedThreadPool(2);
    final Future<Integer> submit1 = es.submit(sumTask1);
    final Future<Integer> submit2 = es.submit(sumTask2);

    final Integer sum1 = submit1.get();
    final Integer sum2 = submit2.get();

    int sumAll = sum1 + sum2;
    log("작업 완료 sumAll = " + sumAll);
    es.close();
  }

  static class SumTask implements Callable<Integer> {

    int startValue;
    int endValue;

    public SumTask(int startValue, int endValue) {
      this.startValue = startValue;
      this.endValue = endValue;
    }

    @Override
    public Integer call() throws Exception {
      log("작업 시작");
      Thread.sleep(2000);
      int sum = 0;
      for (int i = startValue; i <= endValue; i++) {
        sum += i;
      }
      log("작업 완료 result = " + sum);

      return sum;
    }
  }

}
