package game.helpers;

import java.util.function.Consumer;

public class CountingDown extends Thread {
    private String adminNick;
    private Consumer<String> sendMessageToGuessingPlayer;
    private Consumer<String> sendMessageToAdminPlayer;
    private Runnable endGameRunnable;

    public CountingDown(String adminNick, Consumer<String> sendMessageToGuessingPlayer, Consumer<String> sendMessageToAdminPlayer, Runnable endGameRunnable) {
        this.adminNick = adminNick;
        this.sendMessageToGuessingPlayer = sendMessageToGuessingPlayer;
        this.sendMessageToAdminPlayer = sendMessageToAdminPlayer;
        this.endGameRunnable = endGameRunnable;
    }

    @Override
    public void run() {
        sendMessagesToPLayers(String.format("%s, ended the game!", adminNick));

        for (int i = 3; i >= 0; i--) {
            try {
                Thread.sleep(1000);
                sendMessagesToPLayers(String.format("Game will end in %d...", i));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        endGameRunnable.run();
    }

    private void sendMessagesToPLayers(String message) {
        sendMessageToGuessingPlayer.accept(message);
        sendMessageToAdminPlayer.accept(message);
    }
}
