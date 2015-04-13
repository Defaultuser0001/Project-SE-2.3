package controller;

import exceptions.ServerErrorException;
import game.OthelloBoard;
import tools.ServerConnection;
import view.ChooseGameView;
import view.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseGameController extends JPanel implements ActionListener{

	private ChooseGameView view;
	private ServerConnection connection;

	public ChooseGameController(ServerConnection connection, ChooseGameView view) {
		this.view = view;
		this.connection = connection;
		JPanel game = new JPanel();

		JCheckBox checkBoxOthello = new JCheckBox("Othello");
		game.add(checkBoxOthello, BorderLayout.WEST);
		JCheckBox checkBoxTTT = new JCheckBox("Tic-Tac-Toe");
		game.add(checkBoxTTT, BorderLayout.EAST);

		// Choose, player vs player || player vs AI || AI vs AI
		JPanel games = new JPanel();
		final JToggleButton pVSP = new JToggleButton ("Player vs. Player");
		final JToggleButton pVSAi= new JToggleButton ("Player vs. AI");
		final JToggleButton AiVSAi = new JToggleButton ("AI vs. AI");
		// zorgen ervoor dat er maar 1 van de 3 aangeklikt kan zijn, weet nog op dit in 1 kan??
		pVSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JToggleButton pVSP = (JToggleButton) e.getSource();
				if (pVSP.isSelected()) {
					pVSAi.setSelected(false);
					AiVSAi.setSelected(false);
				}
			}
		});
		pVSAi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JToggleButton pVSAi = (JToggleButton) e.getSource();
				if (pVSAi.isSelected()) {
					pVSP.setSelected(false);
					AiVSAi.setSelected(false);
				}
			}
		});
		AiVSAi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JToggleButton AiVSAi = (JToggleButton) e.getSource();
				if (AiVSAi.isSelected()) {
					pVSP.setSelected(false);
					pVSAi.setSelected(false);
				}
			}
		});
		games.add(pVSP);
		games.add(pVSAi);
		games.add(AiVSAi);

		JButton search = new JButton("Search for game");
		search.addActionListener(this);
		this.add(game);
		this.add(games);
		this.add(search);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			connection.sendCommand("subscribe Reversi");
			System.out.println("SUBSCRIBED FOR A GAME");
			new GameView();
			view.dispose();
		} catch (ServerErrorException e1) {
			e1.printStackTrace();
		}
	}
}
