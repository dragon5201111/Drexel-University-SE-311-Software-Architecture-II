package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KWICServer implements AutoCloseable {
    private final ExecutorService pool;
    private ServerSocket server;
    private final int port;
    private boolean acceptConnections;

    public KWICServer(int port) {
        this.server = null;
        this.port = port;
        this.pool = Executors.newCachedThreadPool();
        this.acceptConnections = true;
    }

    public void start() throws IOException {
        // Bind to localhost
        this.server = new ServerSocket(port);

        while (this.acceptConnections) {
            Socket clientSocket = server.accept();
            this.pool.execute(new ClientHandler(clientSocket));
            clientSocket.close();
        }

    }

    @Override
    public void close() throws Exception {
        if (this.server != null) {
            this.acceptConnections = false;
            this.server.close();
        }
    }

    public static void main(String[] args) {
        try (KWICServer kwicServer = new KWICServer(9090)){
            kwicServer.start();
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
}
