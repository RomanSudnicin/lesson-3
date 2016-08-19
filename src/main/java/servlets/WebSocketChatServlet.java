package servlets;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Created by roman on 18.08.16.
 */
@WebServlet(name = "WebSocketChatServlet", urlPatterns = {"/chat"})
public class WebSocketChatServlet extends WebSocketServlet {
    private final static int LOGOUT_TIME = 10 * 60 * 1000;


    @Override
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        webSocketServletFactory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        webSocketServletFactory.setCreator((req, resp) -> new MyWebSocet());
    }

    @SuppressWarnings("UnusedDeclaration")
    @WebSocket
    public class MyWebSocet {
        private Session session;

        @OnWebSocketConnect
        public void onOpen(Session session) {
            this.session = session;
        }

        @OnWebSocketMessage
        public void onMessage(String data) {
            try {
                session.getRemote().sendString(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @OnWebSocketClose
        public void onClose(int statusCode, String reason) {
        }
    }
}
