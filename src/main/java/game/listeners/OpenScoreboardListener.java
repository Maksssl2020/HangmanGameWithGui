package game.listeners;

import game.gui.ScoreboardInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenScoreboardListener implements ActionListener {

    public OpenScoreboardListener() {}

    @Override
    public void actionPerformed(ActionEvent openScoreboardEvent) {
        ScoreboardInterface scoreboardInterface = new ScoreboardInterface();
        scoreboardInterface.showAllPlayersScore();
        scoreboardInterface.setVisible(true);
    }
}
