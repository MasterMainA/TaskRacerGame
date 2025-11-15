package ru.vsu.cs.khalibekov_a_b_racerGame;

import ru.vsu.cs.khalibekov_a_b_racerGame.gui.StartMenuPanel;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame mainWindow = new JFrame("Task Racer Game");
            mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
            
            StartMenuPanel startMenu = new StartMenuPanel(mainWindow);
            mainWindow.getContentPane().add(startMenu);

            mainWindow.setLocationRelativeTo(null);
            mainWindow.setVisible(true);
        });
    }
}