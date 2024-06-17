package game.gui;

import game.gui.components.MyButton;
import game.gui.components.MyTextField;

import javax.swing.*;
import java.awt.*;

public class TextAreaWindow extends JDialog {
    protected JTextArea textArea;
    protected JScrollPane scrollPane;
    protected JPanel bottomPanel;
    protected JTextField inputField;
    protected JButton actionButton;

    public TextAreaWindow() {
        setSize(500, 450);
        setBackground(CustomGuiElements.CUSTOM_BLACK_COLOR);
        setResizable(false);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setBackground(CustomGuiElements.CUSTOM_BLACK_COLOR);
        textArea.setForeground(CustomGuiElements.CUSTOM_EMERALD_COLOR);
        scrollPane = new JScrollPane(textArea);

        bottomPanel = new JPanel(new FlowLayout());
        inputField = new MyTextField(new Dimension(375, 50), CustomGuiElements.CUSTOM_DARK_GRAY_COLOR, CustomGuiElements.CUSTOM_LIGHT_GRAY_COLOR, CustomGuiElements.ROUNDED_BORDER);
        actionButton = new MyButton("", new Dimension(75, 50), false, CustomGuiElements.CUSTOM_DARK_GRAY_COLOR, CustomGuiElements.CUSTOM_LIGHT_GRAY_COLOR, CustomGuiElements.ROUNDED_BORDER);

        bottomPanel.setBorder(CustomGuiElements.PADDING);
        bottomPanel.setBackground(CustomGuiElements.CUSTOM_DARK_GRAY_COLOR);
        bottomPanel.add(inputField);
        bottomPanel.add(actionButton);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    public JButton getActionButton() {
        return actionButton;
    }

    public void setActionButton(JButton actionButton) {
        this.actionButton = actionButton;
    }

    public JPanel getBottomPanel() {
        return bottomPanel;
    }

    public void setBottomPanel(JPanel bottomPanel) {
        this.bottomPanel = bottomPanel;
    }

    public JTextField getInputField() {
        return inputField;
    }

    public void setInputField(JTextField inputField) {
        this.inputField = inputField;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }
}
