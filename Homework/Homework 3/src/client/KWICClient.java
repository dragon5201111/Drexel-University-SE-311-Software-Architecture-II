package client;

import java.io.IOException;
import java.net.Socket;

public class KWICClient implements AutoCloseable{
    private final String ip;
    private final int port;
    private Socket client;

    public KWICClient(String ip, int port) {
        this.client = null;
        this.ip = ip;
        this.port = port;
    }

    public void connect() throws IOException {
        this.client = new Socket(this.ip, this.port);
    }

    @Override
    public void close() throws Exception {
        if (this.client != null) {
            this.client.close();
        }
    }

    public static void main(String[] args) {
        try (KWICClient kwicClient = new KWICClient("127.0.0.1", 9090)){
            kwicClient.connect();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
