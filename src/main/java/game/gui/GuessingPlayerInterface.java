package game.gui;

import game.gui.components.MyButton;
import game.gui.components.MyTextField;
import game.listeners.OpenScoreboardListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GuessingPlayerInterface extends PlayerBasicInterface {
    private final String LETTERS = "ABCDEFGHIJKLMNOPQRTSUVWXYZ";

    private JButton[] letterButtons = new JButton[LETTERS.length()];
    private JPanel letterButtonsPanel, additionalInfoPanel;
    private JTextField hangmanTextArea;

    private final JTextField[] additionalInfoFields = new JTextField[2];
    private final JButton[] additionalOptionButtons = new JButton[2];
    private final Border ROUNDED_BORDER_FOR_LETTER_BUTTONS = BorderFactory.createLineBorder(CustomGuiElements.CUSTOM_LIGHT_GRAY_COLOR, 1, true);

    public GuessingPlayerInterface() {
        initLetterButtons();
        initHangmanTextArea();
        initAdditionalPanelInfo();

        add(letterButtonsPanel);
        add(hangmanTextArea);
        add(additionalInfoPanel);
        setVisible(true);
    }

    private void initLetterButtons() {
        GridLayout buttonsPanelLayout = new GridLayout(7, 4);
        letterButtonsPanel = new JPanel(buttonsPanelLayout);
        letterButtonsPanel.setBackground(CustomGuiElements.CUSTOM_DARK_GRAY_COLOR);
        letterButtonsPanel.setBounds(305, 10, 285, 350);
        letterButtonsPanel.setBorder(CustomGuiElements.COMPOUND_BORDERS);

        for (int i = 0; i < LETTERS.length(); i++) {
            String letter = String.valueOf(LETTERS.charAt(i));
            letterButtons[i] = new MyButton(letter, new Dimension(68, 85), false, CustomGuiElements.CUSTOM_DARK_GRAY_COLOR, CustomGuiElements.CUSTOM_WHITE_COLOR, ROUNDED_BORDER_FOR_LETTER_BUTTONS);
            letterButtonsPanel.add(letterButtons[i]);
        }
    }

    private void initHangmanTextArea() {
        hangmanTextArea = new MyTextField(false, false, CustomGuiElements.CUSTOM_DARK_GRAY_COLOR, CustomGuiElements.CUSTOM_WHITE_COLOR, CustomGuiElements.COMPOUND_BORDERS, new Font(Font.MONOSPACED, Font.PLAIN, 42));
        hangmanTextArea.setBounds(10, 370, 580, 75);
        hangmanTextArea.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void initAdditionalPanelInfo() {
        additionalInfoPanel = new JPanel();
        additionalInfoPanel.setBackground(null);
        additionalInfoPanel.setBounds(9, 455, 580, 80);

        for (int i = 0; i < additionalInfoFields.length; i++) {
            additionalInfoFields[i] = new MyTextField(false, false, CustomGuiElements.CUSTOM_DARK_GRAY_COLOR, CustomGuiElements.CUSTOM_WHITE_COLOR, CustomGuiElements.COMPOUND_BORDERS, new Font(Font.MONOSPACED, Font.PLAIN, 16));

            if (i == 0) {
                additionalInfoFields[i].setText("Word Points:\n0");
                additionalInfoFields[i].setPreferredSize(new Dimension(200, 70));
            } else {
                additionalInfoFields[i].setText("Tries: 7");
                additionalInfoFields[i].setPreferredSize(new Dimension(115, 70));
            }

            additionalInfoPanel.add(additionalInfoFields[i]);
        }

        for (int i = 0; i < additionalOptionButtons.length; i++) {
            additionalOptionButtons[i] = new MyButton(false, Color.DARK_GRAY, CustomGuiElements.CUSTOM_WHITE_COLOR, CustomGuiElements.COMPOUND_BORDERS, new Font(Font.MONOSPACED, Font.PLAIN, 16));

            if (i == 0) {
                additionalOptionButtons[i].setText("Give Up");
                additionalOptionButtons[i].setPreferredSize(new Dimension(120, 70));
            } else {
                additionalOptionButtons[i].setText("Scoreboard");
                additionalOptionButtons[i].setPreferredSize(new Dimension(125, 70));
                additionalOptionButtons[i].addActionListener(new OpenScoreboardListener());
            }

            additionalInfoPanel.add(additionalOptionButtons[i]);
        }
    }

    public void restoreLetterButtonsDefaultBackgroundColor() {
        for (JButton letterButton : letterButtons) {
            letterButton.setBackground(CustomGuiElements.CUSTOM_DARK_GRAY_COLOR);
        }
    }

    public JTextField[] getAdditionalInfoFields() {
        return additionalInfoFields;
    }

    public JTextField getWordPointsField() {
        return additionalInfoFields[0];
    }

    public JTextField getTriesLeftField() {
        return additionalInfoFields[1];
    }

    public JPanel getAdditionalInfoPanel() {
        return additionalInfoPanel;
    }

    public void setAdditionalInfoPanel(JPanel additionalInfoPanel) {
        this.additionalInfoPanel = additionalInfoPanel;
    }

    public JButton[] getAdditionalOptionButtons() {
        return additionalOptionButtons;
    }

    public JTextField getHangmanTextArea() {
        return hangmanTextArea;
    }

    public void setHangmanTextArea(JTextField hangmanTextArea) {
        this.hangmanTextArea = hangmanTextArea;
    }

    public JButton[] getLetterButtons() {
        return letterButtons;
    }

    public void setLetterButtons(JButton[] letterButtons) {
        this.letterButtons = letterButtons;
    }

    public JPanel getLetterButtonsPanel() {
        return letterButtonsPanel;
    }

    public void setLetterButtonsPanel(JPanel letterButtonsPanel) {
        this.letterButtonsPanel = letterButtonsPanel;
    }

    public String getLETTERS() {
        return LETTERS;
    }

    public Border getROUNDED_BORDER_FOR_LETTER_BUTTONS() {
        return ROUNDED_BORDER_FOR_LETTER_BUTTONS;
    }

    public static void main(String[] args) {
        new GuessingPlayerInterface();
    }
}
