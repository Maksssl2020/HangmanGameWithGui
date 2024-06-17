package game.listeners;

import game.controller.HangmanController;
import game.gui.ConfirmationWindow;
import game.gui.GuessingPlayerInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GiveUpButtonListener implements ActionListener {
    private GuessingPlayerInterface playerInterface;
    private HangmanController controller;

    public GiveUpButtonListener(HangmanController controller) {
        this.controller = controller;
        playerInterface = controller.getHangmanPlayerInterface();
    }

    @Override
    public void actionPerformed(ActionEvent playerGiveUpEvent) {
        ConfirmationWindow giveUpConfirmationWindow = new ConfirmationWindow(playerInterface, "Give Up Confirmation", "Are you sure you want to give up?");
        JButton yesButton = giveUpConfirmationWindow.getYesButton();
        yesButton.addActionListener(new GiveUpConfirmationListener(controller, giveUpConfirmationWindow));

        giveUpConfirmationWindow.setVisible(true);
    }
}
