package io.buffered;

import java.io.FileInputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.BUFFER_SIZE;
import static io.buffered.BufferedConst.FILE_NAME;
import static io.buffered.BufferedConst.FILE_SIZE;

public class ReadFileV2 {

  public static void main(String[] args) throws IOException {
    final FileInputStream fis = new FileInputStream(FILE_NAME);

    long startTime = System.currentTimeMillis();

    final byte[] buffer = new byte[BUFFER_SIZE];
    int fileSize = 0;
    int size;
    while((size = fis.read(buffer)) != -1) {
      fileSize += size;
    }
    fis.close();

    long endTime = System.currentTimeMillis();


    System.out.println("File name: " + FILE_NAME);
    System.out.println("File size: " + fileSize / 1024 / 1024 + "MB");
    System.out.println("File taken: " + (endTime - startTime) + "ms");
  }

}
