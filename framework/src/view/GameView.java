package view;

import controller.OthelloController;
import game.OthelloBoard;

import javax.swing.*;

/**
 * Created by Gerard on 4/13/2015.
 */
public class GameView extends JFrame {

    public GameView(){
        this.add(new OthelloBoard(new OthelloController(1,1,"othello")));
        this.setVisible(true);
    }

}
