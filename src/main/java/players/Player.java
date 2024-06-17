package players;

import game.gui.GameChatInterface;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player {
    private String nickname;
    private Socket playerSocket;
    private PrintWriter out;
    private BufferedReader in;
    private GameChatInterface chatInterface;

    protected Player(String nickname) {
        this.nickname = nickname;
    }

    public Player(String nickname, Socket playerSocket, PrintWriter out, BufferedReader in) {
        this.nickname = nickname;
        this.playerSocket = playerSocket;
        this.out = out;
        this.in = in;
    }

    public GameChatInterface getChatInterface() {
        return chatInterface;
    }

    public void setChatInterface(GameChatInterface chatInterface) {
        this.chatInterface = chatInterface;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public Socket getPlayerSocket() {
        return playerSocket;
    }

    public void setPlayerSocket(Socket playerSocket) {
        this.playerSocket = playerSocket;
    }

    @Override
    public String toString() {
        return nickname;
    }
}
