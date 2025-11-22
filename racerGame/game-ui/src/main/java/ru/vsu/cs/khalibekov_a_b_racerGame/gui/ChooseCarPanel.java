package ru.vsu.cs.khalibekov_a_b_racerGame.gui;

import ru.vsu.cs.khalibekov_a_b_racerGame.models.Car;

import javax.swing.*;
import java.awt.*;

<<<<<<< HEAD
/**
 * Панель выбора автомобиля. Позволяет игроку выбрать один из трех типов машин.
 *
 * @author Khalibekov A.B.
 */
=======
>>>>>>> 01a6e8d5470b89a5d9177e2d319653e989cf2832
public class ChooseCarPanel extends JPanel {
    private JButton goBack;
    private JButton car1;
    private JButton car2;
    private JButton car3;
    private JFrame parentFrame;
    private StartMenuPanel startMenuPanel;

<<<<<<< HEAD
    /**
     * Создает панель выбора автомобиля.
     *
     * @param parentFrame родительское окно для навигации
     * @param startMenuPanel панель главного меню для возврата и установки выбранной машины
     */
=======
>>>>>>> 01a6e8d5470b89a5d9177e2d319653e989cf2832
    public ChooseCarPanel(JFrame parentFrame, StartMenuPanel startMenuPanel) {
        this.parentFrame = parentFrame;
        this.startMenuPanel = startMenuPanel;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new GridLayout(4, 1, 0, 20));
        setBorder(BorderFactory.createEmptyBorder(200, 400, 200, 400));

        this.goBack = createMenuButton("Вернуться назад");
        this.car1 = createMenuButton("Машина 1");
        this.car2 = createMenuButton("Машина 2");
        this.car3 = createMenuButton("Машина 3");

        add(goBack);
        add(car1);
        add(car2);
        add(car3);

        setBackground(new Color(81, 81, 81));
        setupEventListeners();
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setPreferredSize(new Dimension(75, 25));
        button.setBackground(new Color(188, 188, 188));
        return button;
    }

    private void setupEventListeners() {
        goBack.addActionListener(e -> {
            switchToPanel(startMenuPanel);
        });

        car1.addActionListener(e -> selectCar(1));
        car2.addActionListener(e -> selectCar(2));
        car3.addActionListener(e -> selectCar(3));
    }

<<<<<<< HEAD
    /**
     * Выбирает автомобиль указанного типа и возвращается в главное меню.
     *
     * @param carType тип автомобиля (1, 2 или 3)
     */
=======
>>>>>>> 01a6e8d5470b89a5d9177e2d319653e989cf2832
    private void selectCar(int carType) {
        Car selectedCar;
        switch (carType) {
            case 1:
                selectedCar = new Car(700, 600);
                break;
            case 2:
                selectedCar = new Car(700, 600);
                // TODO: новый тип машины
                break;
            case 3:
                selectedCar = new Car(700, 600);
                // TODO: новый тип машины
                break;
            default:
                selectedCar = new Car(700, 600);
        }

        startMenuPanel.setSelectedCar(selectedCar);

        JOptionPane.showMessageDialog(this, "Машина " + carType + " выбрана!");

        switchToPanel(startMenuPanel);
    }

    private void switchToPanel(JPanel newPanel) {
        parentFrame.getContentPane().removeAll();
        parentFrame.getContentPane().add(newPanel);
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}