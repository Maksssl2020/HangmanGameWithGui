package game.gui.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class MyButton extends JButton {
    public MyButton(String text, Dimension size, boolean focusable, Color backgroundColor, Color foregroundColor, Border border, ActionListener actionListener) {
        this(text, size, focusable, backgroundColor, foregroundColor, border);
        addActionListener(actionListener);
    }

    public MyButton(String text, Dimension size, boolean focusable, Color backgroundColor, Color foregroundColor, Border border) {
        this(text, size, focusable);
        setBackground(backgroundColor);
        setForeground(foregroundColor);
        setBorder(border);
    }

    public MyButton(boolean focusable, Color backgroundColor, Color foregroundColor, Border border, Font font) {
        setFocusable(focusable);
        setBackground(backgroundColor);
        setForeground(foregroundColor);
        setBorder(border);
        setFont(font);
    }

    public MyButton(String text, Dimension size, boolean focusable, Color backgroundColor, Color foregroundColor) {
        this(text, size, focusable);
        setBackground(backgroundColor);
        setForeground(foregroundColor);
    }


    public MyButton(String text, Dimension size, boolean focusable) {
        this(text, size);
        setFocusable(focusable);
    }

    public MyButton(String text, Dimension size) {
        setText(text);
        setPreferredSize(size);
    }
}
