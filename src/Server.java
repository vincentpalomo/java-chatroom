import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    // figure out a way to make the server run over the internet
    // connect server to client
    // inputs from client will be sent here and sent back out to GUI
    // broadcast messages from client user

    // set port to 3000
    private static final int PORT = 3000;

    // connection endpoint for clients
    private static ArrayList<Socket> clients = new ArrayList<>();

    public static void main(String[] args) {
        try{
            // Server start up
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Chatroom server started on port: " + PORT);

            while (true) {
                // connection check to client -Chatroom GUI-
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                // add client socket to the client list *ArrayList
                clients.add(clientSocket);

                // create a new thread to handle client communication
                Thread thread = new Thread(new ClientHandler(clientSocket));
                thread.start();
            }
        } catch (IOException e) {
            // handle errors that occur during server socket creation
            System.out.println("Error creating server socket: " + e.getMessage());
        }
    }

    // client handler
    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }
        @Override
        public void run() {
            // this method is executed when the thread starts running
            // handles communication with a specific client
            System.out.println("Test");

            // implement the logic to receive and send message to the chatroom
        }
    }
}
