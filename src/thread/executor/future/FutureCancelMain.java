package thread.executor.future;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCancelMain {

//  private static boolean mayInterruptIfRunning = true;
  private static boolean mayInterruptIfRunning = false;

  public static void main(String[] args) {
    ExecutorService es = Executors.newFixedThreadPool(1);
    Future<String> future = es.submit(new MyTask());
    log("Future.state: " + future.state());

    // 일정 시간 후 취소 시도
    sleep(3000);

    // cancel() 호출
    log("future.cancel(" + mayInterruptIfRunning + ") 호출");
    final boolean cancel = future.cancel(mayInterruptIfRunning);
    log("cancel(" + mayInterruptIfRunning + ") result: " + cancel);

    // 결과 확인
    try {
      log("Future result: " + future.get());
    } catch (CancellationException e) {
      log("Future은 이미 취소 되었습니다.");
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }

  static class MyTask implements Callable<String> {

    @Override
    public String call() throws Exception {
      try {
        for (int i = 0; i < 10; i++) {
          log("작업 중: " + i);
          Thread.sleep(1000);
        }
        return "Completed";
      } catch (InterruptedException e) {
        log("인터럽트 발생");
        return "Interrupted";
      }
    }
  }
}
