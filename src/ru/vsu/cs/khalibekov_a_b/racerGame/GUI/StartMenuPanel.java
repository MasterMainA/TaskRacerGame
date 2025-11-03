package ru.vsu.cs.khalibekov_a_b.racerGame.GUI;

import ru.vsu.cs.khalibekov_a_b.racerGame.RacingGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenuPanel extends JPanel {
    private JButton start;
    private JButton chooseCar;
    private JButton chooseTrack;
    private JButton exit;


    public StartMenuPanel() {
        setLayout(new GridLayout(4, 1, 0, 20));

        setBorder(BorderFactory.createEmptyBorder(200, 400, 200, 400));

        this.start = new JButton("Старт!");
        this.chooseCar = new JButton("Выбор машины");
        this.chooseTrack = new JButton("Выбор трека");
        this.exit = new JButton("Выход");

        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        start.setFont(buttonFont);
        chooseCar.setFont(buttonFont);
        chooseTrack.setFont(buttonFont);
        exit.setFont(buttonFont);

        Dimension buttonSize = new Dimension(75, 25);
        start.setPreferredSize(buttonSize);
        chooseCar.setPreferredSize(buttonSize);
        chooseTrack.setPreferredSize(buttonSize);
        exit.setPreferredSize(buttonSize);

        Color buttonColor = new Color(188, 188, 188);
        start.setBackground(buttonColor);
        chooseCar.setBackground(buttonColor);
        chooseTrack.setBackground(buttonColor);
        exit.setBackground(buttonColor);

        add(start);
        add(chooseCar);
        add(chooseTrack);
        add(exit);

        setBackground(new Color(81, 81, 81));

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RacingGame mainFrame = (RacingGame) SwingUtilities.getWindowAncestor(StartMenuPanel.this);
                GameplayPanel gameplayPanel = new GameplayPanel();

                mainFrame.changePanel(gameplayPanel);
                gameplayPanel.startGame();
            }
        });

        chooseCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RacingGame mainFrame = (RacingGame) SwingUtilities.getWindowAncestor(StartMenuPanel.this);
                ChooseCarPanel chooseCarPanel = new ChooseCarPanel();

                mainFrame.changePanel(chooseCarPanel);
            }
        });

        chooseTrack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RacingGame mainFrame = (RacingGame) SwingUtilities.getWindowAncestor(StartMenuPanel.this);
                ChooseTrackPanel chooseTrackPanel = new ChooseTrackPanel();
                mainFrame.changePanel(chooseTrackPanel);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}