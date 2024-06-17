package game.listeners;

import game.controller.HangmanController;
import game.gui.AdminPlayerInterface;
import game.gui.ConfirmationWindow;

import javax.swing.*;
import java.awt.event.*;

public class EndGameButtonListener implements ActionListener {
    private HangmanController controller;
    private AdminPlayerInterface adminInterface;

    public EndGameButtonListener(HangmanController controller) {
        this.controller = controller;
        adminInterface = controller.getAdminInterface();
    }

    @Override
    public void actionPerformed(ActionEvent endGameEvent) {
        ConfirmationWindow endGameConfirmationWindow = new ConfirmationWindow(adminInterface, "End Game Window" ,"Are you sure you want to end the game?");
        JButton yesButton = endGameConfirmationWindow.getYesButton();
        yesButton.addActionListener(new EndGameConfirmationButtonListener(controller, controller::closeGame));

        endGameConfirmationWindow.setVisible(true);
    }
}
