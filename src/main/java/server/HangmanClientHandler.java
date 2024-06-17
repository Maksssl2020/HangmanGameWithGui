package server;

import players.Player;

import java.io.IOException;

public class HangmanClientHandler extends Thread {
    private Player client;
    private String sessionId;

    public HangmanClientHandler(Player client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            client.getChatInterface().gameServerMessage(String.format("Hello, %s!", client.getNickname()));
            while (!client.getChatInterface().getTextArea().getText().contains("ended the game")) {
                String readMessage = client.getIn().readLine();
                HangmanServer
                        .getActiveSessions()
                        .get(sessionId)
                        .forEach(player -> player.getChatInterface().gamePlayerMessage(client.getNickname(), readMessage));
            }
        } catch (IOException e) {
            System.out.println("There was a problem reading the chat message!");
            interrupt();
        }
    }

    public Player getClient() {
        return client;
    }

    public void setClient(Player client) {
        this.client = client;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
