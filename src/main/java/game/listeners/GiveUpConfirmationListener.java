package game.listeners;

import game.controller.HangmanController;
import game.gui.AdminPlayerInterface;
import game.gui.ConfirmationWindow;
import game.gui.GameChatInterface;
import game.gui.GuessingPlayerInterface;
import game.logic.HangmanEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GiveUpConfirmationListener implements ActionListener {
    private HangmanEngine hangmanEngine;
    private GuessingPlayerInterface playerInterface;
    private AdminPlayerInterface adminInterface;
    private GameChatInterface guessingPlayerChatInterface;
    private GameChatInterface adminChatInterface;
    private ConfirmationWindow giveUpConfirmationWindow;

    public GiveUpConfirmationListener(HangmanController controller, ConfirmationWindow giveUpConfirmationWindow) {
        this.giveUpConfirmationWindow = giveUpConfirmationWindow;
        hangmanEngine = controller.getHangmanEngine();
        playerInterface = controller.getHangmanPlayerInterface();
        adminInterface = controller.getAdminInterface();
        guessingPlayerChatInterface = controller.getHangmanGuessingPlayer().getChatInterface();
        adminChatInterface = controller.getHangmanAdmin().getChatInterface();
    }

    @Override
    public void actionPerformed(ActionEvent giveUpConfirmationEvent) {
        if (giveUpConfirmationEvent.getSource().equals(giveUpConfirmationWindow.getYesButton())) {
            handlePlayerGivingUp();
        }
    }

    private void handlePlayerGivingUp() {
        String playerNickname = hangmanEngine.getHangmanGuessingPlayer().getNickname();

        if (hangmanEngine.getWordToGuess().getChosenWord() == null) {
            guessingPlayerChatInterface.gameServerMessage(String.format("%s, you can't give up, because there is no word to guess!", playerNickname));
        } else if (!hangmanEngine.checkGameResult().contains("not guessed") && !hangmanEngine.checkGameResult().contains("Player failed")) {
            guessingPlayerChatInterface.gameServerMessage(String.format("%s, you can't give up because current round is over!", playerNickname));
        } else {
            SwingUtilities.invokeLater(() -> {
                hangmanEngine.getHangmanGuessingPlayer().setGaveUp(true);

                playerInterface.getHangmanTextArea().setText(hangmanEngine.getWordToGuess().getChosenWord());
                playerInterface.updateHangmanImage("C:\\Users\\maksy\\IdeaProjects\\HangmanGameWithGui\\src\\main\\java\\game\\gui\\img\\Hangman_0.png");
                guessingPlayerChatInterface.gameServerMessage("You gave up!");

                adminInterface.getCurrentPlayerAmountOfTries().setText("7");
                adminChatInterface.gameServerMessage(String.format("%s, has gave up!", playerNickname));
            });
        }

        SwingUtilities.invokeLater(() -> giveUpConfirmationWindow.dispose());
    }
}
