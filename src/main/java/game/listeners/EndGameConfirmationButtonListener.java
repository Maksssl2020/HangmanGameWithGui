package game.listeners;

import game.controller.HangmanController;
import game.gui.GameChatInterface;
import game.logic.HangmanEngine;
import game.scoreboard.PlayerScore;
import game.scoreboard.Scoreboard;
import players.GuessingPlayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGameConfirmationButtonListener implements ActionListener {
    private Scoreboard scoreboardRepo = new Scoreboard();
    private HangmanEngine hangmanEngine;
    private GameChatInterface guessingPlayerChatInterface;
    private GameChatInterface adminChatInterface;
    private String hangmanAdminNickname;
    private Runnable endGameRunnable;

    public EndGameConfirmationButtonListener(HangmanController controller, Runnable endGameRunnable) {
        hangmanEngine = controller.getHangmanEngine();
        guessingPlayerChatInterface = controller.getHangmanGuessingPlayer().getChatInterface();
        adminChatInterface = controller.getHangmanAdmin().getChatInterface();
        hangmanAdminNickname = controller.getHangmanAdmin().getNickname();
        this.endGameRunnable = endGameRunnable;
    }

    @Override
    public void actionPerformed(ActionEvent endGameConfirmationButton) {
        closeTheGame();
    }

    private void closeTheGame() {
        GuessingPlayer hangmanPlayer = hangmanEngine.getHangmanGuessingPlayer();
        scoreboardRepo.save(new PlayerScore(hangmanPlayer.getNickname(), hangmanPlayer.getScore()));
        String gameEndingMessage = String.format("%s, has ended the game!", hangmanAdminNickname);
        adminChatInterface.gameServerMessage(gameEndingMessage);
        guessingPlayerChatInterface.gameServerMessage(gameEndingMessage);

        endGameRunnable.run();
    }
}
