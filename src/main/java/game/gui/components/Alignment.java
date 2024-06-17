package game.gui.components;

import javax.swing.*;

public enum Alignment {
    TOP,
    BOTTOM,
    CENTER,
    LEFT,
    RIGHT;

    public int getAlignment() {
        return switch (this) {
            case TOP -> SwingConstants.TOP;
            case BOTTOM -> SwingConstants.BOTTOM;
            case CENTER -> SwingConstants.CENTER;
            case LEFT -> SwingConstants.LEFT;
            case RIGHT -> SwingConstants.RIGHT;
        };
    }
}
