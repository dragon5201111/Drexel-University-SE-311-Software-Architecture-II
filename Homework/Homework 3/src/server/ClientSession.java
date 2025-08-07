package server;

import communication.KWICMessage;
import communication.KWICMethod;
import communication.SocketConnection;
import line.LineStorage;
import log.LogLevel;
import log.Logger;
import process.KeywordSearch;

import java.io.IOException;
import java.util.List;

public class ClientSession implements Runnable {
    private final SocketConnection connection;
    private final LineStorage lineStorage;
    private final KeywordSearch keywordSearch;

    public ClientSession(SocketConnection connection) throws IOException {
        this.connection = connection;
        this.lineStorage = new LineStorage(" ");
        this.keywordSearch = new KeywordSearch(lineStorage);
    }

    @Override
    public void run() {
        try {
            Logger logger = Logger.getLogger();


            while (true) {
                KWICMessage message = (KWICMessage) connection.receive();
                if (message == null) break;

                switch (message.getMethod()) {
                    case INPUT_LINES:
                        List<String> lines = message.getMessage();
                        lineStorage.setLines(lines);
                        connection.send(new KWICMessage(null, KWICMethod.RESPONSE));
                        break;

                    case KEYWORD_SEARCH:
                        String keyword = (String) connection.receive();
                        List<String> results = keywordSearch.getKeywordLines(keyword);
                        connection.send(new KWICMessage(results, KWICMethod.RESPONSE));
                        break;

                    case CLOSE:
                        connection.close();
                        return;

                    default:
                        System.err.println("Unknown method: " + message.getMethod());
                        break;
                }

                logger.log(LogLevel.INFO,  this.connection.getIP() + " " + message.getMethod());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Client disconnected or error: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (IOException ignored) {}
        }

    }
}

