package ru.vsu.cs.khalibekov_a_b_racerGame.gui;

import ru.vsu.cs.khalibekov_a_b_racerGame.models.Car;
import ru.vsu.cs.khalibekov_a_b_racerGame.models.RaceTrack;

import javax.swing.*;
import java.awt.*;

<<<<<<< HEAD
/**
 * Панель главного меню игры. Содержит кнопки для начала игры, выбора машины,
 * выбора трека и выхода из приложения.
 *
 * @author Khalibekov A.B.
 */
=======
>>>>>>> 01a6e8d5470b89a5d9177e2d319653e989cf2832
public class StartMenuPanel extends JPanel {
    private JButton start;
    private JButton chooseCar;
    private JButton chooseTrack;
    private JButton exit;
    private JFrame parentFrame;

    private Car selectedCar;
    private RaceTrack selectedTrack;

<<<<<<< HEAD
    /**
     * Создает панель главного меню.
     *
     * @param parentFrame родительское окно для навигации между панелями
     */
=======
>>>>>>> 01a6e8d5470b89a5d9177e2d319653e989cf2832
    public StartMenuPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        this.selectedCar = new Car(700, 600);
        this.selectedTrack = new RaceTrack();

        initializeUI();
    }

    private void initializeUI() {
        setLayout(new GridLayout(4, 1, 0, 20));
        setBorder(BorderFactory.createEmptyBorder(200, 400, 200, 400));

        this.start = createMenuButton("Старт!");
        this.chooseCar = createMenuButton("Выбор машины");
        this.chooseTrack = createMenuButton("Выбор трека");
        this.exit = createMenuButton("Выход");

        add(start);
        add(chooseCar);
        add(chooseTrack);
        add(exit);

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
        start.addActionListener(e -> {
            GameplayPanel gameplayPanel = new GameplayPanel(parentFrame, selectedCar, selectedTrack);
            switchToPanel(gameplayPanel);
            gameplayPanel.startGame();
        });

        chooseCar.addActionListener(e -> {
            ChooseCarPanel chooseCarPanel = new ChooseCarPanel(parentFrame, this);
            switchToPanel(chooseCarPanel);
        });

        chooseTrack.addActionListener(e -> {
            ChooseTrackPanel chooseTrackPanel = new ChooseTrackPanel(parentFrame, this);
            switchToPanel(chooseTrackPanel);
        });

        exit.addActionListener(e -> System.exit(0));
    }

<<<<<<< HEAD
    /**
     * Устанавливает выбранный автомобиль для игры.
     *
     * @param car выбранный автомобиль
     */
=======
>>>>>>> 01a6e8d5470b89a5d9177e2d319653e989cf2832
    public void setSelectedCar(Car car) {
        this.selectedCar = car;
    }

<<<<<<< HEAD
    /**
     * Устанавливает выбранный трек для игры.
     *
     * @param track выбранный трек
     */
=======
>>>>>>> 01a6e8d5470b89a5d9177e2d319653e989cf2832
    public void setSelectedTrack(RaceTrack track) {
        this.selectedTrack = track;
    }

    public Car getSelectedCar() {
        return selectedCar;
    }

    public RaceTrack getSelectedTrack() {
        return selectedTrack;
    }

<<<<<<< HEAD
    /**
     * Смена панели.
     */
=======
>>>>>>> 01a6e8d5470b89a5d9177e2d319653e989cf2832
    private void switchToPanel(JPanel newPanel) {
        parentFrame.getContentPane().removeAll();
        parentFrame.getContentPane().add(newPanel);
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}