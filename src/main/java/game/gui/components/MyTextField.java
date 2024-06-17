package game.gui.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MyTextField extends JTextField {
    public MyTextField(boolean focusable, boolean editable, Color backgroundColor, Color foregroundColor, Border border, Font font) {
        this(focusable, editable, backgroundColor, foregroundColor, border);
        setFont(font);
    }

    public MyTextField(boolean focusable, boolean editable, Dimension size, Color backgroundColor, Color foregroundColor, Border border) {
        setFocusable(focusable);
        setEditable(editable);
        setPreferredSize(size);
        setBackground(backgroundColor);
        setForeground(foregroundColor);
        setBorder(border);
        setHorizontalAlignment(JTextField.CENTER);
    }

    public MyTextField(boolean focusable, boolean editable, Color backgroundColor, Color foregroundColor, Border border) {
        setFocusable(focusable);
        setEditable(editable);
        setBackground(backgroundColor);
        setForeground(foregroundColor);
        setBorder(border);
    }


    public MyTextField(Dimension size, Color backgroundColor, Color foregroundColor, Border border) {
        this(size, backgroundColor);
        setForeground(foregroundColor);
        setBorder(border);
    }

    public MyTextField(Dimension size, Color backgroundColor) {
        setPreferredSize(size);
        setBackground(backgroundColor);
    }
}
