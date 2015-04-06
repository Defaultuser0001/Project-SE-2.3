package view;

import controller.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginView extends JFrame {

    public static void main(String[] args) {
        new LoginView();
    }

    public LoginView(){

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));

        JLabel usernameLabel = new JLabel("username");

        panel.add(usernameLabel);
        final JTextField textField = new JTextField("Username");
        panel.add(textField);

        JButton button = new JButton("log_in");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Login(textField.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        panel.add(button);

        this.setSize(new Dimension(500,300));
        this.add(panel);
        this.setVisible(true);

    }

}
