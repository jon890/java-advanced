package io.buffered;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.BUFFER_SIZE;
import static io.buffered.BufferedConst.FILE_NAME;
import static io.buffered.BufferedConst.FILE_SIZE;

public class CreateFileV3 {

  public static void main(String[] args) throws IOException {
    final FileOutputStream fos = new FileOutputStream(FILE_NAME);
    final BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER_SIZE);

    long startTime = System.currentTimeMillis();

    for (int i = 0; i < FILE_SIZE; i++) {
      bos.write(i);
    }

    bos.close();

    long endTime = System.currentTimeMillis();

    System.out.println("File created: " + FILE_NAME);
    System.out.println("File size: " + FILE_SIZE / 1024 / 1024 + "MB");
    System.out.println("File taken: " + (endTime - startTime) + "ms");
  }

}
