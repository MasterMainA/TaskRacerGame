package ru.vsu.cs.khalibekov_a_b_racerGame.keyHandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Обработчик клавиатурного ввода для управления автомобилем в игре.
 * Отслеживает нажатия клавиш W, A, S, D и предоставляет методы для получения
 * значений ускорения и поворота.
 *
 * @author Khalibekov A.B.
 */
public class KeyHandler implements KeyListener {
    public boolean WPressed;
    public boolean SPressed;
    public boolean APressed;
    public boolean DPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            WPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            SPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            APressed = true;
        }
        if (code == KeyEvent.VK_D) {
            DPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            WPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            SPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            APressed = false;
        }
        if (code == KeyEvent.VK_D) {
            DPressed = false;
        }
    }

    public double getAccelerationInput() {
        if (WPressed && !SPressed) return 1.0;
        if (SPressed && !WPressed) return -1.0;
        return 0.0;
    }

    public double getSteeringInput() {
        if (APressed && !DPressed) return -1.0;
        if (DPressed && !APressed) return 1.0;
        return 0.0;
    }
}