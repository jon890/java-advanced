package thread.executor;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorBasicMain {

  public static void main(String[] args) {
    ExecutorService executorService = new ThreadPoolExecutor(2,
        2,
        0,
        TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>());
    log("== 초기 상태 ==");
    printState(executorService);

    executorService.execute(new RunnableTask("taskA"));
    executorService.execute(new RunnableTask("taskB"));
    executorService.execute(new RunnableTask("taskC"));
    executorService.execute(new RunnableTask("taskD"));

    log("== 작업 수행 중 ==");
    printState(executorService);

    sleep(3000);
    log("== 작업 수행 완료 ==");
    printState(executorService);

    executorService.close();
    log("== shutdown 완료 ==");
    printState(executorService);
  }

}
