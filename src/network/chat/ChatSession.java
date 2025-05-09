package network.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

public class ChatSession implements Runnable {

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final SessionManager sessionManager;

    private boolean initialized = false;
    private String nickname;


    public ChatSession(Socket socket, SessionManager sessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager = sessionManager;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String read = input.readUTF();
                log("[ChatSession] 클라이언트로부터 받은 메시지 : " + read);
                handleCommand(read);
            } catch (Exception e) {
                e.printStackTrace();
                log("[ChatSession] 오류 발생 " + e);
                break;
            }
        }
    }

    public void sendMessage(String message) throws IOException {
        this.output.writeUTF(message);
    }

    private void handleCommand(String chat) throws IOException {
        if (chat.startsWith("/join")) {
            // 입장 처리
            final String nickname = chat.split("\\|")[1];
            this.nickname = nickname;
            this.initialized = true;

            final String message = String.format("[ChatServer] 닉네임이 %s로 설정되었습니다", nickname);
            sendMessage(message);
        } else if (chat.startsWith("/message")) {
            if (!initialized) {
                final String message = "[ChatServer] 닉네임이 아직 설정되지 않았어요. 닉네임을 먼저 설정해주세요";
                sendMessage(message);
                return;
            }

            // 메시지 전송
            final String message = chat.split("\\|")[1];

            for (ChatSession chatSession : sessionManager.getSessions()) {
                chatSession.sendMessage(String.format("[%s] : %s", nickname, message));
            }
        }
    }
}
