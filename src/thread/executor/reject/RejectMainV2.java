
package thread.executor.reject;

import static util.MyLogger.log;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;
import thread.executor.RunnableTask;

public class RejectMainV2 {

  public static void main(String[] args) {
    final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,
        1,
        0,
        TimeUnit.SECONDS,
        new SynchronousQueue<>(),
        new DiscardPolicy());

    threadPoolExecutor.submit(new RunnableTask("task1"));
    threadPoolExecutor.submit(new RunnableTask("task2"));
    threadPoolExecutor.submit(new RunnableTask("task3"));
    threadPoolExecutor.submit(new RunnableTask("task4"));

    threadPoolExecutor.close();
  }

}
