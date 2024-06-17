package game.gui;

import game.gui.components.Alignment;
import game.gui.components.MyButton;
import game.gui.components.MyLabel;
import game.gui.components.MyTextField;
import game.listeners.ClearButtonListener;
import game.listeners.OpenScoreboardListener;

import javax.swing.*;
import java.awt.*;

public class AdminPlayerInterface extends PlayerBasicInterface {
    private JPanel currentPlayerInfoPanel, adminOptionsPanelToManageWordToGuess, adminOptionsPanelToManageGame;
    private JLabel  playerNameLabel, playerScoreLabel, amountOfTriesLabel, wordToGuessLabel, giveHintLabel, startNewRoundLabel, openScoreboardLabel, endGameLabel;
    private JTextField currentPlayerName, currentPlayerScore, currentPlayerAmountOfTries, settingWordToGuessField;
    private JButton setWordButton, clearFieldButton, giveHintToPlayerButton, startNewRoundButton, openScoreboardButton, endGameButton;

    public AdminPlayerInterface() {
        initCurrentPlayerInfoPanel();
        initAdminOptionsPanel();
        initAdminOptionsPanelToManageGame();

        add(currentPlayerInfoPanel);
        add(adminOptionsPanelToManageWordToGuess);
        add(adminOptionsPanelToManageGame);
        setVisible(true);
    }

    public void initCurrentPlayerInfoPanel() {
        currentPlayerInfoPanel = new JPanel(new FlowLayout());
        currentPlayerInfoPanel.setBackground(CustomGuiElements.CUSTOM_DARK_GRAY_COLOR);
        currentPlayerInfoPanel.setBounds(305, 10 ,285, 190);
        currentPlayerInfoPanel.setBorder(CustomGuiElements.COMPOUND_BORDERS);

        initInfoLabels();
        initCurrentPlayerInfoTextFields();

        currentPlayerInfoPanel.add(playerNameLabel);
        currentPlayerInfoPanel.add(currentPlayerName);
        currentPlayerInfoPanel.add(playerScoreLabel);
        currentPlayerInfoPanel.add(amountOfTriesLabel);
        currentPlayerInfoPanel.add(currentPlayerScore);
        currentPlayerInfoPanel.add(currentPlayerAmountOfTries);
    }

    public void initInfoLabels() {
        playerNameLabel = new MyLabel("Current Player Nickname", new Dimension(250, 15), new Color(255,255,255), Alignment.CENTER, Alignment.BOTTOM);
        playerScoreLabel = new MyLabel("Player Score", new Dimension(120, 30), new Color(255,255,255), Alignment.CENTER,Alignment.BOTTOM);
        amountOfTriesLabel = new MyLabel("Tries Left", new Dimension(120, 30), new Color(255,255,255), Alignment.CENTER, Alignment.BOTTOM);
    }

    public void initCurrentPlayerInfoTextFields() {
        currentPlayerName = new MyTextField(false, false, new Dimension(255, 50), CustomGuiElements.CUSTOM_LIGHT_GRAY_COLOR, CustomGuiElements.CUSTOM_DARK_GRAY_COLOR, null);
        currentPlayerScore = new MyTextField(false, false, new Dimension(125, 50), CustomGuiElements.CUSTOM_LIGHT_GRAY_COLOR, CustomGuiElements.CUSTOM_DARK_GRAY_COLOR, null);
        currentPlayerAmountOfTries = new MyTextField(false, false, new Dimension(125, 50), CustomGuiElements.CUSTOM_LIGHT_GRAY_COLOR, CustomGuiElements.CUSTOM_DARK_GRAY_COLOR, null);
    }

    public void initAdminOptionsPanel() {
        adminOptionsPanelToManageWordToGuess = new JPanel(new FlowLayout());
        adminOptionsPanelToManageWordToGuess.setBounds(305, 210 ,285, 150);
        adminOptionsPanelToManageWordToGuess.setBackground(CustomGuiElements.CUSTOM_DARK_GRAY_COLOR);
        adminOptionsPanelToManageWordToGuess.setBorder(CustomGuiElements.ROUNDED_BORDER);

        initAdminPanelComponents();
    }

    public void initAdminPanelComponents() {
        wordToGuessLabel = new MyLabel("Set Word To Guess", new Dimension(250, 15), new Color(255,255,255), Alignment.CENTER, Alignment.BOTTOM);
        settingWordToGuessField = new MyTextField(new Dimension(255, 50), CustomGuiElements.CUSTOM_LIGHT_GRAY_COLOR);
        settingWordToGuessField.setHorizontalAlignment(Alignment.CENTER.getAlignment());
        setWordButton = new MyButton("SET", new Dimension(125, 50), false, Color.DARK_GRAY, CustomGuiElements.CUSTOM_WHITE_COLOR, CustomGuiElements.ROUNDED_BORDER);
        clearFieldButton = new MyButton("CLEAR", new Dimension(125, 50), false, Color.DARK_GRAY, CustomGuiElements.CUSTOM_WHITE_COLOR, CustomGuiElements.ROUNDED_BORDER, new ClearButtonListener(settingWordToGuessField));

        adminOptionsPanelToManageWordToGuess.add(wordToGuessLabel);
        adminOptionsPanelToManageWordToGuess.add(settingWordToGuessField);
        adminOptionsPanelToManageWordToGuess.add(setWordButton);
        adminOptionsPanelToManageWordToGuess.add(clearFieldButton);
    }

    public void initAdminOptionsPanelToManageGame() {
        adminOptionsPanelToManageGame = new JPanel(new FlowLayout());
        adminOptionsPanelToManageGame.setBounds(10, 400 ,580, 100);
        adminOptionsPanelToManageGame.setBackground(CustomGuiElements.CUSTOM_DARK_GRAY_COLOR);
        adminOptionsPanelToManageGame.setBorder(CustomGuiElements.ROUNDED_BORDER);

        initAdminOptionsPanelToManageGameLabels();
        adminOptionsPanelToManageGame.add(giveHintLabel);
        adminOptionsPanelToManageGame.add(startNewRoundLabel);
        adminOptionsPanelToManageGame.add(openScoreboardLabel);
        adminOptionsPanelToManageGame.add(endGameLabel);

        initAdminOptionsPanelToManageGameButtons();
        adminOptionsPanelToManageGame.add(giveHintToPlayerButton);
        adminOptionsPanelToManageGame.add(startNewRoundButton);
        adminOptionsPanelToManageGame.add(openScoreboardButton);
        adminOptionsPanelToManageGame.add(endGameButton);
    }

    public void initAdminOptionsPanelToManageGameLabels() {
        giveHintLabel = new MyLabel("Give Hint To Player", new Dimension(125, 20), new Color(255,255,255), Alignment.CENTER, Alignment.BOTTOM);
        startNewRoundLabel = new MyLabel("Start New Round", new Dimension(125, 20), new Color(255,255,255), Alignment.CENTER, Alignment.BOTTOM);
        openScoreboardLabel = new MyLabel("Open Scoreboard", new Dimension(125, 20), new Color(255,255,255), Alignment.CENTER, Alignment.BOTTOM);
        endGameLabel = new MyLabel("End Game", new Dimension(125, 20), new Color(255,255,255), Alignment.CENTER, Alignment.BOTTOM);
    }

    public void initAdminOptionsPanelToManageGameButtons() {
        giveHintToPlayerButton = new MyButton("HINT", new Dimension(125, 50), false, Color.DARK_GRAY, CustomGuiElements.CUSTOM_WHITE_COLOR, CustomGuiElements.ROUNDED_BORDER);
        startNewRoundButton = new MyButton("NEW ROUND", new Dimension(125, 50), false, Color.DARK_GRAY, CustomGuiElements.CUSTOM_WHITE_COLOR, CustomGuiElements.ROUNDED_BORDER);
        openScoreboardButton = new MyButton("SCOREBOARD", new Dimension(125, 50), false, Color.DARK_GRAY, CustomGuiElements.CUSTOM_WHITE_COLOR, CustomGuiElements.ROUNDED_BORDER, new OpenScoreboardListener());
        endGameButton = new MyButton("END GAME", new Dimension(125, 50), false, Color.DARK_GRAY, CustomGuiElements.CUSTOM_WHITE_COLOR, CustomGuiElements.ROUNDED_BORDER);
    }

    public JPanel getAdminOptionsPanelToManageGame() {
        return adminOptionsPanelToManageGame;
    }

    public void setAdminOptionsPanelToManageGame(JPanel adminOptionsPanelToManageGame) {
        this.adminOptionsPanelToManageGame = adminOptionsPanelToManageGame;
    }

    public JPanel getAdminOptionsPanelToManageWordToGuess() {
        return adminOptionsPanelToManageWordToGuess;
    }

    public void setAdminOptionsPanelToManageWordToGuess(JPanel adminOptionsPanelToManageWordToGuess) {
        this.adminOptionsPanelToManageWordToGuess = adminOptionsPanelToManageWordToGuess;
    }

    public JLabel getAmountOfTriesLabel() {
        return amountOfTriesLabel;
    }

    public void setAmountOfTriesLabel(JLabel amountOfTriesLabel) {
        this.amountOfTriesLabel = amountOfTriesLabel;
    }

    public JButton getClearFieldButton() {
        return clearFieldButton;
    }

    public void setClearFieldButton(JButton clearFieldButton) {
        this.clearFieldButton = clearFieldButton;
    }

    public JTextField getCurrentPlayerAmountOfTries() {
        return currentPlayerAmountOfTries;
    }

    public void setCurrentPlayerAmountOfTries(JTextField currentPlayerAmountOfTries) {
        this.currentPlayerAmountOfTries = currentPlayerAmountOfTries;
    }

    public JPanel getCurrentPlayerInfoPanel() {
        return currentPlayerInfoPanel;
    }

    public void setCurrentPlayerInfoPanel(JPanel currentPlayerInfoPanel) {
        this.currentPlayerInfoPanel = currentPlayerInfoPanel;
    }

    public JTextField getCurrentPlayerName() {
        return currentPlayerName;
    }

    public void setCurrentPlayerName(JTextField currentPlayerName) {
        this.currentPlayerName = currentPlayerName;
    }

    public JTextField getCurrentPlayerScore() {
        return currentPlayerScore;
    }

    public void setCurrentPlayerScore(JTextField currentPlayerScore) {
        this.currentPlayerScore = currentPlayerScore;
    }

    public JButton getEndGameButton() {
        return endGameButton;
    }

    public void setEndGameButton(JButton endGameButton) {
        this.endGameButton = endGameButton;
    }

    public JLabel getEndGameLabel() {
        return endGameLabel;
    }

    public void setEndGameLabel(JLabel endGameLabel) {
        this.endGameLabel = endGameLabel;
    }

    public JLabel getGiveHintLabel() {
        return giveHintLabel;
    }

    public void setGiveHintLabel(JLabel giveHintLabel) {
        this.giveHintLabel = giveHintLabel;
    }

    public JButton getGiveHintToPlayerButton() {
        return giveHintToPlayerButton;
    }

    public void setGiveHintToPlayerButton(JButton giveHintToPlayerButton) {
        this.giveHintToPlayerButton = giveHintToPlayerButton;
    }

    public JButton getOpenScoreboardButton() {
        return openScoreboardButton;
    }

    public void setOpenScoreboardButton(JButton openScoreboardButton) {
        this.openScoreboardButton = openScoreboardButton;
    }

    public JLabel getOpenScoreboardLabel() {
        return openScoreboardLabel;
    }

    public void setOpenScoreboardLabel(JLabel openScoreboardLabel) {
        this.openScoreboardLabel = openScoreboardLabel;
    }

    public JLabel getPlayerNameLabel() {
        return playerNameLabel;
    }

    public void setPlayerNameLabel(JLabel playerNameLabel) {
        this.playerNameLabel = playerNameLabel;
    }

    public JLabel getPlayerScoreLabel() {
        return playerScoreLabel;
    }

    public void setPlayerScoreLabel(JLabel playerScoreLabel) {
        this.playerScoreLabel = playerScoreLabel;
    }

    public JTextField getSettingWordToGuessField() {
        return settingWordToGuessField;
    }

    public void setSettingWordToGuessField(JTextField settingWordToGuessField) {
        this.settingWordToGuessField = settingWordToGuessField;
    }

    public JButton getSetWordButton() {
        return setWordButton;
    }

    public void setSetWordButton(JButton setWordButton) {
        this.setWordButton = setWordButton;
    }

    public JButton getStartNewRoundButton() {
        return startNewRoundButton;
    }

    public void setStartNewRoundButton(JButton startNewRoundButton) {
        this.startNewRoundButton = startNewRoundButton;
    }

    public JLabel getStartNewRoundLabel() {
        return startNewRoundLabel;
    }

    public void setStartNewRoundLabel(JLabel startNewRoundLabel) {
        this.startNewRoundLabel = startNewRoundLabel;
    }

    public JLabel getWordToGuessLabel() {
        return wordToGuessLabel;
    }

    public void setWordToGuessLabel(JLabel wordToGuessLabel) {
        this.wordToGuessLabel = wordToGuessLabel;
    }

    public static void main(String[] args) {
        new AdminPlayerInterface();
    }
}
