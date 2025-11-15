package ru.vsu.cs.khalibekov_a_b_racerGame.models;

/**
 * Represents a spectator in the game
 * Contains only data - NO rendering code
 */
public class Spectator {
    public interface ColorSchema {
        int getSkinColor();
        int getClothesColor();
    }

    private final int BODY_COLOR;
    private final int CLOTHES_COLOR;
    private final int HEAD_COLOR;
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

    private int generateRandomClothesColor() {
        int[] clothesColors = {
                0xC83232, // Red
                0x3232C8, // Blue
                0x32C832, // Green
                0xC8C832, // Yellow
                0xC832C8, // Magenta
                0x32C8C8, // Cyan
                0x966432  // Brown
        };
        return clothesColors[(int)(Math.random() * clothesColors.length)];
    }

    private int generateRandomSkinColor() {
        int[] skinColors = {
                0xFFDCB1, // Light
                0xF0B8A0, // Medium Light
                0xD29678, // Medium
                0xB4785A, // Medium Dark
                0x966450  // Dark
        };
        return skinColors[(int)(Math.random() * skinColors.length)];
    }


    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getBodyColor() { return BODY_COLOR; }
    public int getClothesColor() { return CLOTHES_COLOR; }
    public int getHeadColor() { return HEAD_COLOR; }
}