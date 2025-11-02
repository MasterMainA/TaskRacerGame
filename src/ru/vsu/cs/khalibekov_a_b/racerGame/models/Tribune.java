package ru.vsu.cs.khalibekov_a_b.racerGame.models;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tribune {
    private final Color TRIBUNE_COLOR = new Color(80, 80, 80);
    private final Color SEAT_COLOR = new Color(200, 200, 200);
    private final Color GUARDRAIL_COLOR = new Color(180, 180, 180);

    private final int SEAT_ROWS = 5;
    private final int SEAT_COLS = 30;
    private final int SEAT_WIDTH = 11;
    private final int SEAT_HEIGHT = 11;

    private List<Spectator> spectators = new ArrayList<>();

    public Tribune() {}

    public void draw(Graphics gr, double panelWidth, double panelHeight) {
        Graphics2D g2d = (Graphics2D) gr;

        if (spectators.isEmpty()) {
            createSpectators(panelWidth, panelHeight);
        }

        int x, y;

        x = (int) (panelWidth * 0.5 - (panelHeight * 0.7) / 2);
        y = (int) (panelHeight * 0.05);
        drawHorizontalTribune(g2d, x, y, (int)(panelHeight * 0.7), true);

        x = (int) (panelWidth * 0.15 - panelHeight * 0.1);
        y = (int) (panelHeight * 0.15);
        drawVerticalTribune(g2d, x, y, (int)(panelHeight * 0.7), true);

        x = (int) (panelWidth * 0.5 - (panelHeight * 0.7) / 2);
        y = (int) (panelHeight * 0.85);
        drawHorizontalTribune(g2d, x, y, (int)(panelHeight * 0.7), false);

        x = (int) (panelWidth * 0.85);
        y = (int) (panelHeight * 0.15);
        drawVerticalTribune(g2d, x, y, (int)(panelHeight * 0.7), false);

        for (Spectator spectator : spectators) {
            spectator.draw(g2d);
        }
    }

    private void createSpectators(double panelWidth, double panelHeight) {
        spectators.clear();

        createHorizontalSpectators(panelWidth, panelHeight, true);
        createHorizontalSpectators(panelWidth, panelHeight, false);
        createVerticalSpectators(panelWidth, panelHeight, true);
        createVerticalSpectators(panelWidth, panelHeight, false);
    }

    private void createHorizontalSpectators(double panelWidth, double panelHeight, boolean isTop) {
        int seatSpacing = 4;
        int x = (int) (panelWidth * 0.5 - (panelHeight * 0.7) / 2);
        int y = (int) (isTop ? panelHeight * 0.05 : panelHeight * 0.85);
        int width = (int)(panelHeight * 0.7);
        int tribuneSize = 80;

        int totalSeatsWidth = SEAT_COLS * SEAT_WIDTH + (SEAT_COLS - 1) * seatSpacing;
        int horizontalMargin = (width - totalSeatsWidth) / 2;

        for (int row = 0; row < SEAT_ROWS; row++) {
            for (int col = 0; col < SEAT_COLS; col++) {
                if (Math.random() > 0.3) {
                    int seatX = x + horizontalMargin + col * (SEAT_WIDTH + seatSpacing);
                    int seatY;

                    if (isTop) {
                        seatY = y + tribuneSize - (row + 1) * (SEAT_HEIGHT + seatSpacing) - 5;
                    } else {
                        seatY = y + row * (SEAT_HEIGHT + seatSpacing) + 5;
                    }

                    int spectatorWidth = SEAT_WIDTH - 2;
                    int spectatorHeight = SEAT_HEIGHT + 4;
                    int spectatorX = seatX + 1;
                    int spectatorY = seatY - 3;

                    spectators.add(new Spectator(spectatorX, spectatorY, spectatorWidth, spectatorHeight));
                }
            }
        }
    }

    private void createVerticalSpectators(double panelWidth, double panelHeight, boolean isLeft) {
        int seatSpacing = 4;
        int x = (int) (isLeft ? panelWidth * 0.15 - panelHeight * 0.1 : panelWidth * 0.85);
        int y = (int) (panelHeight * 0.15);
        int height = (int)(panelHeight * 0.7);
        int tribuneSize = 80;

        int totalSeatsHeight = SEAT_COLS * SEAT_HEIGHT + (SEAT_COLS - 1) * seatSpacing;
        int verticalMargin = (height - totalSeatsHeight) / 2;

        for (int row = 0; row < SEAT_COLS; row++) {
            for (int col = 0; col < SEAT_ROWS; col++) {
                if (Math.random() > 0.3) {
                    int seatY = y + verticalMargin + row * (SEAT_HEIGHT + seatSpacing);
                    int seatX;

                    if (isLeft) {
                        seatX = x + tribuneSize - (col + 1) * (SEAT_WIDTH + seatSpacing) - 5;
                    } else {
                        seatX = x + col * (SEAT_WIDTH + seatSpacing) + 9;
                    }

                    int spectatorWidth = SEAT_WIDTH - 2;
                    int spectatorHeight = SEAT_HEIGHT + 4;
                    int spectatorX = seatX + 1;
                    int spectatorY = seatY - 3;

                    spectators.add(new Spectator(spectatorX, spectatorY, spectatorWidth, spectatorHeight));
                }
            }
        }
    }

    private void drawHorizontalTribune(Graphics2D g2d, int x, int y, int width, boolean isTop) {
        int tribuneSize = 80;

        g2d.setColor(TRIBUNE_COLOR);
        g2d.fillRect(x, y, width, tribuneSize);

        g2d.setColor(new Color(70, 70, 70));
        for (int i = 0; i < width; i += 18) {
            g2d.drawLine(x + i, y, x + i, y + tribuneSize);
        }

        drawHorizontalSeats(g2d, x, y, width, tribuneSize, isTop);

        g2d.setColor(GUARDRAIL_COLOR);
        if (isTop) {
            g2d.fillRect(x, y + tribuneSize - 6, width, 6);
        } else {
            g2d.fillRect(x, y, width, 6);
        }
    }

    private void drawVerticalTribune(Graphics2D g2d, int x, int y, int height, boolean isLeft) {
        int tribuneSize = 80;

        g2d.setColor(TRIBUNE_COLOR);
        g2d.fillRect(x, y, tribuneSize, height);

        g2d.setColor(new Color(70, 70, 70));
        for (int i = 0; i < height; i += 18) {
            g2d.drawLine(x, y + i, x + tribuneSize, y + i);
        }

        drawVerticalSeats(g2d, x, y, tribuneSize, height, isLeft);

        g2d.setColor(GUARDRAIL_COLOR);
        if (isLeft) {
            g2d.fillRect(x + tribuneSize - 6, y, 6, height);
        } else {
            g2d.fillRect(x, y, 6, height);
        }
    }

    private void drawHorizontalSeats(Graphics2D g2d, int x, int y, int width, int height, boolean isTop) {
        g2d.setColor(SEAT_COLOR);

        int seatSpacing = 4;
        int totalSeatsWidth = SEAT_COLS * SEAT_WIDTH + (SEAT_COLS - 1) * seatSpacing;
        int horizontalMargin = (width - totalSeatsWidth) / 2;

        for (int row = 0; row < SEAT_ROWS; row++) {
            for (int col = 0; col < SEAT_COLS; col++) {
                int seatX = x + horizontalMargin + col * (SEAT_WIDTH + seatSpacing);
                int seatY;

                if (isTop) {
                    seatY = y + height - (row + 1) * (SEAT_HEIGHT + seatSpacing) - 5;
                } else {
                    seatY = y + row * (SEAT_HEIGHT + seatSpacing) + 9;
                }

                g2d.fillRect(seatX, seatY, SEAT_WIDTH, SEAT_HEIGHT);

                g2d.setColor(new Color(150, 150, 150));
                if (isTop) {
                    g2d.fillRect(seatX, seatY + SEAT_HEIGHT, SEAT_WIDTH, 3);
                } else {
                    g2d.fillRect(seatX, seatY - 3, SEAT_WIDTH, 3);
                }
                g2d.setColor(SEAT_COLOR);
            }
        }
    }

    private void drawVerticalSeats(Graphics2D g2d, int x, int y, int width, int height, boolean isLeft) {
        g2d.setColor(SEAT_COLOR);

        int seatSpacing = 4;
        int totalSeatsHeight = SEAT_COLS * SEAT_HEIGHT + (SEAT_COLS - 1) * seatSpacing;
        int verticalMargin = (height - totalSeatsHeight) / 2;

        for (int row = 0; row < SEAT_COLS; row++) {
            for (int col = 0; col < SEAT_ROWS; col++) {
                int seatY = y + verticalMargin + row * (SEAT_HEIGHT + seatSpacing);
                int seatX;

                if (isLeft) {
                    seatX = x + width - (col + 1) * (SEAT_WIDTH + seatSpacing) - 5;
                } else {
                    seatX = x + col * (SEAT_WIDTH + seatSpacing) + 9;
                }

                g2d.fillRect(seatX, seatY, SEAT_WIDTH, SEAT_HEIGHT);

                g2d.setColor(new Color(150, 150, 150));
                if (isLeft) {
                    g2d.fillRect(seatX + SEAT_WIDTH, seatY, 3, SEAT_HEIGHT);
                } else {
                    g2d.fillRect(seatX - 3, seatY, 3, SEAT_HEIGHT);
                }
                g2d.setColor(SEAT_COLOR);
            }
        }
    }
}