package io.buffered;

import java.io.FileInputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.FILE_NAME;
import static io.buffered.BufferedConst.FILE_SIZE;

public class ReadFileV4 {

  public static void main(String[] args) throws IOException {
    final FileInputStream fis = new FileInputStream(FILE_NAME);

    long startTime = System.currentTimeMillis();

    final byte[] bytes = fis.readAllBytes();
    fis.close();

    long endTime = System.currentTimeMillis();


    System.out.println("File name: " + FILE_NAME);
    System.out.println("File size: " + bytes.length/ 1024 / 1024 + "MB");
    System.out.println("File taken: " + (endTime - startTime) + "ms");
  }

}
