package network.chat;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static util.MyLogger.log;

public class ChatClient {

    public static void main(String[] args) throws IOException {
        try (final Socket socket = new Socket("localhost", ChatServer.PORT);
             final DataInputStream input = new DataInputStream(socket.getInputStream());
             final DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        ) {
            log("[ChatClient] 소켓 연결 : " + socket);

            UserInputProcessor outputProcessor = new UserInputProcessor(socket);
            new Thread(outputProcessor).start();

            ServerMessageProcessor serverMessageProcessor = new ServerMessageProcessor(socket);
            new Thread(serverMessageProcessor).start();

            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static class UserInputProcessor implements Runnable {
        private final Socket socket;
        private final DataOutputStream output;

        public UserInputProcessor(Socket socket) throws IOException {
            this.socket = socket;
            this.output = new DataOutputStream(socket.getOutputStream());
        }

        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                String userInput = scanner.nextLine();
                if (userInput.startsWith("/exit")) {
                    break;
                }
                try {
                    output.writeUTF(userInput);
                    log("[ChatClient] 메시지 전송 => " + userInput);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class ServerMessageProcessor implements Runnable {
        private final Socket socket;
        private final DataInputStream input;

        public ServerMessageProcessor(Socket socket) throws IOException {
            this.socket = socket;
            this.input = new DataInputStream(socket.getInputStream());
        }

        @Override
        public void run() {
            while (true) {

            }
        }
    }
}
