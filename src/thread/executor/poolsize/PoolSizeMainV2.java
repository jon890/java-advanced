package thread.executor.poolsize;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import thread.executor.RunnableTask;

public class PoolSizeMainV2 {

  public static void main(String[] args) {
    final ExecutorService es = Executors.newFixedThreadPool(2);

    log("pool 생성");
    printState(es);

    for (int i = 1; i <=6 ; i++) {
      String taskName = "task" + i;
      es.execute(new RunnableTask(taskName));
      printState(es, taskName);
    }

    es.close();
  }
}
