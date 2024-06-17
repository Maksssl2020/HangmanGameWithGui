package game.controller;

import game.gui.AdminPlayerInterface;
import game.gui.GuessingPlayerInterface;
import game.listeners.*;
import game.logic.HangmanEngine;
import players.AdminPlayer;
import players.GuessingPlayer;
import players.Player;

import javax.swing.*;
import java.io.IOException;

public class HangmanController extends Thread {
    private GuessingPlayer hangmanGuessingPlayer;
    private AdminPlayer hangmanAdmin;
    private HangmanEngine hangmanEngine;
    private GuessingPlayerInterface guessingPlayerInterface;
    private AdminPlayerInterface adminInterface;

    public HangmanController(Player hangmanGuessingPlayer, Player hangmanAdmin) {
        this.hangmanGuessingPlayer = new GuessingPlayer(hangmanGuessingPlayer);
        this.hangmanAdmin = new AdminPlayer(hangmanAdmin);
        this.hangmanEngine = new HangmanEngine(this.hangmanGuessingPlayer, this.hangmanAdmin);
    }

    @Override
    public void run() {
        initPlayersInterfaces();
        initSetWordToGuessListener();
        initButtonsListeners();
        initNewRoundListener();
        initHintToPlayerListener();
    }

    public void initPlayersInterfaces() {
        guessingPlayerInterface = hangmanGuessingPlayer.getPlayerInterface();
        guessingPlayerInterface.getAdditionalOptionButtons()[0].addActionListener(new GiveUpButtonListener(this));

        adminInterface = hangmanAdmin.getAdminInterface();
        adminInterface.getEndGameButton().addActionListener(new EndGameButtonListener(this));
        adminInterface.getCurrentPlayerName().setText(hangmanGuessingPlayer.getNickname());
        adminInterface.getCurrentPlayerScore().setText("0");
        adminInterface.getCurrentPlayerAmountOfTries().setText("7");
    }

    public void initSetWordToGuessListener() {
        adminInterface.getSetWordButton().addActionListener(new SetWordToGuessListener(this));
        adminInterface.getSettingWordToGuessField().addKeyListener(new SetWordToGuessListener(this));
    }

    public void initNewRoundListener() {
        adminInterface.getStartNewRoundButton().addActionListener(new NewRoundListener(this));
    }

    public void initHintToPlayerListener() {
        adminInterface.getGiveHintToPlayerButton().addActionListener(new HintToPlayerListener(this));
    }

    public void initButtonsListeners() {
        LetterButtonListener letterButtonListener = new LetterButtonListener(this);
        for (JButton letterButton : guessingPlayerInterface.getLetterButtons()) {
            letterButton.addActionListener(letterButtonListener);
        }
    }

    public AdminPlayerInterface getAdminInterface() {
        return adminInterface;
    }

    public void setAdminInterface(AdminPlayerInterface adminInterface) {
        this.adminInterface = adminInterface;
    }

    public HangmanEngine getHangmanEngine() {
        return hangmanEngine;
    }

    public void setHangmanEngine(HangmanEngine hangmanEngine) {
        this.hangmanEngine = hangmanEngine;
    }

    public GuessingPlayerInterface getHangmanPlayerInterface() {
        return guessingPlayerInterface;
    }

    public void setHangmanPlayerInterface(GuessingPlayerInterface guessingPlayerInterface) {
        this.guessingPlayerInterface = guessingPlayerInterface;
    }

    public AdminPlayer getHangmanAdmin() {
        return hangmanAdmin;
    }

    public void setHangmanAdmin(AdminPlayer hangmanAdmin) {
        this.hangmanAdmin = hangmanAdmin;
    }

    public GuessingPlayer getHangmanGuessingPlayer() {
        return hangmanGuessingPlayer;
    }

    public void setHangmanGuessingPlayer(GuessingPlayer hangmanGuessingPlayer) {
        this.hangmanGuessingPlayer = hangmanGuessingPlayer;
    }

    public void closeGame() {
        guessingPlayerInterface.dispose();
        adminInterface.dispose();
        hangmanGuessingPlayer.getChatInterface().dispose();
        hangmanAdmin.getChatInterface().dispose();

        try {
            hangmanGuessingPlayer.getPlayerSocket().close();
            hangmanAdmin.getPlayerSocket().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.interrupt();
    }
}
