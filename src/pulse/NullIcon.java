package pulse;

import javax.swing.*;
import java.awt.*;


public final class NullIcon implements Icon {

    private int width;
    private int height;

    public NullIcon() {
        this(0, 0);
    }

    public NullIcon(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getIconHeight() {
        return height;
    }

    public int getIconWidth() {
        return width;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
    }

}