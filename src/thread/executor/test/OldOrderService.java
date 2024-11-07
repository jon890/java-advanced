package thread.executor.test;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class OldOrderService {

  public void order(String orderNo) throws ExecutionException, InterruptedException {
    ExecutorService es = Executors.newFixedThreadPool(3);

    InventoryWork inventoryWork = new InventoryWork(orderNo);
    ShippingWork shippingWork = new ShippingWork(orderNo);
    AccountingWork accountingWork = new AccountingWork(orderNo);

    final Future<Boolean> inventoryResultFuture = es.submit(inventoryWork);
    final Future<Boolean> shippingResultFuture = es.submit(shippingWork);
    final Future<Boolean> accountingResultFuture = es.submit(accountingWork);

    final Boolean inventoryResult = inventoryResultFuture.get();
    final Boolean shippingResult = shippingResultFuture.get();
    final Boolean accountingResult = accountingResultFuture.get();

    // 결과 확인
    if (inventoryResult && shippingResult && accountingResult) {
      log("모든 주문 처리가 성공적으로 완료되었습니다.");
    } else {
      log("일부 작업이 실패했습니다.");
    }

    es.close();
  }

  static class InventoryWork implements Callable<Boolean> {

    private final String orderNo;

    public InventoryWork(String orderNo) {
      this.orderNo = orderNo;
    }

    @Override
    public Boolean call() {
      log("재고 업데이트: " + orderNo);
      sleep(1000);
      return true;
    }
  }

  static class ShippingWork implements Callable<Boolean> {

    private final String orderNo;

    public ShippingWork(String orderNo) {
      this.orderNo = orderNo;
    }

    @Override
    public Boolean call() {
      log("배송 시스템 알림: " + orderNo);
      sleep(1000);
      return true;
    }
  }

  static class AccountingWork implements Callable<Boolean> {

    private final String orderNo;

    public AccountingWork(String orderNo) {
      this.orderNo = orderNo;
    }

    @Override
    public Boolean call() {
      log("회계 시스템 업데이트: " + orderNo);
      sleep(1000);
      return true;
    }
  }

}