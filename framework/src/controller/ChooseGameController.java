package controller;

import exceptions.ServerErrorException;
import game.OthelloBoard;
import game.OthelloController;
import game.OthelloGameView;
import game.TicTacToeController;
import game.TicTacToeGameView;
import tools.ServerConnection;
import view.ChooseGameView;
import view.GameView;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChooseGameController extends JPanel {

	private ChooseGameView view;
	private ServerConnection connection;
	private ServerListener server;
	private JRadioButton othelloButton;
	private JRadioButton tttButton;
	private Player player;

	private enum GameTypes {
		REVERSI, TTT
	}

	public ChooseGameController(final ServerConnection connection,
			final ChooseGameView view, final ServerListener server, Player player) {
		this.player = player;
		this.view = view;
		this.connection = connection;
		this.server = server;
		
		this.setLayout(new FlowLayout());

		server.setActiveChooseGameController(this);

		JPanel pictures = new JPanel();

		BufferedImage othello = null;
		BufferedImage ttt = null;
		try {
			othello = ImageIO.read(new File("./res/othello.png"));
			ttt = ImageIO.read(new File("./res/tictactoe.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(othello));
		JLabel picLabel2 = new JLabel(new ImageIcon(ttt));

		pictures.add(picLabel, BorderLayout.EAST);
		pictures.add(picLabel2, BorderLayout.WEST);

		JPanel game = new JPanel();

		othelloButton = new JRadioButton("Othello");
		othelloButton.setActionCommand("othello");
		othelloButton.setSelected(true);

		tttButton = new JRadioButton("Tic-Tac-Toe");
		tttButton.setActionCommand("ttt");

		ButtonGroup group = new ButtonGroup();
		group.add(othelloButton);
		group.add(tttButton);

		game.add(othelloButton, BorderLayout.EAST);
		game.add(tttButton, BorderLayout.WEST);

		// Choose, player vs player || player vs AI || AI vs AI
		JPanel games = new JPanel();
		final JToggleButton pVSP = new JToggleButton("Player vs. Player");
		final JToggleButton pVSAi = new JToggleButton("Player vs. AI");

		// zorgen ervoor dat er maar 1 van de 3 aangeklikt kan zijn, weet nog op
		// dit in 1 kan??
		pVSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JToggleButton pVSP = (JToggleButton) e.getSource();
				if (pVSP.isSelected()) {
					pVSAi.setSelected(false);
					player.setAI(false);
				}
			}
		});
		pVSAi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JToggleButton pVSAi = (JToggleButton) e.getSource();
				if (pVSAi.isSelected()) {
					pVSP.setSelected(false);
					player.setAI(true);
				}
			}
		});
		games.add(pVSP);
		games.add(pVSAi);
		games.setSize(600,600);

		JButton search = new JButton("Search for game");
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (othelloButton.isSelected()) {
						connection.sendCommand("subscribe Reversi");
						view.setQueueText();
					} else {
						connection.sendCommand("subscribe Tic-tac-toe");
						view.setQueueText();
					}
				} catch (ServerErrorException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		this.add(pictures);
		this.add(game);
		this.add(games);
		this.add(search);
		view.pack();
		

	}

	public void createGame(String gametype) {
		GameTypes type = GameTypes.valueOf(gametype.toUpperCase());
		switch (type) {
		case REVERSI:
			OthelloController othelloController = new OthelloController(connection,player);
			OthelloGameView othelloView = new OthelloGameView(view,
					othelloController.getBoard(), othelloController, connection);
			server.setActiveGame(othelloController, othelloView);
			view.setEnabled(false);
			break;
		case TTT:
			TicTacToeController tttController = new TicTacToeController(
					connection, player);
			TicTacToeGameView tttGameView = new TicTacToeGameView(view,
					tttController.getBoard(), tttController, connection);
			server.setActiveGame(tttController, tttGameView);
			view.setEnabled(false);
		}
	}
	
	public ChooseGameView getView(){
		return view;
	}

}
