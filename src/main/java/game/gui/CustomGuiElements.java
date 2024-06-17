package game.gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CustomGuiElements {
    public static final Color CUSTOM_BLACK_COLOR = new Color(6, 6, 6);
    public static final Color CUSTOM_DARK_GRAY_COLOR = new Color(44, 44, 47);
    public static final Color CUSTOM_EMERALD_COLOR = new Color(56, 204, 174);
    public static final Color CUSTOM_LIGHT_GRAY_COLOR = new Color(170, 170, 174);
    public static final Color CUSTOM_WHITE_COLOR = new Color(255, 255, 255);
    public static final EmptyBorder PADDING = new EmptyBorder(10, 10, 10, 10);
    public static final Border ROUNDED_BORDER = BorderFactory.createLineBorder(CUSTOM_EMERALD_COLOR, 2, true);
    public static final CompoundBorder COMPOUND_BORDERS = new CompoundBorder(ROUNDED_BORDER, PADDING);
}
