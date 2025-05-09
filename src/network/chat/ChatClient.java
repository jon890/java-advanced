package network.chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static util.MyLogger.log;

public class ChatClient {

    public static void main(String[] args) throws IOException {
        try (final Socket socket = new Socket("localhost", ChatServer.PORT);
             final DataOutputStream output = new DataOutputStream(socket.getOutputStream());
             final InputStream input = socket.getInputStream();
             final Scanner scanner = new Scanner(System.in);
        ) {
            log("[ChatClient] 소켓 연결 : " + socket);

            // 서버에서 전달받은 메시지를 다른 스레드에서 처리
            ServerMessageProcessor serverMessageProcessor = new ServerMessageProcessor(input);
            new Thread(serverMessageProcessor).start();

            while (true) {
                String userInput = scanner.nextLine();
                if (userInput.startsWith("/exit")) {
                    break;
                }

                try {
                    output.writeUTF(userInput);
                    log("[ChatClient] 메시지 전송 => " + userInput);
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ServerMessageProcessor implements Runnable {
        private final DataInputStream input;

        public ServerMessageProcessor(InputStream input) {
            this.input = new DataInputStream(input);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String read = input.readUTF();
                    log(read);
                    Thread.sleep(100);
                } catch (EOFException e) {
                    // 읽을 메시지 없음.. 계속 처리
                } catch (IOException e) {
                    // 메시지 읽는 중 오류 발생, 스레드 종료해야함
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    // 스레드 오류 발생, 스레드 종료해야함
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
