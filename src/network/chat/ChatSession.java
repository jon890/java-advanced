package network.chat;

import network.tcp.SocketCloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import static util.MyLogger.log;

public class ChatSession implements Runnable {

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;

    private boolean initialized = false;
    private String nickname;


    public ChatSession(Socket socket) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        while (true) {
            try {
                String read = input.readUTF();
                log("[ChatSession] 클라이언트로부터 받은 메시지 : " + read);
                handleCommand(read);

                this.output.writeUTF("OK");
            } catch (Exception e) {
                e.printStackTrace();
                log("[ChatSession] 오류 발생 " + e);
                break;
            }
        }
    }

    private void handleCommand(String chat) {
        if (chat.startsWith("/join")) {
            // 입장 처리
            final String nickname = chat.split("\\|")[1];
            this.nickname = nickname;
            this.initialized = true;
            log(String.format("[ChatSession] 닉네임이 %s로 설정되었습니다", nickname));
        }
    }
}
