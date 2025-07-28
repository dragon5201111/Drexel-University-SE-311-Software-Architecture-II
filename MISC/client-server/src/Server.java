import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements AutoCloseable{
    private final ServerSocket serverSocket;

    public Server(int maxConnections, int port, String ipAddress) throws Exception {
        this.serverSocket = new ServerSocket(port, maxConnections, InetAddress.getByName(ipAddress));
    }

    public void handleClient(Socket client) {
        try {
            System.out.println("Client connected.");
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("Client [" + client.getInetAddress().getHostAddress() + "]: " + in.readLine());

            client.close();
        }catch(Exception e) {
            throw new RuntimeException();
        }
    }

    public void start() throws Exception {
        while(!this.serverSocket.isClosed()){
            Socket client = this.serverSocket.accept();

            Thread clientThread = new Thread(()->
                this.handleClient(client)
            );

            clientThread.start();
        }
    }


    @Override
    public void close() throws Exception {
        this.serverSocket.close();
        System.out.println("Server closed.");
    }
}
