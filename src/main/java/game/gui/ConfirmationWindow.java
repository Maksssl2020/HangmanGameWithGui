package game.gui;

import javax.swing.*;
import java.awt.*;

public class ConfirmationWindow extends JDialog {
    private JButton yesButton, noButton;

    public ConfirmationWindow(JFrame parentFrame, String windowTitle, String windowMessage) {
        super(parentFrame, windowTitle, true);
        setSize(new Dimension(300, 115));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parentFrame);

        JPanel confirmationPanel = new JPanel();
        confirmationPanel.setPreferredSize(new Dimension(300, 115));

        JLabel messageLabel = new JLabel(windowMessage);

        yesButton = new JButton("Yes");
        yesButton.setPreferredSize(new Dimension(100, 25));
        noButton = new JButton("No");
        noButton.setPreferredSize(new Dimension(100, 25));
        noButton.addActionListener((cancelActionEvent) -> dispose());

        confirmationPanel.add(messageLabel);
        confirmationPanel.add(yesButton);
        confirmationPanel.add(noButton);
        getContentPane().add(confirmationPanel);
    }

    public JButton getNoButton() {
        return noButton;
    }

    public void setNoButton(JButton noButton) {
        this.noButton = noButton;
    }

    public JButton getYesButton() {
        return yesButton;
    }

    public void setYesButton(JButton yesButton) {
        this.yesButton = yesButton;
    }
}
