package client;

import communication.KWICMessage;
import communication.KWICMethod;
import communication.SocketConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class ClientHandler {
    private final SocketConnection connection;

    public ClientHandler(SocketConnection connection) {
        this.connection = connection;
    }

    public void sendInitialLines() throws IOException, ClassNotFoundException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter lines (empty line to finish):");
        List<String> lines = consoleReader.lines()
                .takeWhile(line -> !line.isEmpty())
                .collect(Collectors.toList());

        this.sendLines(lines);
    }

    public void sendLines(List<String> lines) throws IOException, ClassNotFoundException {
        connection.send(new KWICMessage(lines, KWICMethod.INPUT_LINES));
        // Acknowledge response
        receiveResponse();
        System.out.println("Server acknowledged lines.");
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
