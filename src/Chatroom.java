import javax.swing.*;
import java.awt.*;
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
        JButton sendButton = new JButton();
        panel.add(sendButton);

        // frame layout
        frame.getContentPane().add(BorderLayout.CENTER, new JScrollPane(chatArea));
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
    }
    public static void main(String[] args) {
        System.out.printf("hello world");
        String username = "jinx";
        new Chatroom(username);
    }
}
