package game.logic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HangmanWordTest {

    @Test
    void testGivingWordLessThanThreeLettersLong() {
        HangmanWord shortWord = new HangmanWord("cat");
        assertThat(shortWord.getWordPoints()).isEqualTo(1);
    }

    @Test
    void testGivingWordLessThanSixLettersLong() {
        HangmanWord shortWord = new HangmanWord("house");
        assertThat(shortWord.getWordPoints()).isEqualTo(2);
    }

    @Test
    void testGivingWordLessThanEightLettersLong() {
        HangmanWord shortWord = new HangmanWord("flowers");
        assertThat(shortWord.getWordPoints()).isEqualTo(3);
    }

    @Test
    void testGivingWordLessThanTenLettersLong() {
        HangmanWord shortWord = new HangmanWord("buildings");
        assertThat(shortWord.getWordPoints()).isEqualTo(4);
    }

    @Test
    void testGivingWordGreaterThanTenLettersLong() {
        HangmanWord shortWord = new HangmanWord("organization");
        assertThat(shortWord.getWordPoints()).isEqualTo(5);
    }

    @Test
    void testCheckingCorrectLetter() {
        HangmanWord shortWord = new HangmanWord("house");
        assertThat(shortWord.isLetterInTheWord("H")).isTrue();
    }

    @Test
    void testCheckingIncorrectLetter() {
        HangmanWord shortWord = new HangmanWord("house");
        assertThat(shortWord.isLetterInTheWord("p")).isFalse();
    }

    @Test
    void testGettingChosenWordLength() {
        HangmanWord shortWord = new HangmanWord("house");
        assertThat(shortWord.getChosenWordLength()).isEqualTo(5);
    }
}