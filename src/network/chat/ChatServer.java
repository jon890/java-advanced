package network.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

/**
 * 요구 사항은 다음과 같다.
 * 서버에 접속한 사용자는 모두 대화할 수 있어야 한다.
 * 다음과 같은 채팅 명령어가 있어야 한다.
 * 입장 /join|{name}
 * 메시지 /message|{내용}
 * 이름 변경 /change|{name}
 * 전체 사용자 /users
 * 종료 /exit
 */
public class ChatServer {

    public static int PORT = 12345;

    public static void main(String[] args) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(12345);
        final SessionManager sessionManager = SessionManager.getInstance();

        while (true) {
            try {
                log("[ChatServer] 연결 대기 중");

                Socket socket = serverSocket.accept();
                log("[ChatServer] 클라이언트 연결 : " + socket);

                ChatSession session = new ChatSession(socket, sessionManager);

                sessionManager.addSession(session);
                new Thread(session).start();
            } catch (Exception e) {
                break;
            }
        }
    }
}
