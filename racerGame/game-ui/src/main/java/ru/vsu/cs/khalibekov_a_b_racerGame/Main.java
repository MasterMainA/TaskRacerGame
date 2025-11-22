package ru.vsu.cs.khalibekov_a_b_racerGame;

import ru.vsu.cs.khalibekov_a_b_racerGame.gui.StartMenuPanel;
import javax.swing.*;

<<<<<<< HEAD
/**
 * Точка входа в программу.
 * Создает главное окно приложения.
 *
 * @author Khalibekov A.B.
 */
=======
>>>>>>> 01a6e8d5470b89a5d9177e2d319653e989cf2832
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