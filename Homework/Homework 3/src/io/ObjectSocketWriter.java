package io;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ObjectSocketWriter {
    private final Socket socket;

    public ObjectSocketWriter(Socket socket){
        this.socket = socket;
    }

    public void writeObject(Object obj) throws IOException {
        OutputStream outputStream = this.socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(obj);
    }
}
