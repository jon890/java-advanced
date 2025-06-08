package was.v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.nio.charset.StandardCharsets.UTF_8;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class HttpRequestHandlerV2 implements Runnable {

    private final Socket socket;

    public HttpRequestHandlerV2(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            process(socket);
        } catch (IOException e) {
            log(e);
        }
    }

    private void process(Socket socket) throws IOException {
        try (socket;
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), false, UTF_8)) {

            String requestString = requestToString(reader);
            if (requestString.contains("/favicon.ico")) {
                log("favicon 요청");
                return;
            }

            log("HTTP 요청 정보 출력");
            System.out.println(requestString);

            log("HTTP 응답 정보 생성 중..");
            sleep(5000);
            responseToClient(writer);
            log("HTTP 응답 전달 완료");
        }
    }

    private static String requestToString(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            sb.append(line).append("\n");
        }

        return sb.toString();
    }

    private void responseToClient(PrintWriter writer) {
        // 웹 브라우저에 전달

        String body = "<h1>Hello World!</h1>";
        int length = body.getBytes(UTF_8).length;

        StringBuilder sb = new StringBuilder();
        String carrageReturn = "\r\n";
        sb.append("HTTP/1.1 200 OK").append(carrageReturn)
                .append("Content-Type: text/html").append(carrageReturn)
                .append("Content-Length: ").append(length).append(carrageReturn)
                .append(carrageReturn) // 공백 라인 필요
                .append(body);

        log("HTTP 응답 정보 출력");
        System.out.println(sb);

        writer.println(sb);
        writer.flush();
    }
}
