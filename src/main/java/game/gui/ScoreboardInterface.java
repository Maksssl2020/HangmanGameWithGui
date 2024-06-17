package game.gui;

import game.scoreboard.PlayerScore;
import game.scoreboard.Scoreboard;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ScoreboardInterface extends TextAreaWindow {
    private final Scoreboard scoreboardRepository = new Scoreboard();

    public ScoreboardInterface() {
        setTitle("Hangman Scoreboard");
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        actionButton.setText("SEARCH");
        actionButton.addActionListener(e -> searchPlayerScores());
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    searchPlayerScores();
                }
            }
        });
    }

    private void searchPlayerScores() {
        String playerNickname = inputField.getText();

        if (playerNickname.isEmpty()) {
            textArea.setText("");
        } else if (playerNickname.equals("ALL")) {
            textArea.setText("");
            showAllPlayersScore();
        } else {
            textArea.setText("");
            showPlayerScores(playerNickname);
        }
    }

    public void showAllPlayersScore() {
        ArrayList<PlayerScore> playersScores = scoreboardRepository.findAll();
        getScores(playersScores);
    }

    public void showPlayerScores(String playerNickname) {
        ArrayList<PlayerScore> playerScores = scoreboardRepository.findByPlayerNickname(playerNickname);
        getScores(playerScores);
    }

    private void getScores(ArrayList<PlayerScore> playersScores) {
        playersScores.forEach(playerScore -> textArea.append(preparePlayerScoreToDisplay(playerScore)));
    }

    private String preparePlayerScoreToDisplay(PlayerScore playerScore) {
        return String.format("%s\n", playerScore);
    }

    public static void main(String[] args) {
        new ScoreboardInterface();
    }
}
