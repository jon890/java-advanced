package thread.start;

public class HelloThreadMain {

  public static void main(String[] args) {
    System.out.println(Thread.currentThread().getName() + ": main() started");

    HelloThread helloThread = new HelloThread();
    System.out.println(Thread.currentThread().getName() + ": start() 호출 전");
    // main 스레드는 다른 스레드를 시작하라고 명령하고 해당 스레드가 수행될떄까지 기다리지 않는다
    helloThread.start();
    System.out.println(Thread.currentThread().getName() + ": start() 호출 후");

    System.out.println(Thread.currentThread().getName() + ": main() end");
  }

}
