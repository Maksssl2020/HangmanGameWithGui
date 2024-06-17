package game.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearButtonListener implements ActionListener {
    private JTextField fieldToClear;

    public ClearButtonListener(JTextField fieldToClear) {
        this.fieldToClear = fieldToClear;
    }

    @Override
    public void actionPerformed(ActionEvent clearEvent) {
        fieldToClear.setText("");
    }
}
