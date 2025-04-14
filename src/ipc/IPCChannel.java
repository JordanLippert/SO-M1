package ipc;

public interface IPCChannel {
    void send(String message);
    String receive();
}
