package game.gui;

import javax.swing.*;

public class PlayerBasicInterface extends JFrame {
    protected JLabel hangmanLabelImage = new JLabel();
    protected ImageIcon image = new ImageIcon("C:\\Users\\maksy\\IdeaProjects\\HangmanGameWithGui\\src\\main\\java\\game\\gui\\img\\Hangman_0.png");
    protected JPanel hangmanPanel;

    public PlayerBasicInterface() {
        super("Hangman Game");
        setSize(610,580);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(CustomGuiElements.CUSTOM_BLACK_COLOR);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        hangmanLabelImage.setIcon(image);
        hangmanLabelImage.setHorizontalTextPosition(JLabel.CENTER);

        hangmanPanel = new JPanel();
        hangmanPanel.setBackground(CustomGuiElements.CUSTOM_DARK_GRAY_COLOR);
        hangmanPanel.add(hangmanLabelImage);
        hangmanPanel.setBounds(10, 10, 285, 350);
        hangmanPanel.setBorder(CustomGuiElements.COMPOUND_BORDERS);

        add(hangmanPanel);
    }

    public void updateHangmanImage(String imageUrl) {
        hangmanLabelImage.setIcon(new ImageIcon(imageUrl));
    }

    public JLabel getHangmanLabelImage() {
        return hangmanLabelImage;
    }

    public void setHangmanLabelImage(JLabel hangmanLabelImage) {
        this.hangmanLabelImage = hangmanLabelImage;
    }

    public JPanel getHangmanPanel() {
        return hangmanPanel;
    }

    public void setHangmanPanel(JPanel hangmanPanel) {
        this.hangmanPanel = hangmanPanel;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public static void main(String[] args) {
        new PlayerBasicInterface();
    }
}
