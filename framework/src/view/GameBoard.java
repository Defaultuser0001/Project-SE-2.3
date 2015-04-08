package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class GameBoard extends JPanel{

    private String gameMode;
    private JButton[][] boardTiles;

    public GameBoard(int x, int y, String gameMode, ActionListener listener){
        this.gameMode = gameMode;
        setBoardTiles();
        this.setLayout(new GridLayout(x,y));
        validate();
    }
    
    protected abstract void setBoardTiles();

}
