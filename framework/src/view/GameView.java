package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import game.OthelloBoard;
import game.OthelloController;
import game.TicTacToeBoard;
import game.TicTacToeController;

import javax.swing.*;

import controller.BoardController;

/**
 * Created by Gerard on 4/13/2015.
 */
public abstract class GameView extends JFrame {
	
	private JLabel label;
	private BoardController controller;

	public GameView(final JFrame parent, JPanel board, BoardController controller) {
		super();
		this.controller = controller;
		setLayout(new BorderLayout());
		label = new JLabel("");
		label.setPreferredSize(new Dimension(100,30));
		add(label, BorderLayout.NORTH);
		add(board, BorderLayout.CENTER);
		add(controller.getTimeView(), BorderLayout.SOUTH);
		setVisible(true);
		setPreferredSize(new Dimension(700,700));
		pack();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				parent.setEnabled(true);
				dispose();
			}
		});

	}
	
	public void updateLabel(){
		if(controller.getModel().isMyTurn()){
			label.setText("MY TURN");
		} else {
			label.setText("Opponent's turn");
		}
	}

}
