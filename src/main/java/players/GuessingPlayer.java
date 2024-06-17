package players;

import game.gui.GuessingPlayerInterface;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GuessingPlayer extends Player {
    private int score;
    private int tries;
    private boolean isGaveUp;
    private GuessingPlayerInterface playerInterface;

    public GuessingPlayer(String nickname) {
        super(nickname);
    }

    public GuessingPlayer(String nickname, Socket playerSocket, PrintWriter out, BufferedReader in) {
        super(nickname, playerSocket, out, in);
        isGaveUp = false;
        playerInterface = new GuessingPlayerInterface();
    }

    public GuessingPlayer(Player player) {
        super(player.getNickname(), player.getPlayerSocket(), player.getOut(), player.getIn());
        setChatInterface(player.getChatInterface());
        isGaveUp = false;
        playerInterface = new GuessingPlayerInterface();
    }

    public void addPoints(int amountOfPoints) {
        score += amountOfPoints;
    }

    public void incorrectAnswer() {
        tries--;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTries() {
        return tries;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }

    public boolean isGaveUp() {
        return isGaveUp;
    }

    public void setGaveUp(boolean gaveUp) {
        isGaveUp = gaveUp;
    }

    public GuessingPlayerInterface getPlayerInterface() {
        return playerInterface;
    }

    public void setPlayerInterface(GuessingPlayerInterface playerInterface) {
        this.playerInterface = playerInterface;
    }
}

