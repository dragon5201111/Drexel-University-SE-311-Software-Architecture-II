import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements AutoCloseable{
    private final Socket server;
    public Client(String ip, int port) throws IOException {
        this.server = new Socket(ip, port);
    }

    public void send(String message) throws IOException {
        PrintWriter out = new PrintWriter(server.getOutputStream(), true);
        out.println(message);
        System.out.println("Sent message [" + message + "] to " + server.getInetAddress().getHostAddress() + ":" + server.getPort());
    }

    @Override
    public void close() throws Exception {
        this.server.close();
        System.out.println("Client closed.");
    }
}
