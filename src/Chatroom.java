import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Chatroom {

    private String username;
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private final JFrame frame = new JFrame("Chatroom");
    private final JTextArea chatArea = new JTextArea(20, 40);
    private final JTextField messageField = new JTextField(40);

    public Chatroom(String username) {
        this.username = username;

        // connect to the server
        connectToServer();

        // initialize gui
        initializeGUI();

        // start a new thread to handle incoming messages
        new Thread(new IncomingMessageHandler()).start();
    }

    private void initializeGUI() {
        // set up gui components

        // message area
        JPanel panel = new JPanel();
        panel.add(messageField);

        // send button
        JButton sendButton = new JButton("Send");
        panel.add(sendButton);

        // frame layout
        frame.getContentPane().add(BorderLayout.CENTER, new JScrollPane(chatArea));
        frame.getContentPane().add(BorderLayout.SOUTH, panel);

        // set font of the chat area
        chatArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // padding for the chat area
        chatArea.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // margins for message field
        messageField.setMargin(new Insets(10,10,10,10));

        // need action listeners for button and message
        sendButton.addActionListener(new SendButtonListener());
        messageField.addActionListener(new SendButtonListener());


        // set up frame GUI
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,500);
        frame.setVisible(true);
    }

    // method to connect to server
    private void connectToServer(){
        try {
            // establish connection to server
            socket = new Socket("localhost", 3000);

            // set up input and output stream for communication with server
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);

            // send username to the server
            out.println(username);
        } catch (IOException e) {
            chatArea.append("Error connecting to server: " + e.getMessage() + "\n");
        }
    }

    // Send Button Action Listener
    private class SendButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // get message from the message field
            String message = messageField.getText();

            // send the message to server
            out.println(message);

            // clear the message field
            messageField.setText("");
        }
    }

    // Incoming Message Handler
    private class IncomingMessageHandler implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    // sends messages to the next line after submission
                    String message = in.nextLine();
                    // display the recieved message in the chat area
                    chatArea.append(message + "\n");
                }
            } catch (Exception e) {
                chatArea.append("Error receiving message: " + e.getMessage() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        System.out.printf("chatroom is online");
        String username = "jinx";
        new Chatroom(username);
    }
}
