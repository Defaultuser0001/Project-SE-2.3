package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import exceptions.ServerErrorException;
import tools.ServerConnection;

public class PlayerListView extends JFrame{
	
	private LinkedList<String> playerList;
	
	public PlayerListView(LinkedList<String> playerlist, ServerConnection connection){
		//cba making a separate controller class for this
		super("Online players");
		this.playerList = playerlist;
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (int i = 0; i < playerList.size(); i++) {
			model.addElement(playerList.get(i));
		}
		JList<String> jlist2 = new JList<String>(model);
	    jlist2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );    
	    JScrollPane scrollPane2 = new JScrollPane(jlist2);
	    add(scrollPane2, BorderLayout.NORTH);
	    
	    JPanel buttons = new JPanel();
	    
	    JRadioButton othelloButton = new JRadioButton("Othello");
		othelloButton.setActionCommand("Reversi");
		othelloButton.setSelected(true);
		
		JRadioButton tttButton = new JRadioButton("Tic-Tac-Toe");
		tttButton.setActionCommand("Tic-tac-toe");
		
		ButtonGroup group = new ButtonGroup();
		group.add(othelloButton);
		group.add(tttButton);
		
		buttons.add(othelloButton, BorderLayout.EAST);
		buttons.add(tttButton, BorderLayout.WEST);
		
		add(buttons, BorderLayout.CENTER);
	    
	    JButton challenge = new JButton("Challenge player");
	    challenge.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					connection.sendCommand("challenge \"" + jlist2.getSelectedValue() + "\" \"" + group.getSelection().getActionCommand() +"\"");
				} catch (ServerErrorException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
	    	
	    });
	    add(challenge, BorderLayout.SOUTH);
	    setSize(300,350);
	    setVisible(true);
		
	}
	
	
}
