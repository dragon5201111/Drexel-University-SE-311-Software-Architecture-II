package server;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket client;
    public ClientHandler (Socket client){
        this.client = client;
    }

    @Override
    public void run() {
        System.out.println("Handling client.");
    }
}
