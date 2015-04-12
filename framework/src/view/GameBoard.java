package view;



import javax.swing.*;

import controller.BoardController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class GameBoard extends JPanel implements ActionListener{

	protected final static int WHITE = 1;
	protected final static int BLACK = 2;
	
    protected String gameMode;
    protected JButton[][] boardTiles;
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

}
