package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ChooseGameView extends JFrame{

    public ChooseGameView(){
        JFrame frame = new JFrame("Choose Game");

        // Choose your game mode
        JTextField text = new JTextField("Choose your game mode");
        frame.add(text, BorderLayout.NORTH);

        // Othello of tictactoe
        JPanel game = new JPanel();

        JCheckBox checkBoxOthello = new JCheckBox("Othello ");
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
                JToggleButton pVSP = (JToggleButton)e.getSource();
                if (pVSP.isSelected()) {
                    pVSAi.setSelected(false);
                    AiVSAi.setSelected(false);
                }
            }
        });
        pVSAi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JToggleButton pVSAi = (JToggleButton)e.getSource();
                if (pVSAi.isSelected()) {
                    pVSP.setSelected(false);
                    AiVSAi.setSelected(false);
                }
            }
        });
        AiVSAi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JToggleButton AiVSAi = (JToggleButton)e.getSource();
                if (AiVSAi.isSelected()) {
                    pVSP.setSelected(false);
                    pVSAi.setSelected(false);
                }
            }
        });
        games.add(pVSP);
        games.add(pVSAi);
        games.add(AiVSAi);
        frame.add(game, BorderLayout.NORTH);
        frame.add(games, BorderLayout.CENTER);


        // Search for game
        JButton search = new JButton("Search for game");
        frame.add(search, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(400,600));
        frame.pack();
    }
}