package communication;

import java.io.*;
import java.net.Socket;

public class SocketConnection implements AutoCloseable {
    private final Socket socket;
    private final ObjectInputStream input;
    private final ObjectOutputStream output;

    public SocketConnection(Socket socket) throws IOException {
        this.socket = socket;
        this.output = new ObjectOutputStream(socket.getOutputStream());
        this.output.flush();
        this.input = new ObjectInputStream(socket.getInputStream());
        this.socket.setSoTimeout(30000);
    }

    public synchronized void send(Object obj) throws IOException {
        output.writeObject(obj);
        output.flush();
    }

    public synchronized Object receive() throws IOException, ClassNotFoundException {
        return input.readObject();
    }

    public synchronized String getIP() {
        return socket.getInetAddress().getHostAddress()  + ":" + socket.getPort();
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }
}
