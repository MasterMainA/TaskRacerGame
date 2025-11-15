package ru.vsu.cs.khalibekov_a_b_racerGame.models;

import java.util.ArrayList;
import java.util.List;

public class Tribune {
    private final int TRIBUNE_COLOR = 0x505050;
    private final int SEAT_COLOR = 0xC8C8C8;
    private final int GUARDRAIL_COLOR = 0xB4B4B4;

    private final int SEAT_ROWS = 5;
    private final int SEAT_COLS = 30;
    private final int SEAT_WIDTH = 11;
    private final int SEAT_HEIGHT = 11;

    private List<Spectator> spectators = new ArrayList<>();

    public Tribune() {}

    public void createSpectators(double panelWidth, double panelHeight) {
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


    public List<Spectator> getSpectators() {
        return spectators;
    }

    public int getTribuneColor() { return TRIBUNE_COLOR; }
    public int getSeatColor() { return SEAT_COLOR; }
    public int getGuardrailColor() { return GUARDRAIL_COLOR; }
    public int getSeatRows() { return SEAT_ROWS; }
    public int getSeatCols() { return SEAT_COLS; }
    public int getSeatWidth() { return SEAT_WIDTH; }
    public int getSeatHeight() { return SEAT_HEIGHT; }
}