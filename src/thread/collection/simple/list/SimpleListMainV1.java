package thread.collection.simple.list;

public class SimpleListMainV1 {

  public static void main(String[] args) {
    SimpleList list = new BasicList();

    list.add("A"); // 스레드 실행 가정
    list.add("B"); // 스레드 실행 가정
    System.out.println(list);
  }

}
