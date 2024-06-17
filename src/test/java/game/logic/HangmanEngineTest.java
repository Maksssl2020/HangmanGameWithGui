package game.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import players.AdminPlayer;
import players.GuessingPlayer;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HangmanEngineTest {

    private HangmanEngine hangmanEngine;
    private GuessingPlayer hangmanPlayer;
    private AdminPlayer hangmanAdmin;

    @BeforeEach
    void setUp() {
        hangmanPlayer = new GuessingPlayer("Hangman2020");
        hangmanAdmin = new AdminPlayer("Frank2020");
        hangmanEngine = new HangmanEngine(hangmanPlayer, hangmanAdmin);
    }

    @Test
    void testPassingWordToGuess() {
        hangmanEngine.setWordToGuess("building");
        assertThat(hangmanEngine.getWordToGuess().getChosenWord()).isEqualTo("BUILDING");
    }

    @Test
    void testCreatingHiddenWord() {
        hangmanEngine.setWordToGuess("building");
        String createdHiddenWord = hangmanEngine.getHiddenWord();
        assertThat(createdHiddenWord.length()).isEqualTo("building".length());
    }

    @Test
    void testEnteringOneCorrectLetter() {
        hangmanEngine.setWordToGuess("building");
        String resultMessage = hangmanEngine.guessLetterInHiddenWord("d");
        assertThat(resultMessage).isEqualTo("Hangman2020 entered correct letter: 'D'!");
    }

    @Test
    void testEnteringOneIncorrectLetter() {
        hangmanEngine.setWordToGuess("building");
        String resultMessage = hangmanEngine.guessLetterInHiddenWord("p");
        assertThat(resultMessage).isEqualTo("Hangman2020 has 6 tries left!");
    }

    @Test
    void testDisplayingHiddenWordAfterOneCorrectGuess() {
        hangmanEngine.setWordToGuess("building");
        hangmanEngine.guessLetterInHiddenWord("u");
        String hiddenWord = hangmanEngine.getHiddenWord();
        assertThat(hiddenWord.charAt(1)).isEqualTo('U');
    }

    @Test
    void testDisplayingHiddenWordAfterIncorrectGuess() {
        hangmanEngine.setWordToGuess("building");
        hangmanEngine.guessLetterInHiddenWord("p");
        String hiddenWord = hangmanEngine.getHiddenWord();
        assertThat((hiddenWord.contains("p"))).isFalse();
    }

    @Test
    void testCheckingGameResultWhenFewLettersHaveBeenGuessed() {
        hangmanEngine.setWordToGuess("flower");
        hangmanEngine.guessLetterInHiddenWord("o");
        hangmanEngine.guessLetterInHiddenWord("w");
        assertThat(hangmanEngine.checkGameResult()).isEqualTo("Word is not guessed!");
    }

    @Test
    void testGuessedAllCorrectLetters() {
        hangmanEngine.setWordToGuess("dogs");
        hangmanEngine.guessLetterInHiddenWord("d");
        hangmanEngine.guessLetterInHiddenWord("g");
        hangmanEngine.guessLetterInHiddenWord("o");
        hangmanEngine.guessLetterInHiddenWord("s");
        assertThat(hangmanEngine.checkGameResult()).isEqualTo("Word has been guessed! Player won!");
    }

    @Test
    void testPlayerDoNotHaveAnyTry() {
        hangmanEngine.setWordToGuess("microscope");
        hangmanEngine.guessLetterInHiddenWord("l");
        hangmanEngine.guessLetterInHiddenWord("l");
        hangmanEngine.guessLetterInHiddenWord("l");
        hangmanEngine.guessLetterInHiddenWord("l");
        hangmanEngine.guessLetterInHiddenWord("l");
        hangmanEngine.guessLetterInHiddenWord("l");
        String result = hangmanEngine.guessLetterInHiddenWord("l");

        assertThat(result).isEqualTo("Hangman2020 has 0 tries left!");
    }

    @Test
    void testEnteringLetterThatIsAlreadyGuessed() {
        hangmanEngine.setWordToGuess("microscope");
        hangmanEngine.guessLetterInHiddenWord("s");
        String result = hangmanEngine.guessLetterInHiddenWord("s");

        assertThat(result).isEqualTo("'S' is already guessed!");
    }

    @Test
    void testClearingWordToGuess() {
        hangmanEngine.setWordToGuess("flowers");
        hangmanEngine.clearWordToGuess();

        assertThat(hangmanEngine.getWordToGuess().getChosenWord()).isNull();
        assertThat(hangmanEngine.getHiddenWord()).isEmpty();
    }

    @Test
    void testDrawingLetterForAHint() {
        String wordToGuess = "organization";
        hangmanEngine.setWordToGuess(wordToGuess);
        Set<String> drawnLetters = new HashSet<>();
        Set<Character> wordToGuessUniqueLetters = new HashSet<>();

        for (Character letter : wordToGuess.toCharArray()) {
            wordToGuessUniqueLetters.add(letter);
        }

        for (int i = 0; i < 100; i++) {
            drawnLetters.add(hangmanEngine.drawnLetterForAHint());
        }

        assertThat(wordToGuessUniqueLetters.size()).isEqualTo(drawnLetters.size());
    }
}