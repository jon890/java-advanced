package io.text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReaderWriterMainV1 {

  public static void main(String[] args) throws IOException {
    String writeString = "ABC";
    byte[] writeBytes = writeString.getBytes(UTF_8);
    System.out.println("writeString = " + writeString);
    System.out.println("writeString bytes= " + Arrays.toString(writeBytes));

    // 파일에 쓰기
    final FileOutputStream fos = new FileOutputStream(FILE_NAME);
    fos.write(writeBytes);
    fos.close();

    // 파일 읽기
    FileInputStream fis = new FileInputStream(FILE_NAME);
    byte[] readBytes = fis.readAllBytes();
    fis.close();

    // byte -> String UTF-8 디코딩
    final String readString = new String(readBytes, UTF_8);
    System.out.println("readString = " + readString);
    System.out.println("readString bytes= " + Arrays.toString(readBytes));
  }

}
