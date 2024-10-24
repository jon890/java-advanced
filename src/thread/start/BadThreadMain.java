package thread.start;

public class BadThreadMain {

  public static void main(String[] args) {
    System.out.println(Thread.currentThread().getName() + ": main() started");

    HelloThread helloThread = new HelloThread();
    System.out.println(Thread.currentThread().getName() + ": start() 호출 전");
    // 직접 실행하면 이 스레드가 실행한다
    helloThread.run();
    System.out.println(Thread.currentThread().getName() + ": start() 호출 후");

    System.out.println(Thread.currentThread().getName() + ": main() end");
  }

}
