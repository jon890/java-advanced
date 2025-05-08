package network.chat;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SessionManager {

    private SessionManager() {
        sessions = new ConcurrentLinkedQueue<>();
    }

    private static SessionManager INSTANCE;

    private final ConcurrentLinkedQueue<ChatSession> sessions;

    public static SessionManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SessionManager();
        }

        return INSTANCE;
    }

    public void addSession(ChatSession session) {
        sessions.add(session);
    }

    public void removeSession(ChatSession session) {
        sessions.remove(session);
    }

    public Queue<ChatSession> getSessions() {
        return this.sessions;
    }
}
