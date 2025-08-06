package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KWICServer {
    private final int port;
    private final ExecutorService clientPool;

    public KWICServer(int port) {
        this.port = port;
        this.clientPool = Executors.newCachedThreadPool();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("KWIC Server listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + clientSocket.getRemoteSocketAddress());

                ClientSession session = new ClientSession(clientSocket);
                clientPool.execute(session);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            clientPool.shutdown();
        }
    }

    public static void main(String[] args) {
        new KWICServer(9090).start();
    }
}

