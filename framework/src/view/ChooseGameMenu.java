package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import exceptions.ServerErrorException;
import tools.ServerConnection;

public class ChooseGameMenu extends JMenuBar{
	

	public ChooseGameMenu(final ServerConnection connection){
        JMenu tab1 = new JMenu("Options");
        JMenuItem playerList = new JMenuItem("Playerlist");
        playerList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					connection.sendCommand("GET playerlist");
				} catch (ServerErrorException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
        	
        });
        JMenuItem hack = new JMenuItem("DDoS");
        hack.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = 0;
				while(true){
					try {
						connection.sendCommand("login player"+i);
						i++;
					} catch (ServerErrorException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
        	
        });
        tab1.add(hack);
        tab1.add(playerList);
        add(tab1);
	}
	
}
