package io.text;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReaderWriterMainV2 {

  public static void main(String[] args) throws IOException {
    final String writeString = "가나다";
    System.out.println("writeString = " + writeString);

    final FileOutputStream fos = new FileOutputStream(FILE_NAME);
    final OutputStreamWriter osw = new OutputStreamWriter(fos, UTF_8);

    osw.write(writeString);
    osw.close();

    final FileInputStream fis = new FileInputStream(FILE_NAME);
    final InputStreamReader isr = new InputStreamReader(fis, UTF_8);

    final StringBuilder sb = new StringBuilder();
    int ch;
    while ((ch = isr.read()) != -1) {
      sb.append((char) ch);
    }
    isr.close();

    System.out.println("readString = " + sb);
  }
}
