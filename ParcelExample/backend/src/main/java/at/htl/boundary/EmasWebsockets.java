package at.htl.boundary;

import at.htl.model.Student;
import io.vertx.core.json.Json;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
@ServerEndpoint("/websocket/")
public class EmasWebsockets {

    Map<String, Session> sessionMap = new ConcurrentHashMap<>();


    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected ... " + session.getId());
        sessionMap.put(session.getId(), session);
        //session.getAsyncRemote().sendObject("asd");

    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Message from " + session.getId() + ": " + message);

    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println(String.format("Session %s close because of %s", session.getId(), closeReason));
        sessionMap.remove(session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Error: " + throwable.getMessage());
    }


    /****
     *   public void broadcastmsg(String message) {
     *         // Nachricht schicken
     *         for (Session session : sessionMap.values()) {
     *             session.getAsyncRemote().sendObject(message);
     *         }
     *         // JSON Objekt schicken
     *     }
     * @param message
     */


    public void broadcastOBJ(Student message) {

        for (Session session : sessionMap.values()) {
            session.getAsyncRemote().sendObject(Json.encode(message));
        }
    }
}
