package io;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ObjectSocketReader {
    private final Socket socket;

    public ObjectSocketReader(Socket socket){
        this.socket = socket;
    }

    Object readObject() throws IOException {
        InputStream inputStream = this.socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        return objectInputStream.read();
    }
}
