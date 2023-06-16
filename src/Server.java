import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    // figure out a way to make the server run over the internet

    // set port to 3000
    private static final int PORT = 3000;

    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Chatroom server started on port: " + PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
