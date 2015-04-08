package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import model.BoardModel;

import view.GameBoard;

public abstract class BoardController implements ActionListener{

	private GameBoard board;
	private BoardModel model;
	private String gameMode;
	
	public BoardController(int x, int y, String gameMode){
		this.gameMode = gameMode;
	}

	@Override
	public abstract void actionPerformed(ActionEvent e);
	
	public JPanel getBoard() {
		return board;
	}
}
