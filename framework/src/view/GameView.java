package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import game.OthelloBoard;
import game.OthelloController;
import game.TicTacToeBoard;
import game.TicTacToeController;

import javax.swing.*;

/**
 * Created by Gerard on 4/13/2015.
 */
public abstract class GameView extends JFrame {

	public GameView(final JFrame parent, JPanel board) {
		super();
		add(board);
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				parent.setEnabled(true);
				dispose();
			}
		});

	}

}
