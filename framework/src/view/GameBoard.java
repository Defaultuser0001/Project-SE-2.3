package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameBoard extends JPanel{

    private String gameMode;
    private ArrayList<BoardTile> tiles = new ArrayList<BoardTile>();

    public GameBoard(int x, int y, String gameMode, ActionListener listener){
        this.setLayout(new GridLayout(x,y));
        this.gameMode = gameMode;
        
        for (int i = 0; i < x; i++){
        	for (int j = 0; j < y; j++){
        		BoardTile tile = new BoardTile(i,j);
        		tile.addActionListener(listener);
        		tiles.add(tile);
        		add(tile);
        	}
        }
    }

}
