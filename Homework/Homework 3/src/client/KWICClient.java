package client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;

import communication.SocketConnection;

public class KWICClient implements AutoCloseable {
    private final SocketConnection connection;
    private final ClientHandler handler;

    public KWICClient(String ip, int port) throws IOException {
        Socket socket = new Socket(ip, port);
        this.connection = new SocketConnection(socket, true);
        this.handler = new ClientHandler(connection);
    }

    public void run() throws IOException, ClassNotFoundException {

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("Enter keyword to search or 'exit' to quit: ");
            String keyword = consoleReader.readLine();

            if ("exit".equalsIgnoreCase(keyword)){
                break;
            }

            List<String> results = handler.searchKeyword(keyword);

            String searchResult = results.isEmpty()
                    ? String.format("[%s] keyword not found", keyword)
                    : String.format("%d sentence(s) found", results.size());

            System.out.println(searchResult);
            results.forEach(System.out::println);
        }

        handler.close();
    }

    @Override
    public void close() throws IOException {
        connection.close();
    }

    public static void main(String[] args) {
        try (KWICClient client = new KWICClient("127.0.0.1", 9090)) {
            client.run();
        }catch (SocketTimeoutException e){
            System.out.println("The KWIC server is not responding.");
            System.out.println("Exiting...");
        }
        catch (Exception e) {
           throw new RuntimeException(e);
        }
    }
}
