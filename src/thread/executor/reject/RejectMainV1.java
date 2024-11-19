package thread.executor.reject;

import static util.MyLogger.log;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;
import thread.executor.RunnableTask;

public class RejectMainV1 {

  public static void main(String[] args) {
    final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,
        1,
        0,
        TimeUnit.SECONDS,
        new SynchronousQueue<>(),
        new AbortPolicy());

    threadPoolExecutor.submit(new RunnableTask("task1"));

    try {
      threadPoolExecutor.submit(new RunnableTask("task1"));
    } catch (RejectedExecutionException e) {
      log("요청 초과");
      // 포기, 다시 시도 등 다양한 고민을 하면 됨
      log(e);
    }


    threadPoolExecutor.close();
  }

}
