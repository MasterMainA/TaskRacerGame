import java.awt.*;

public class Spectator {
    private final Color BODY_COLOR;
    private final Color CLOTHES_COLOR;
    private final Color HEAD_COLOR;
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public Spectator(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.BODY_COLOR = generateRandomClothesColor();
        this.CLOTHES_COLOR = generateRandomClothesColor();
        this.HEAD_COLOR = generateRandomSkinColor();
    }

    public void draw(Graphics2D g2d) {
        // Тело
        g2d.setColor(BODY_COLOR);
        g2d.fillOval(x, y + height / 3, width, height * 2 / 3);

        // Одежда
        g2d.setColor(CLOTHES_COLOR);
        g2d.fillRect(x, y + height / 2, width, height / 3);

        // Голова
        g2d.setColor(HEAD_COLOR);
        int headSize = height / 2;
        g2d.fillOval(x + (width - headSize) / 2, y, headSize, headSize);

        // глаза
        g2d.setColor(Color.BLACK);
        int eyeSize = headSize / 5;
        g2d.fillOval(x + (width - headSize) / 2 + headSize / 3 - eyeSize / 2,
                y + headSize / 3, eyeSize, eyeSize);
        g2d.fillOval(x + (width - headSize) / 2 + headSize * 2 / 3 - eyeSize / 2,
                y + headSize / 3, eyeSize, eyeSize);
    }

    private Color generateRandomClothesColor() {
        Color[] clothesColors = {
                new Color(200, 50, 50),
                new Color(50, 50, 200),
                new Color(50, 200, 50),
                new Color(200, 200, 50),
                new Color(200, 50, 200),
                new Color(50, 200, 200),
                new Color(150, 100, 50)
        };
        return clothesColors[(int)(Math.random() * clothesColors.length)];
    }

    private Color generateRandomSkinColor() {
        Color[] skinColors = {
                new Color(255, 220, 177),
                new Color(240, 184, 160),
                new Color(210, 150, 120),
                new Color(180, 120, 90),
                new Color(150, 100, 80)
        };
        return skinColors[(int)(Math.random() * skinColors.length)];
    }
}