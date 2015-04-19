package view;

import controller.ChooseGameController;
import model.Player;
import tools.ServerConnection;
import tools.ServerListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ChooseGameView extends JFrame{
	
	private JLabel queue;

    public ChooseGameView(Player player, ServerConnection connection, ServerListener server){
        super("Logged in as: " + player.getName());
        
        JLabel text = new JLabel("Choose your game mode");
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setVerticalAlignment(JLabel.CENTER);

        this.add(text, BorderLayout.NORTH);

        this.add(new ChooseGameController(connection,this, server,player), BorderLayout.CENTER);
        
        queue = new JLabel("");
        
        queue.setHorizontalAlignment(JLabel.CENTER);
        queue.setVerticalAlignment(JLabel.CENTER);
        
        this.add(queue, BorderLayout.SOUTH);
 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(new ChooseGameMenu(connection));
        this.setVisible(true);
        this.setPreferredSize(new Dimension(400, 550));
        this.setResizable(false);
        this.pack();
    }
    
    public void setQueueText(){
    	ImageIcon imgThisImg = new ImageIcon("./res/waiticon.gif");
    	queue.setText("Waiting for a match...");
    	queue.setIcon(imgThisImg);
    }
    
    public void disableQueueText(){
    	queue.setText("");
    	queue.setIcon(null);
    }
}