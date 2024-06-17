package game.listeners;

import game.controller.HangmanController;
import game.gui.AdminPlayerInterface;
import game.gui.GameChatInterface;
import game.logic.HangmanEngine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HintToPlayerListener implements ActionListener {
    private HangmanEngine gameEngine;
    private AdminPlayerInterface adminInterface;
    private GameChatInterface adminChatInterface;
    private GameChatInterface guessingPlayerChatInterface;

    public HintToPlayerListener(HangmanController controller) {
        gameEngine = controller.getHangmanEngine();
        adminInterface = controller.getAdminInterface();
        adminChatInterface = controller.getHangmanAdmin().getChatInterface();
        guessingPlayerChatInterface = controller.getHangmanGuessingPlayer().getChatInterface();
    }

    @Override
    public void actionPerformed(ActionEvent hintEvent) {
        if (hintEvent.getSource().equals(adminInterface.getGiveHintToPlayerButton())) {
            handleGivingHint();
        }
    }

    private void handleGivingHint() {
        String adminNickname = gameEngine.getGameAdmin().getNickname();
        String playerNickname = gameEngine.getHangmanGuessingPlayer().getNickname();

        if (gameEngine.getGameAdmin().getAmountOfPossibleHints() == 0) {
            adminChatInterface.gameServerMessage(String.format("%s, you can't give a hint because you don't have anymore possible hints!", adminNickname));
        } else if (gameEngine.getWordToGuess().getChosenWord() == null) {
            adminChatInterface.gameServerMessage(String.format("%s, you can't give a hint because word to guess isn't set!", adminNickname));
        } else {
            String drawnLetter = gameEngine.drawnLetterForAHint();
            adminChatInterface.gameServerMessage(String.format("%s, you just sent a hint to %s!", adminNickname, playerNickname));
            guessingPlayerChatInterface.gameServerMessage(String.format("%s, sent %s a hint! One of the letters in hidden word is: '%S'", adminNickname, playerNickname, drawnLetter));
            gameEngine.getGameAdmin().subtractAmountOfPossibleHints();
        }
    }
}
