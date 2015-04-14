package view;

import game.OthelloBoard;
import game.OthelloController;
import game.TicTacToeBoard;
import game.TicTacToeController;

import javax.swing.*;

/**
 * Created by Gerard on 4/13/2015.
 */
public class GameView extends JFrame {

    public GameView(){
        this.add(new TicTacToeController().getBoard());
        this.setVisible(true);
    }

}
