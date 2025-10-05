import java.awt.*;

public class Tribune {
    private final Color COLOR_1 = new Color(112, 112, 112);
    private final Color COLOR_2 = new Color(165, 165, 165);

    public Tribune() {}

    public void draw(Graphics gr, double panelWidth, double panelHeight) {
        Graphics2D g2d = (Graphics2D) gr;
        int x;
        int y;

        x = (int) (panelWidth * 0.15);
        y = (int) (panelHeight  * 0.05);
        drawPart(g2d, panelWidth, panelHeight, x, y, true);

        x = (int) (panelWidth * 0.15 - panelHeight * 0.1);
        y = (int) (panelHeight * 0.15);
        drawPart(g2d, panelWidth, panelHeight, x, y, false);

        x = (int) (panelWidth * 0.15);
        y = (int) (panelHeight * 0.85);
        drawPart(g2d, panelWidth, panelHeight, x, y, true);

        x = (int) (panelWidth * 0.85);
        y = (int) (panelHeight * 0.15);
        drawPart(g2d, panelWidth, panelHeight, x, y, false);
    }

    private void drawPart(Graphics2D g2d, double panelWidth, double panelHeight, int x, int y, boolean isHorizontally) {
        int width;
        int height;

        if (isHorizontally) {
            width = (int) (panelWidth * 0.7);
            height = (int) (panelHeight * 0.1);
        } else {
            width = (int) (panelHeight * 0.1);
            height = (int) (panelHeight * 0.7);
        }

        g2d.drawRect(x, y, width, height);
    }
}
