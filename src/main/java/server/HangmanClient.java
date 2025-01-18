package server;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HangmanClient extends Thread {
    private static final  int SERVER_PORT = 1234;
    private static final String SERVER_HOST = "localhost";
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public HangmanClient() {
        try {
            socket = new Socket(SERVER_HOST, SERVER_PORT);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            String playerNickname;

            do {
                playerNickname = JOptionPane.showInputDialog("Enter your nickname:");
            } while (playerNickname.isEmpty());

            writer.println(playerNickname);
            String serverResponse;

            while (!socket.isClosed()) {
                serverResponse = reader.readLine();
                if (serverResponse == null) {
                    break;
                }
                writer.println(serverResponse);
            }
        } catch (IOException e) {
            System.out.println("There was a problem connecting to the server!");
        } finally {
            try {
                writer.close();
                reader.close();
                socket.close();
                this.interrupt();
            } catch (IOException e) {
                System.out.println("There was a problem closing the socket!");
            }
        }
    }

    public static void main(String[] args) {
        new HangmanClient().start();
    }
}
