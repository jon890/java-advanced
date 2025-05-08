package network.chat;

import network.tcp.SocketCloseUtil;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static util.MyLogger.log;

public class ChatClient {

    public static void main(String[] args) throws IOException {
        try (final Socket socket = new Socket("localhost", ChatServer.PORT);
             final DataInputStream input = new DataInputStream(socket.getInputStream());
             final DataOutputStream output = new DataOutputStream(socket.getOutputStream());
             final Scanner scanner = new Scanner(System.in);
        ) {
            log("[ChatClient] 소켓 연결 : " + socket);

            while (true) {
                String userInput = scanner.nextLine();
                output.writeUTF(userInput);
                log("[ChatClient] 메시지 전송 => " + userInput);

                if (userInput.startsWith("/exit")) {
                    break;
                }

                String serverSend = input.readUTF();
                log("[ChatClient] 서버 전송 : " + serverSend);
            }
        }
    }
}
