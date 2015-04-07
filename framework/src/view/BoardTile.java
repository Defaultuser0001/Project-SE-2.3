package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JPanel;

public class BoardTile extends JPanel{
	
	/*
	 * TODO: add background
	 * TODO: add player markings (X, O for tictactoe; black or white stone for Othello)
	 * TODO: add MouseListener (to register that this tile has been clicked)
	 */
	
	private int x;
	private int y;
	
	private LinkedList<ActionListener> listeners = new LinkedList<ActionListener>();
	
	public BoardTile(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void addActionListener(ActionListener listener){
		listeners.add(listener);
	}
	
	private void processEvent(ActionEvent e){
		for( ActionListener l : listeners)
			l.actionPerformed( e );
	}
}
