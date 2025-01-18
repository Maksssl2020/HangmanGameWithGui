package game.listeners;

import game.controller.HangmanController;
import game.gui.AdminPlayerInterface;
import game.gui.GameChatInterface;
import game.gui.GuessingPlayerInterface;
import game.logic.HangmanEngine;

import java.awt.event.*;
import java.util.Optional;

public class SetWordToGuessListener extends KeyAdapter implements ActionListener {
    private GuessingPlayerInterface playerInterface;
    private AdminPlayerInterface adminInterface;
    private HangmanEngine hangmanEngine;
    private GameChatInterface adminChatInterface;
    private GameChatInterface guessingPlayerChatInterface;

    public SetWordToGuessListener(HangmanController gameController) {
        playerInterface = gameController.getHangmanPlayerInterface();
        adminInterface = gameController.getAdminInterface();
        hangmanEngine = gameController.getHangmanEngine();
        adminChatInterface = gameController.getHangmanAdmin().getChatInterface();
        guessingPlayerChatInterface = gameController.getHangmanGuessingPlayer().getChatInterface();
    }

    @Override
    public void actionPerformed(ActionEvent setWordToGuessEvent) {
        if (setWordToGuessEvent.getSource().equals(adminInterface.getSetWordButton())) {
            handleSettingWordToGuess();
        }
    }

    @Override
    public void keyPressed(KeyEvent setWordToGuessEvent) {
        if (setWordToGuessEvent.getKeyCode() == KeyEvent.VK_ENTER) {
            handleSettingWordToGuess();
        }
    }

    private void handleSettingWordToGuess() {
        String enteredWordByAdmin = adminInterface.getSettingWordToGuessField().getText();
        Optional<String> currentWordToGuess = Optional.ofNullable(hangmanEngine.getWordToGuess().getChosenWord());
        String adminNickname = hangmanEngine.getGameAdmin().getNickname();
        String isGameOver = hangmanEngine.checkGameResult();

        if (enteredWordByAdmin.isEmpty()) {
            adminChatInterface.gameServerMessage(String.format("%s: You can't set empty word to guess!", adminNickname));
        } else if (currentWordToGuess.isPresent() && isGameOver.contains("not guessed!")) {
            adminChatInterface.gameServerMessage(String.format("%s: You can't set new word to guess, current game is not over!", adminNickname));
        } else if (isGameOver.contains("not guessed!") || hangmanEngine.isCurrentRoundActive()) {
            adminChatInterface.gameServerMessage(String.format("%s: You can't set new word to guess, current game is not over!", adminNickname));
        } else {
            hangmanEngine.setWordToGuess(enteredWordByAdmin);
            hangmanEngine.setCurrentRoundActive(true);
            String messageFotBothPlayers = String.format("%s set new word to guess!", adminNickname);

            adminInterface.getSettingWordToGuessField().setText("");
            adminChatInterface.gameServerMessage(messageFotBothPlayers);

            playerInterface.setHangmanTextAreaContent(hangmanEngine.getHiddenWord());
            playerInterface.getWordPointsField().setText(String.format("Word points: %d", hangmanEngine.getWordToGuess().getWordPoints()));
            guessingPlayerChatInterface.gameServerMessage(messageFotBothPlayers);
        }
    }

}
