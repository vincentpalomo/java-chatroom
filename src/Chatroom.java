import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        // open GUI
        // need to implement server class
        // connect to server
        // figure out why GUI is not showing up
        initializeGUI();
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

    // Send Button Action Listener
    private class SendButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String message = messageField.getText();
            out.println(message);
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
                    chatArea.append(message + "\n");
                }
            } catch (Exception e) {
                chatArea.append("Error receiving message: " + e.getMessage() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        System.out.printf("hello world");
        String username = "jinx";
        new Chatroom(username);
    }
}
