package ru.vsu.cs.khalibekov_a_b.racerGame;

import ru.vsu.cs.khalibekov_a_b.racerGame.GUI.StartMenuPanel;

import javax.swing.*;
import java.awt.*;

public class RacingGame extends JFrame {
    private StartMenuPanel startMenuPanel;

    public RacingGame() {
        this.startMenuPanel = new StartMenuPanel();
        getContentPane().add(startMenuPanel);
    }

    public void changePanel(JPanel newPanel) {
        Container contentPane = getContentPane();
        contentPane.removeAll();
        contentPane.add(newPanel);
        contentPane.revalidate();
        contentPane.repaint();
    }

    public static void main(String[] args) {
        RacingGame window = new RacingGame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
