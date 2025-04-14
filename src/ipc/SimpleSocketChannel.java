package ipc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleSocketChannel implements IPCChannel {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public SimpleSocketChannel(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void send(String message) {
        out.println(message);
    }

    @Override
    public String receive() {
        try {
            return in.readLine();
        } catch (IOException e) {
            return null;
        }
    }
}
