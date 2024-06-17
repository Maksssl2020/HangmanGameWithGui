package game.listeners;

import game.controller.HangmanController;
import game.gui.AdminPlayerInterface;
import game.gui.GameChatInterface;
import game.gui.GuessingPlayerInterface;
import game.logic.HangmanEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LetterButtonListener implements ActionListener {
    private HangmanEngine gameEngine;
    private GameChatInterface guessingPlayerChatInterface;
    private GameChatInterface adminChatInterface;
    private GuessingPlayerInterface playerInterface;
    private AdminPlayerInterface adminInterface;
    private String letter;
    private int playerTries;

    public LetterButtonListener(HangmanController gameController) {
        gameEngine = gameController.getHangmanEngine();
        guessingPlayerChatInterface = gameController.getHangmanGuessingPlayer().getChatInterface();
        playerInterface = gameController.getHangmanPlayerInterface();
        adminChatInterface = gameController.getHangmanAdmin().getChatInterface();
        adminInterface = gameController.getAdminInterface();
        playerTries = gameEngine.getHangmanGuessingPlayer().getTries();
    }

    @Override
    public void actionPerformed(ActionEvent letterButtonClick) {
        if (isPerformedActionValid()) {
            JButton clickedButton = checkEnteredLetter(letterButtonClick);
            refreshPlayerInterface(clickedButton);
        }
    }

    private boolean isPerformedActionValid() {
        String actualGameResult = gameEngine.checkGameResult();

        if (gameEngine.getWordToGuess().getChosenWord() == null) {
            guessingPlayerChatInterface.gameServerMessage(String.format("%s, you can't guess a letter, when there is no word to guess!", gameEngine.getHangmanGuessingPlayer().getNickname()));
            return false;
        } else if (actualGameResult.contains("Word has been guessed")) {
            gameMessageWhenPlayerGuessedTheWord(actualGameResult);
            return false;
        } else {
            return true;
        }
    }

    private JButton checkEnteredLetter(ActionEvent letterButtonClick) {
        JButton clickedButton = null;
        for (JButton letterButton : playerInterface.getLetterButtons()) {
            if (letterButtonClick.getSource().equals(letterButton)) {
                letter = letterButton.getText().toLowerCase();
                clickedButton = letterButton;
                break;
            }
        }

        return clickedButton;
    }

    private void refreshPlayerInterface(JButton clickedButton) {
        String guessingResult = gameEngine.guessLetterInHiddenWord(letter);
        String gameResultAfterGuessingLetter = gameEngine.checkGameResult();
        guessingPlayerChatInterface.gameServerMessage(guessingResult);

        if (guessingResult.contains("correct")) {
            clickedButton.setBackground(Color.GREEN);
        } else if (guessingResult.contains("left")) {
            clickedButton.setBackground(Color.RED);
        }

        if (gameResultAfterGuessingLetter.contains("Player won")) {
            gameMessageWhenPlayerGuessedTheWord(gameResultAfterGuessingLetter);
            gameEngine.getHangmanGuessingPlayer().addPoints(gameEngine.getWordToGuess().getWordPoints());
        }

        int playerTries = gameEngine.getHangmanGuessingPlayer().getTries();
        changeHangmanImage(playerTries);

        playerInterface.getHangmanTextArea().setText(gameEngine.getHiddenWord());
        playerInterface.getTriesLeftField().setText(String.format("Tries: %d", playerTries));
        adminInterface.getCurrentPlayerAmountOfTries().setText(String.valueOf(playerTries));
    }

    private void gameMessageWhenPlayerGuessedTheWord(String actualGameResult) {
        String messageForPlayers = actualGameResult.replace("Player", adminInterface.getCurrentPlayerName().getText());
        guessingPlayerChatInterface.gameServerMessage(messageForPlayers);
        adminChatInterface.gameServerMessage(messageForPlayers);
    }

    private void changeHangmanImage(int playerRemainingTries) {
        if (playerRemainingTries != playerTries) {
            playerTries = playerRemainingTries;
            switch (playerRemainingTries) {
                case 6 -> playerInterface.updateHangmanImage("C:\\Users\\maksy\\IdeaProjects\\HangmanGameWithGui\\src\\main\\java\\game\\gui\\img\\Hangman_1.png");
                case 5 -> playerInterface.updateHangmanImage("C:\\Users\\maksy\\IdeaProjects\\HangmanGameWithGui\\src\\main\\java\\game\\gui\\img\\Hangman_2.png");
                case 4 -> playerInterface.updateHangmanImage("C:\\Users\\maksy\\IdeaProjects\\HangmanGameWithGui\\src\\main\\java\\game\\gui\\img\\Hangman_3.png");
                case 3 -> playerInterface.updateHangmanImage("C:\\Users\\maksy\\IdeaProjects\\HangmanGameWithGui\\src\\main\\java\\game\\gui\\img\\Hangman_4.png");
                case 2 -> playerInterface.updateHangmanImage("C:\\Users\\maksy\\IdeaProjects\\HangmanGameWithGui\\src\\main\\java\\game\\gui\\img\\Hangman_5.png");
                case 1 -> playerInterface.updateHangmanImage("C:\\Users\\maksy\\IdeaProjects\\HangmanGameWithGui\\src\\main\\java\\game\\gui\\img\\Hangman_6.png");
                case 0 -> playerInterface.updateHangmanImage("C:\\Users\\maksy\\IdeaProjects\\HangmanGameWithGui\\src\\main\\java\\game\\gui\\img\\Hangman_7.png");
            }
        }
    }
}
