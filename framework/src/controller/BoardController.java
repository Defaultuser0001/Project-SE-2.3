package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.BoardModel;

import view.BoardTile;
import view.GameBoard;

public class BoardController implements ActionListener{

	private GameBoard board;
	private BoardModel model;
	private String gameMode;
	
	public BoardController(int x, int y, String gameMode){
		board = new GameBoard(x, y, gameMode, this);
		model = new BoardModel(x, y);
		this.gameMode = gameMode;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		BoardTile tile = (BoardTile) e.getSource();
		// TODO: process action further...
	}
}
