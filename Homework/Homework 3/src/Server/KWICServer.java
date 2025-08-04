package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class KWICServer implements AutoCloseable {
    private ServerSocket serverSocket;
    private final int port;

    public KWICServer(int port) {
        this.serverSocket = null;
        this.port = port;
    }

    public void start() throws IOException {
        this.serverSocket = new ServerSocket(port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
        }

    }

    @Override
    public void close() throws Exception {
        if (this.serverSocket != null) {
            this.serverSocket.close();
        }
    }

    public static void main(String[] args) {
    }
}
