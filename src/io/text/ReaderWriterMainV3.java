package io.text;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

// file output stream + output stream writer => file writer
public class ReaderWriterMainV3 {

  public static void main(String[] args) throws IOException {
    String writeString = "abc";
    System.out.println("writeString = " + writeString);

    // 파일에 쓰기
    final FileWriter fw = new FileWriter(FILE_NAME, UTF_8);
    fw.write(writeString);
    fw.close();

    // 파일에서 읽기
    StringBuilder content = new StringBuilder();
    FileReader fr = new FileReader(FILE_NAME, UTF_8);
    int ch;
    while ((ch = fr.read()) != -1) {
      content.append((char) ch);
    }
    System.out.println("readString = " + content);
    fr.close();
  }

}
