package view;

import controller.ChooseGameController;
import controller.ServerListener;
import model.Human;
import model.Player;
import tools.ServerConnection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ChooseGameView extends JFrame{

    public ChooseGameView(Human player, ServerConnection connection){
        super("Choose a game || Logged in as: " + player.getName());

        JTextField text = new JTextField("Choose your game mode");
        this.add(text, BorderLayout.NORTH);

        this.add(new ChooseGameController(connection,this), BorderLayout.CENTER);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(400, 550));
        this.setResizable(false);
        this.pack();
    }
}