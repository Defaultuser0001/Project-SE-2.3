package test;

import static org.junit.Assert.*;
import game.Othello;

import model.Player;

import org.junit.Test;

public class TestOthello {

	@Test
	public void testPossibleMoves() {
		Othello o = new Othello(new Player("test"));
		int[] result = {19, 37, 26, 44};
		int i = 0;
		for (Integer move : result) {
			assertTrue(o.getPossibleMoves().contains(move));
			i++;
		}
	}

	@Test
	public void testPlayMove() {
		Othello o = new Othello(new Player("test"));
        o.playMove(26);
        int[] board = o.getBoard();
        assertEquals(board[26], o.getOpponent(o.getActivePlayer()));
        assertEquals(board[27], o.getOpponent(o.getActivePlayer()));
	}
}
