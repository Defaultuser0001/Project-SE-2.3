package view;

import controller.Login;
import tools.ServerConnection;
import tools.ServerListener;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;

public class LoginView extends JFrame {

    public static void main(String[] args) throws IOException {
        new LoginView();
    }

    private Login login;
    private final JTextField nameField;
    private final JTextField ip;
    private final JTextField port;
    
    public LoginView() throws IOException {

        super("Log in");
        
        JPanel usernamepanel = new JPanel();
        usernamepanel.setLayout(new BorderLayout());
        JLabel usernameLabel = new JLabel("Username:");
        usernamepanel.add(usernameLabel, BorderLayout.NORTH);
        nameField = new JTextField("Username");
        usernamepanel.add(nameField, BorderLayout.CENTER);
        
        JPanel centerpanel = new JPanel();
        centerpanel.setLayout(new BorderLayout()); 
        JLabel socketLabel = new JLabel("Enter IP and port"); 
        centerpanel.add(socketLabel, BorderLayout.CENTER);
        
        JPanel socketpanel = new JPanel();
        socketpanel.setLayout(new BorderLayout()); 
        ip = new JTextField("localhost");
        socketpanel.add(ip, BorderLayout.NORTH);
        port = new JTextField("7789");
        socketpanel.add(port, BorderLayout.CENTER);
        socketpanel.add(login = new Login(this), BorderLayout.SOUTH);
        this.setPreferredSize(new Dimension(400,200));
        
        this.add(usernamepanel, BorderLayout.NORTH);
        this.add(centerpanel, BorderLayout.CENTER);
        this.add(socketpanel, BorderLayout.SOUTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();

    }

    public String getCurrentName(){
        return nameField.getText();
    }
    
    public String getIP(){
    	return ip.getText();
    }
    
    public int getPort(){
    	return Integer.parseInt(port.getText());
    }

    public Login getLogin(){
        return login;
    }

}
