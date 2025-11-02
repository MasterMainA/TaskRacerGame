package ru.vsu.cs.khalibekov_a_b.racerGame.GUI;

import ru.vsu.cs.khalibekov_a_b.racerGame.RacingGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseCarPanel extends JPanel {
    private JButton goBack;
    private JButton car1;
    private JButton car2;
    private JButton car3;


    public ChooseCarPanel() {
        setLayout(new GridLayout(4, 1, 0, 20));

        setBorder(BorderFactory.createEmptyBorder(200, 400, 200, 400));

        this.goBack = new JButton("Вернуться назад");
        this.car1 = new JButton("Машина 1");
        this.car2 = new JButton("Машина 2");
        this.car3 = new JButton("Машина 3");

        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        goBack.setFont(buttonFont);
        car1.setFont(buttonFont);
        car2.setFont(buttonFont);
        car3.setFont(buttonFont);

        Dimension buttonSize = new Dimension(75, 25);
        goBack.setPreferredSize(buttonSize);
        car1.setPreferredSize(buttonSize);
        car2.setPreferredSize(buttonSize);
        car3.setPreferredSize(buttonSize);

        Color buttonColor = new Color(188, 188, 188);
        goBack.setBackground(buttonColor);
        car1.setBackground(buttonColor);
        car2.setBackground(buttonColor);
        car3.setBackground(buttonColor);

        add(goBack);
        add(car1);
        add(car2);
        add(car3);

        setBackground(new Color(81, 81, 81));

        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RacingGame mainFrame = (RacingGame) SwingUtilities.getWindowAncestor(ChooseCarPanel.this);
                StartMenuPanel startMenuPanel = new StartMenuPanel();

                mainFrame.changePanel(startMenuPanel);
            }
        });

        car1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        car2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        car3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}