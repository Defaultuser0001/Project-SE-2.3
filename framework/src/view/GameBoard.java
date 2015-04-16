package view;



import javax.swing.*;

import controller.BoardController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class GameBoard extends JPanel implements ActionListener{

	public final static int PLAYER1 = 1;
	public final static int PLAYER2 = 2;
	
    protected String gameMode;
    protected LinkedList<ActionListener> listeners = new LinkedList<ActionListener>();

    public GameBoard(BoardController listener){
    	addActionListener(listener);
    }
    
    protected abstract void setBoardTiles();
    
    public void addActionListener(ActionListener listener){
    	listeners.add(listener);
    }
    
    protected abstract void processAction(int move);
    
    public abstract void makeMove(int player, int move);

	public abstract void updateBoard(int[] board);

}
