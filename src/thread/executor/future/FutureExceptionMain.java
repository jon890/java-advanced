package thread.executor.future;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExceptionMain {

  public static void main(String[] args) {
    ExecutorService es = Executors.newFixedThreadPool(1);
    log("작업 전달");
    final Future<Integer> submit = es.submit(new ExCallable());
    sleep(1000); // 잠시 대기

    try {

      log("future.get() 호출 시도, future.state(): " + submit.state());
      final Integer result = submit.get();
      log("result value = " + result);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } catch (ExecutionException e) {
      log("e = " + e);
      final Throwable cause = e.getCause();
      log("cause = " + cause);
    }
  }

  static class ExCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
      log("Callable 실행, 예외 발생");
      throw new IllegalStateException("ex!");
    }
  }

}
