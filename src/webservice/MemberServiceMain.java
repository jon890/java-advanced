package webservice;

import io.member.impl.FileMemberRepository;
import was.httpserver.HttpServer;
import was.httpserver.ServletManager;
import was.httpserver.servlet.DiscardServlet;
import was.httpserver.servlet.annotation.AnnotationServletV3;

import java.io.IOException;
import java.util.List;

public class MemberServiceMain {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        FileMemberRepository fileMemberRepository = new FileMemberRepository();
        MemberController memberController = new MemberController(fileMemberRepository);

        AnnotationServletV3 servlet = new AnnotationServletV3(List.of(memberController));
        ServletManager servletManager = new ServletManager();
        servletManager.add("/favicon.ico", new DiscardServlet());
        servletManager.setDefaultServlet(servlet);

        HttpServer httpServer = new HttpServer(PORT, servletManager);
        httpServer.start();
    }
}
