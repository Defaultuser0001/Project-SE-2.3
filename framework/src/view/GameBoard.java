package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class GameBoard extends JPanel{

    protected String gameMode;
    protected JButton[][] boardTiles;
    private LinkedList<ActionListener> listeners = new LinkedList<ActionListener>();

    public GameBoard(ActionListener listener){
    	addActionListener(listener);
    }
    
    protected abstract void setBoardTiles();
    
    public void addActionListener(ActionListener listener){
    	listeners.add(listener);
    }
    
    protected void processAction() {
    	for (ActionListener listener : listeners){
    		listener.notify();
    	}
    }

}
