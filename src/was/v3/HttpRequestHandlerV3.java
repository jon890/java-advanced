package was.v3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class HttpRequestHandlerV3 implements Runnable {

    private final Socket socket;
    private static final String newLine = "\r\n";

    public HttpRequestHandlerV3(Socket socket) {
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
            if (requestString.startsWith("GET /site1")) {
                site1(writer);
            } else if (requestString.startsWith("GET /site2")) {
                site2(writer);
            } else if (requestString.startsWith("GET /search")) {
                search(writer, requestString);
            } else if (requestString.startsWith("GET / ")) {
                home(writer);
            } else {
                notFound(writer);
            }

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

    private void site1(PrintWriter writer) {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK").append(newLine)
                .append("Content-Type: text/html; charset=UTF-8").append(newLine)
                .append(newLine) // 공백 라인 필요
                .append("<h1>Site1</h1>");

        writer.println(sb);
        writer.flush();
    }

    private void site2(PrintWriter writer) {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK").append(newLine)
                .append("Content-Type: text/html; charset=UTF-8").append(newLine)
                .append(newLine) // 공백 라인 필요
                .append("<h1>Site2</h1>");

        writer.println(sb);
        writer.flush();
    }


    private void search(PrintWriter writer, String requestString) {
        int startIndex = requestString.indexOf("q=");
        int endIndex = requestString.indexOf(" ", startIndex + 2);

        String query = requestString.substring(startIndex + 2, endIndex);
        String decode = URLDecoder.decode(query, UTF_8);

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK").append(newLine)
                .append("Content-Type: text/html; charset=UTF-8").append(newLine)
                .append(newLine) // 공백 라인 필요
                .append("<h1>Search</h1>")
                .append("<ul>")
                .append("<li>query : ").append(query).append("</li>")
                .append("<li>decode : ").append(decode).append("</li>")
                .append("</ul>");

        writer.println(sb);
        writer.flush();
    }


    private void home(PrintWriter writer) {
        // 원칙적으로 Content-Length를 계산해서 전달해야 하지만, 예제를 단순하게 하기 위해 생략하겠다.
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK").append(newLine)
                .append("Content-Type: text/html; charset=UTF-8").append(newLine)
                .append(newLine) // 공백 라인 필요
                .append("<h1>Home</h1>")
                .append("<ul>")
                .append("<li><a href='/site1'>site1</a></li>")
                .append("<li><a href='/site2'>site2</a></li>")
                .append("<li><a href='/search?q=hello'>search</a></li>")
                .append("</ul>");

        writer.println(sb);
        writer.flush();
    }

    private void notFound(PrintWriter writer) {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 404 OK").append(newLine)
                .append("Content-Type: text/html; charset=UTF-8").append(newLine)
                .append(newLine) // 공백 라인 필요
                .append("<h1>페이지를 찾을 수 없습니다.</h1>");

        writer.println(sb);
        writer.flush();
    }
}
