package game.gui;

import players.Player;
import server.HangmanClientHandler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameChatInterface extends TextAreaWindow {
    private HangmanClientHandler hangmanClientHandler;
    private Player chatPlayer;

    public GameChatInterface(Player chatPlayer) {
        this.chatPlayer = chatPlayer;
        setTitle("Hangman Chat Console");
        actionButton.setText("SEND");
        actionButton.addActionListener(e -> sendMessage());
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    sendMessage();
                }
            }
        });
    }

    public void gameServerMessage(String message) {
        textArea.append(String.format("Hangman-Engine: %s\n", message));
    }

    public void gamePlayerMessage(String playerNickname, String message) {
        textArea.append(String.format("%s: %s\n", playerNickname, message));
    }

    private void sendMessage() {
        String textToSend = getInputField().getText();

        if (!textToSend.isEmpty()) {
            getChatPlayer().getOut().println(textToSend);
            getInputField().setText("");
        }
    }

    public HangmanClientHandler getHangmanClientHandler() {
        return hangmanClientHandler;
    }

    public void setHangmanClientHandler(HangmanClientHandler hangmanClientHandler) {
        this.hangmanClientHandler = hangmanClientHandler;
    }

    public Player getChatPlayer() {
        return chatPlayer;
    }

    public void setChatPlayer(Player chatPlayer) {
        this.chatPlayer = chatPlayer;
    }
}
