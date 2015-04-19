package test;

import static org.junit.Assert.*;

import model.Player;

import org.junit.Test;

import game.TicTacToe;
import game.TicTacToeAI;

public class TestTicTacToe {

	@Test
	public void testPlayMove() {
		TicTacToe t = new TicTacToe(new Player("test"));
        t.playMove(0);
        int[] board = t.getBoard();
        assertEquals(board[0], t.getOpponent(t.getActivePlayer()));
	}

	@Test
	public void isAWinTest(){
		 TicTacToe t = new TicTacToe(new Player("test"));
         t.playMove(0);
         t.playMove(3);
         t.playMove(1);
         t.playMove(4);
         t.playMove(2);
         assertEquals(t.isAWin(), -1);	
	}
	
	@Test
	public void chooseMoveTest(){
		TicTacToe t = new TicTacToe(new Player("test"));
        t.playMove(0);
        t.playMove(3);
        t.playMove(1);
        TicTacToeAI ai = new TicTacToeAI(t);
        assertEquals(2, ai.chooseMove());
	}
}
