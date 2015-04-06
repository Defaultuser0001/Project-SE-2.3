package view;

import javax.swing.*;
import java.awt.*;

public abstract class GameBoard extends JPanel{

    private String gameMode;

    public GameBoard(int x, int y, String gameMode){
        this.setLayout(new GridLayout(x,y));
        this.gameMode = gameMode;
    }

}
