package client;

import communication.KWICMessage;
import communication.KWICMethod;
import communication.SocketConnection;

import java.io.IOException;
import java.util.List;

public class ClientHandler {
    private final SocketConnection connection;

    public ClientHandler(SocketConnection connection) {
        this.connection = connection;
    }

    public List<String> searchKeyword(String keyword) throws IOException, ClassNotFoundException {
        connection.send(new KWICMessage(null, KWICMethod.KEYWORD_SEARCH));
        connection.send(keyword);
        KWICMessage response = receiveResponse();
        return response.getMessage();
    }

    public void close() throws IOException {
        connection.send(new KWICMessage(null, KWICMethod.CLOSE));
        connection.close();
    }

    private KWICMessage receiveResponse() throws IOException, ClassNotFoundException {
        KWICMessage response = (KWICMessage) connection.receive();
        if (response.getMethod() != KWICMethod.RESPONSE) {
            throw new IOException("Expected RESPONSE, got: " + response.getMethod());
        }
        return response;
    }
}
