package server;

import communication.KWICMessage;
import communication.KWICMethod;
import communication.SocketConnection;
import io.Input;
import line.LineStorage;
import log.LogLevel;
import log.Logger;
import object.OptionReader;
import process.KeywordSearch;

import java.io.IOException;
import java.util.List;


public class ClientSession implements Runnable {
    private final SocketConnection connection;
    private final LineStorage lineStorage;
    private final KeywordSearch keywordSearch;

    public ClientSession(SocketConnection connection) throws IOException {
        this.connection = connection;
        OptionReader.readOptions("config.properties");
        this.lineStorage = getLinePopulatedStorage();
        this.keywordSearch = new KeywordSearch(lineStorage);
    }

    @Override
    public void run() {
        int searches = 0;
        int successfulRequests = 0;
        try {
            Logger logger = Logger.getLogger();


            while (true) {
                KWICMessage message = (KWICMessage) connection.receive();
                if (message == null) break;

                switch (message.getMethod()) {
                    case KEYWORD_SEARCH:
                        String keyword = (String) connection.receive();
                        List<String> results = keywordSearch.getKeywordLines(keyword);
                        if (!results.isEmpty()) {
                            successfulRequests++;
                        }
                        connection.send(new KWICMessage(results, KWICMethod.RESPONSE));
                        break;

                    case CLOSE:
                        connection.close();
                        return;

                    default:
                        System.err.println("Unknown method: " + message.getMethod());
                        break;
                }

                searches++;
                logger.log(LogLevel.INFO,  this.connection.getIP() + " " + message.getMethod()
                        + " Successful Requests: "
                        + successfulRequests
                + " Total Searches: " + searches);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Client disconnected or error: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (IOException ignored) {}
        }


    }

    private LineStorage getLinePopulatedStorage() {
        Input input = (Input) OptionReader.getObjectFromStr(OptionReader.getString("Input"));
        String inputFileName = OptionReader.getString("InputFileName");
        LineStorage lineStorage = new LineStorage();
        input.readLines(inputFileName, lineStorage);
        return lineStorage;
    }
}

