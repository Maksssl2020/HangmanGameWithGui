package game.logic;

public class HangmanWord {
    private String chosenWord;
    private int wordPoints;

    public HangmanWord() {
    }

    public HangmanWord(String chosenWord) {
        this.chosenWord = chosenWord.toUpperCase();
        assignPointsToWordDependsOnItsLength();
    }

    private void assignPointsToWordDependsOnItsLength() {
        if (chosenWord.length() < 4) {
            wordPoints = 1;
        } else if (chosenWord.length() < 6) {
            wordPoints = 2;
        } else if (chosenWord.length() < 8) {
            wordPoints = 3;
        } else if (chosenWord.length() < 10) {
            wordPoints = 4;
        } else {
            wordPoints = 5;
        }
    }

    public boolean isLetterInTheWord(String letter) {
        return chosenWord.contains(letter.toUpperCase());
    }

    public int getChosenWordLength() {
        return chosenWord.length();
    }

    public String getChosenWord() {
        return chosenWord;
    }

    public void setChosenWord(String chosenWord) {
        this.chosenWord = chosenWord;
    }

    public int getWordPoints() {
        return wordPoints;
    }

    public void setWordPoints(int wordPoints) {
        this.wordPoints = wordPoints;
    }
}
