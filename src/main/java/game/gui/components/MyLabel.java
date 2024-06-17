package game.gui.components;

import javax.swing.*;
import java.awt.*;

public class MyLabel extends JLabel {
    public MyLabel(String text, Dimension size, Color foregroundColor, Alignment horizontalAlignment, Alignment verticalAlignment) {
        setText(text);
        setPreferredSize(size);
        setForeground(foregroundColor);
        setHorizontalAlignment(horizontalAlignment.getAlignment());
        setVerticalAlignment(verticalAlignment.getAlignment());
    }
}
