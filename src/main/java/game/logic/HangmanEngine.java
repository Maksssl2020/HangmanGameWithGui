package game.logic;

import players.AdminPlayer;
import players.GuessingPlayer;

import java.util.ArrayList;
import java.util.Random;

public class HangmanEngine {
    private HangmanWord wordToGuess = new HangmanWord();
    private StringBuilder hiddenWordToGuess;
    private GuessingPlayer hangmanGuessingPlayer;
    private AdminPlayer gameAdmin;

    public HangmanEngine(GuessingPlayer hangmanGuessingPlayer, AdminPlayer gameAdmin) {
        this.hangmanGuessingPlayer = hangmanGuessingPlayer;
        this.gameAdmin = gameAdmin;
        hangmanGuessingPlayer.setTries(7);
    }

    public void createHiddenWordToGuess() {
        hiddenWordToGuess = new StringBuilder();
        hiddenWordToGuess.append("_".repeat(wordToGuess.getChosenWordLength()));
    }

    public String guessLetterInHiddenWord(String guessedLetter) {
        if (hangmanGuessingPlayer.getTries() == 0) {
            return createMessageForPlayer(HangmanGuessingResults.ZERO_TRIES, guessedLetter);
        } else if (isEnteredLetterAlreadyGuessed(guessedLetter)) {
            return createMessageForPlayer(HangmanGuessingResults.ALREADY_GUESSED_LETTER, guessedLetter);
        } else if (wordToGuess.isLetterInTheWord(guessedLetter)) {
            showCorrectLetter(guessedLetter);
            return createMessageForPlayer(HangmanGuessingResults.CORRECT_ANSWER, guessedLetter);
        } else {
            hangmanGuessingPlayer.incorrectAnswer();
            return createMessageForPlayer(HangmanGuessingResults.INCORRECT_ANSWER, guessedLetter);
        }
    }

    public boolean isEnteredLetterAlreadyGuessed(String guessedLetter) {
        return hiddenWordToGuess.toString().contains(guessedLetter.toUpperCase());
    }

    private String createMessageForPlayer(HangmanGuessingResults guessingResults, String enteredLetter) {
        return switch (guessingResults) {
            case HangmanGuessingResults.ALREADY_GUESSED_LETTER -> String.format("'%S' is already guessed!", enteredLetter);
            case HangmanGuessingResults.CORRECT_ANSWER -> String.format("%s entered correct letter: '%S'!", hangmanGuessingPlayer.getNickname(), enteredLetter);
            case HangmanGuessingResults.INCORRECT_ANSWER -> {
                String tryWord = hangmanGuessingPlayer.getTries() == 1 ? "try" : "tries";
                yield String.format("%s has %d %s left!", hangmanGuessingPlayer.getNickname(), hangmanGuessingPlayer.getTries(), tryWord);
            }
            case HangmanGuessingResults.ZERO_TRIES -> String.format("%s has no more tries!", hangmanGuessingPlayer.getNickname());
        };
    }

    private void showCorrectLetter(String guessedLetter) {
        ArrayList<Integer> guessedLetterIndexes = getGuessedLetterIndex(guessedLetter.toUpperCase());

        for (int i = 0; i < hiddenWordToGuess.length(); i++) {
            if (guessedLetterIndexes.contains(i)) {
                hiddenWordToGuess.setCharAt(i, guessedLetter.toUpperCase().charAt(0));
            }
        }
    }

    public ArrayList<Integer> getGuessedLetterIndex(String guessedLetter) {
        ArrayList<Integer> letterIndexes = new ArrayList<>();

        for (int i = 0; i < wordToGuess.getChosenWord().length(); i++) {
            if (wordToGuess.getChosenWord().charAt(i) == guessedLetter.charAt(0)) {
                letterIndexes.add(i);
            }
        }

        return letterIndexes;
    }

    public String checkGameResult() {
        if (wordToGuess.getChosenWord() == null) {
            return "Word to guess is not set!";
        } else if (hangmanGuessingPlayer.getTries() == 0) {
            return  String.format("%s doesn't have any try! Player failed!", hangmanGuessingPlayer.getNickname());
        } else if (hangmanGuessingPlayer.isGaveUp()) {
            return "The guessing player gave up!";
        } else if (hiddenWordToGuess.toString().contains("_")) {
            return "Word is not guessed!";
        } else {
            return "Word has been guessed! Player won!";
        }
    }

    public String drawnLetterForAHint() {
        Random randomLetterIndex = new Random();
        String drawnLetter;
        int drawnIndex;

        do {
            drawnIndex = randomLetterIndex.nextInt(wordToGuess.getChosenWordLength());
            drawnLetter = String.valueOf(wordToGuess.getChosenWord().charAt(drawnIndex));
        } while (hiddenWordToGuess.toString().contains(drawnLetter));

        return drawnLetter;
    }

    public void clearGame() {
        clearWordToGuess();
        hangmanGuessingPlayer.setTries(7);
        hangmanGuessingPlayer.setGaveUp(false);
        gameAdmin.setAmountOfPossibleHints(3);
    }

    public void clearWordToGuess() {
        wordToGuess = new HangmanWord();
        hiddenWordToGuess = new StringBuilder();
    }

    public String getHiddenWord() {
        return hiddenWordToGuess.toString();
    }

    public void setWordToGuess(String wordToGuess) {
        this.wordToGuess = new HangmanWord(wordToGuess);
        createHiddenWordToGuess();
    }

    public HangmanWord getWordToGuess() {
        return wordToGuess;
    }

    public GuessingPlayer getHangmanGuessingPlayer() {
        return hangmanGuessingPlayer;
    }

    public void setHangmanGuessingPlayer(GuessingPlayer hangmanGuessingPlayer) {
        this.hangmanGuessingPlayer = hangmanGuessingPlayer;
    }

    public AdminPlayer getGameAdmin() {
        return gameAdmin;
    }

    public void setGameAdmin(AdminPlayer gameAdmin) {
        this.gameAdmin = gameAdmin;
    }
}
