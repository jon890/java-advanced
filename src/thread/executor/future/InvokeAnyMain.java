package thread.executor.future;

import static util.MyLogger.log;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import thread.executor.CallableTask;

public class InvokeAnyMain {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    final ExecutorService executorService = Executors.newFixedThreadPool(10);

    CallableTask task1 = new CallableTask("task1", 1000);
    CallableTask task2 = new CallableTask("task2", 2000);
    CallableTask task3 = new CallableTask("task3", 3000);

    List<CallableTask> tasks = List.of(task1, task2, task3);
    final Integer value = executorService.invokeAny(tasks);
    log("value = " + value);


    executorService.close();

  }


}
