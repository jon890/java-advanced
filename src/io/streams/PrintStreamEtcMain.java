package io.streams;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintStreamEtcMain {

  public static void main(String[] args) throws FileNotFoundException {
    final FileOutputStream fos = new FileOutputStream("temp/print.txt");
    final PrintStream ps = new PrintStream(fos);

    ps.println("hello Java!");
    ps.println(10);
    ps.println(true);
    ps.printf("hello %s", "world");
    ps.close();
  }

}
