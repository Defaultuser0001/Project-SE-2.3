package view;

import controller.Login;
import controller.ServerListener;
import tools.ServerConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginView extends JFrame {

    public static void main(String[] args) throws IOException {
        new LoginView();
    }

    public LoginView() throws IOException {

        final ServerConnection connection = new ServerConnection();
        Thread thread = new Thread(new ServerListener(connection));
        thread.start();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JLabel usernameLabel = new JLabel("username");

        panel.add(usernameLabel);
        final JTextField textField = new JTextField("Username");
        panel.add(textField);
        panel.add(new Login(textField.getText(), connection, this));

        this.setSize(new Dimension(500, 300));
        this.add(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

}
