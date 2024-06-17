package server;

import game.controller.HangmanController;
import game.gui.GameChatInterface;
import players.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class HangmanServer {
    private static final int HANGMAN_SERVER_PORT = 1234;
    private static final List<Player> players = Collections.synchronizedList(new LinkedList<>());
    private static final Map<String, List<Player>> activeSessions = new HashMap<>();
    private static int createdSessionsNumber = 0;
    private ServerSocket serverSocket;

    public HangmanServer() {
        try {
            serverSocket = new ServerSocket(HANGMAN_SERVER_PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void runServer() {
        try {
            while (true) {
                String sessionId = String.format("HS%d", createdSessionsNumber + 1);
                Socket clientSocket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                String playerNickname = reader.readLine();
                Player player = new Player(playerNickname, clientSocket, writer, reader);
                player.setChatInterface(new GameChatInterface(player));
                players.add(player);

                synchronized (players) {
                    if (players.size() >= 2) {
                        Player player1 = players.removeFirst();
                        Player player2 = players.removeFirst();
                        activeSessions.put(sessionId, List.of(player1, player2));
                        new HangmanController(player1, player2).start();
                        createdSessionsNumber++;
                    }
                }

                HangmanClientHandler handler = new HangmanClientHandler(player);
                handler.setSessionId(sessionId);
                handler.start();
            }
        } catch (IOException e) {
            System.out.println("There was a problem starting the server!");
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    System.out.println("There was a problem closing the server!");
                }
            }
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static List<Player> getPlayers() {
        return players;
    }

    public static Map<String, List<Player>> getActiveSessions() {
        return activeSessions;
    }

    public static void main(String[] args) {
        HangmanServer server = new HangmanServer();
        server.runServer();
    }
}
