package view;

import controller.Login;
import controller.ServerListener;
import tools.ServerConnection;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoginView extends JFrame {

    public static void main(String[] args) throws IOException {
        new LoginView();
    }

    private Login login;
    private final JTextField nameField;

    public LoginView() throws IOException {

        super("Log in");
        final ServerConnection connection = new ServerConnection();
        Thread thread = new Thread(new ServerListener(connection, this));
        thread.start();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JLabel usernameLabel = new JLabel("username");
        panel.add(usernameLabel);
        nameField = new JTextField("Username");
        panel.add(nameField);
        panel.add(login = new Login(connection, this));
        this.setPreferredSize(new Dimension(400,200));
        this.add(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();

    }

    public String getCurrentName(){
        return nameField.getText();
    }

    public Login getLogin(){
        return login;
    }

}
