package game.listeners;

import game.controller.HangmanController;
import game.gui.AdminPlayerInterface;
import game.gui.GameChatInterface;
import game.gui.GuessingPlayerInterface;
import game.logic.HangmanEngine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewRoundListener implements ActionListener {
    private final HangmanEngine gameEngine;
    private final AdminPlayerInterface adminInterface;
    private final GuessingPlayerInterface playerInterface;
    private final GameChatInterface adminChatInterface;

    public NewRoundListener(HangmanController gameController) {
        gameEngine = gameController.getHangmanEngine();
        adminInterface = gameController.getAdminInterface();
        playerInterface = gameController.getHangmanPlayerInterface();
        adminChatInterface = gameController.getHangmanAdmin().getChatInterface();
    }

    @Override
    public void actionPerformed(ActionEvent newRoundEvent) {
        if (newRoundEvent.getSource().equals(adminInterface.getStartNewRoundButton())) {
            handleNewRoundButton();
        }
    }

    private void handleNewRoundButton() {
        String gameResult = gameEngine.checkGameResult();

        if (gameResult.contains("is not guessed!") || gameResult.contains("is not set!")) {
            adminChatInterface.gameServerMessage(String.format("%s, you can't start new round because this one hasn't ended yet!", gameEngine.getGameAdmin().getNickname()));
        } else {
            gameEngine.clearGame();

            if (gameResult.contains("won")) {
                gameEngine.getHangmanGuessingPlayer().addPoints(gameEngine.getWordToGuess().getWordPoints());
            }

            adminInterface.getCurrentPlayerScore().setText(String.valueOf(gameEngine.getHangmanGuessingPlayer().getScore()));
            adminInterface.getCurrentPlayerAmountOfTries().setText("7");
            adminChatInterface.gameServerMessage(String.format("%s, started new round!", gameEngine.getGameAdmin().getNickname()));

            playerInterface.clearGameInterface();
        }
    }
}
