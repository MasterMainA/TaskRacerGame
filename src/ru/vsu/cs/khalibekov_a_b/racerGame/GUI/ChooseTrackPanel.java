package ru.vsu.cs.khalibekov_a_b.racerGame.GUI;

import ru.vsu.cs.khalibekov_a_b.racerGame.RacingGame;
import ru.vsu.cs.khalibekov_a_b.racerGame.models.trackCalculate.BasicTrackCalculator;
import ru.vsu.cs.khalibekov_a_b.racerGame.models.RaceTrack;

import javax.swing.*;
import java.awt.*;

public class ChooseTrackPanel extends JPanel {
    private JButton backButton;
    private JButton basicTrackButton;
    private JButton randomTrackButton;
    private JButton customTrackButton;
    private JButton previewButton;

    private JSlider aSlider;
    private JSlider bSlider;
    private JSlider mSlider;
    private JSlider nSlider;

    private JLabel aValueLabel;
    private JLabel bValueLabel;
    private JLabel mValueLabel;
    private JLabel nValueLabel;

    private JPanel previewPanel;
    private JPanel controlPanel;
    private JPanel parametersPanel;

    private RaceTrack previewTrack;
    private BasicTrackCalculator currentCalculator;

    public ChooseTrackPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(81, 81, 81));

        currentCalculator = new BasicTrackCalculator();
        previewTrack = new RaceTrack();
        previewTrack.setTrackCalculator(currentCalculator);

        initializeComponents();
        setupLayout();
        setupEventListeners();

        showControlPanel();
    }

    private void initializeComponents() {
        backButton = createStyledButton("Назад");
        basicTrackButton = createStyledButton("Базовый трек");
        randomTrackButton = createStyledButton("Случайный трек");
        customTrackButton = createStyledButton("Настроить параметры");
        previewButton = createStyledButton("Начать гонку");

        aSlider = createParameterSlider(10, 25, 20); // a: 1.0 - 2.5
        bSlider = createParameterSlider(5, 20, 10);  // b: 0.5 - 2.0
        mSlider = createParameterSlider(1, 4, 1);    // m: 1-4
        nSlider = createParameterSlider(1, 4, 1);    // n: 1-4

        aValueLabel = createValueLabel("2.0");
        bValueLabel = createValueLabel("1.0");
        mValueLabel = createValueLabel("1");
        nValueLabel = createValueLabel("1");

        previewPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (previewTrack != null) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    g2d.translate(0, -155);
                    previewTrack.drawForPreview(g);
                    g2d.translate(0, 155);
                }
            }
        };

        previewPanel.setPreferredSize(new Dimension(400, 300));
        previewPanel.setBackground(new Color(79, 144, 24));
        previewPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        controlPanel = createControlPanel();
        parametersPanel = createParametersPanel();
    }

    private void setupLayout() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(81, 81, 81));
        topPanel.add(backButton);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(81, 81, 81));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        centerPanel.add(previewPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        add(controlPanel, BorderLayout.SOUTH);
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBackground(new Color(81, 81, 81));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        panel.add(basicTrackButton);
        panel.add(randomTrackButton);
        panel.add(customTrackButton);
        panel.add(previewButton);

        return panel;
    }

    private JPanel createParametersPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(81, 81, 81));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel slidersPanel = new JPanel(new GridLayout(2, 2, 8, 10));
        slidersPanel.setBackground(new Color(81, 81, 81));

        slidersPanel.add(createSliderPanel("a:", aSlider, aValueLabel));
        slidersPanel.add(createSliderPanel("b:", bSlider, bValueLabel));
        slidersPanel.add(createSliderPanel("m:", mSlider, mValueLabel));
        slidersPanel.add(createSliderPanel("n:", nSlider, nValueLabel));

        panel.add(slidersPanel);

        return panel;
    }

    private JPanel createSliderPanel(String label, JSlider slider, JLabel valueLabel) {
        JPanel panel = new JPanel(new BorderLayout(2, 2));
        panel.setBackground(new Color(81, 81, 81));

        JLabel paramLabel = createParameterLabel(label);
        panel.add(paramLabel, BorderLayout.NORTH);
        panel.add(slider, BorderLayout.CENTER);

        JPanel valuePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        valuePanel.setBackground(new Color(81, 81, 81));
        valuePanel.add(valueLabel);
        panel.add(valuePanel, BorderLayout.SOUTH);

        return panel;
    }

    private void setupEventListeners() {
        backButton.addActionListener(e -> {
            if (parametersPanel.getParent() != null && parametersPanel.isVisible()) {
                showControlPanel();
            } else {
                goBackToMainMenu();
            }
        });

        basicTrackButton.addActionListener(e -> {
            setBasicTrack();
            showParametersPanel();
        });

        randomTrackButton.addActionListener(e -> {
            setRandomTrack();
            showParametersPanel();
        });

        customTrackButton.addActionListener(e -> showParametersPanel());

        previewButton.addActionListener(e -> startGameWithCurrentTrack());

        aSlider.addChangeListener(e -> updateParameterValues());
        bSlider.addChangeListener(e -> updateParameterValues());
        mSlider.addChangeListener(e -> updateParameterValues());
        nSlider.addChangeListener(e -> updateParameterValues());
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(188, 188, 188));
        button.setPreferredSize(new Dimension(200, 50));
        button.setFocusPainted(false);
        return button;
    }

    private JSlider createParameterSlider(int min, int max, int value) {
        JSlider slider = new JSlider(min, max, value);
        slider.setBackground(new Color(81, 81, 81));
        slider.setForeground(Color.WHITE);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }

    private JLabel createParameterLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(Color.WHITE);
        return label;
    }

    private JLabel createValueLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(Color.YELLOW);
        label.setPreferredSize(new Dimension(50, 20));
        return label;
    }

    private void updateParameterValues() {
        double aValue = aSlider.getValue() / 10.0;
        double bValue = bSlider.getValue() / 10.0;
        int mValue = mSlider.getValue();
        int nValue = nSlider.getValue();

        aValueLabel.setText(String.format("%.1f", aValue));
        bValueLabel.setText(String.format("%.1f", bValue));
        mValueLabel.setText(String.valueOf(mValue));
        nValueLabel.setText(String.valueOf(nValue));

        currentCalculator.setParameters(aValue, bValue, mValue, nValue);

        previewPanel.repaint();
    }

    private void setBasicTrack() {
        aSlider.setValue(20);
        bSlider.setValue(10);
        mSlider.setValue(1);
        nSlider.setValue(1);
        updateParameterValues();
    }

    private void setRandomTrack() {
        aSlider.setValue(10 + (int)(Math.random() * 16)); // 1.0 - 3.0
        bSlider.setValue(5 + (int)(Math.random() * 16));  // 0.5 - 2.0
        mSlider.setValue(1 + (int)(Math.random() * 4));   // 1-4
        nSlider.setValue(1 + (int)(Math.random() * 4));   // 1-4
        updateParameterValues();
    }

    private void showControlPanel() {
        remove(parametersPanel);
        add(controlPanel, BorderLayout.SOUTH);
        controlPanel.setVisible(true);
        revalidate();
        repaint();
    }

    private void showParametersPanel() {
        remove(controlPanel);
        add(parametersPanel, BorderLayout.SOUTH);
        parametersPanel.setVisible(true);
        revalidate();
        repaint();
    }

    private void startGameWithCurrentTrack() {
        RacingGame mainFrame = (RacingGame) SwingUtilities.getWindowAncestor(ChooseTrackPanel.this);
        GameplayPanel gameplayPanel = new GameplayPanel();

        gameplayPanel.setTrackCalculator(currentCalculator);

        mainFrame.changePanel(gameplayPanel);
        gameplayPanel.startGame();
    }

    private void goBackToMainMenu() {
        RacingGame mainFrame = (RacingGame) SwingUtilities.getWindowAncestor(ChooseTrackPanel.this);
        StartMenuPanel startMenuPanel = new StartMenuPanel();
        mainFrame.changePanel(startMenuPanel);
    }
}