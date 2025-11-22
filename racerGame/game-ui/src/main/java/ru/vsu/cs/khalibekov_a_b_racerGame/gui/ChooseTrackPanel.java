package ru.vsu.cs.khalibekov_a_b_racerGame.gui;

import ru.vsu.cs.khalibekov_a_b_racerGame.trackCalculate.BasicTrackCalculator;
import ru.vsu.cs.khalibekov_a_b_racerGame.models.RaceTrack;
import ru.vsu.cs.khalibekov_a_b_racerGame.models.Car;

import javax.swing.*;
import java.awt.*;

/**
 * Панель выбора и настройки гоночного трека. Позволяет выбрать: базовый трек,
 * генерировать случайные или настраивать параметры трека вручную.
 *
 * @author Khalibekov A.B.
 */
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
    private JFrame parentFrame;
    private final StartMenuPanel startMenuPanel;

    /**
     * Создает панель выбора трека.
     *
     * @param parentFrame родительское окно для навигации
     * @param startMenuPanel панель главного меню для возврата и установки выбранного трека
     */
    public ChooseTrackPanel(JFrame parentFrame, StartMenuPanel startMenuPanel) {
        this.parentFrame = parentFrame;
        this.startMenuPanel = startMenuPanel;
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
                drawTrackPreview(g);
            }
        };

        previewPanel.setPreferredSize(new Dimension(400, 300));
        previewPanel.setBackground(new Color(79, 144, 24));
        previewPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        controlPanel = createControlPanel();
        parametersPanel = createParametersPanel();
    }

    /**
     * Рисует превью трека в окне при выборе трека.
     */
    private void drawTrackPreview(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = previewPanel.getWidth();
        int height = previewPanel.getHeight();
        int centerX = width / 2;
        int centerY = height / 2;

        // Draw background
        g2d.setColor(new Color(79, 144, 24));
        g2d.fillRect(0, 0, width, height);

        // Draw track
        java.util.List<java.awt.geom.Point2D.Double> outerPoints = previewTrack.getOuterTrackPoints();
        java.util.List<java.awt.geom.Point2D.Double> innerPoints = previewTrack.getInnerTrackPoints();

        // Draw road area
        Polygon roadPolygon = new Polygon();
        for (java.awt.geom.Point2D.Double point : outerPoints) {
            roadPolygon.addPoint(centerX + (int)point.x, centerY - (int)point.y);
        }
        for (int i = innerPoints.size() - 1; i >= 0; i--) {
            java.awt.geom.Point2D.Double point = innerPoints.get(i);
            roadPolygon.addPoint(centerX + (int)point.x, centerY - (int)point.y);
        }

        g2d.setColor(Color.GRAY);
        g2d.fill(roadPolygon);

        // Draw track boundaries
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(2f));
        drawPreviewTrackPath(g2d, outerPoints, centerX, centerY);
        drawPreviewTrackPath(g2d, innerPoints, centerX, centerY);
    }

    private void drawPreviewTrackPath(Graphics2D g2d, java.util.List<java.awt.geom.Point2D.Double> points, int centerX, int centerY) {
        if (points.isEmpty()) return;

        int[] xPoints = new int[points.size()];
        int[] yPoints = new int[points.size()];

        for (int i = 0; i < points.size(); i++) {
            java.awt.geom.Point2D.Double point = points.get(i);
            xPoints[i] = centerX + (int) point.x;
            yPoints[i] = centerY - (int) point.y;
        }

        g2d.drawPolygon(xPoints, yPoints, points.size());
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

    /**
     * Обновляет значения параметров трека на основе позиций слайдеров
     * и перерисовывает превью трека.
     */
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
        previewTrack.setTrackCalculator(currentCalculator);
        previewPanel.repaint();
    }

    /**
     * Устанавливает параметры для базового трека.
     */
    private void setBasicTrack() {
        aSlider.setValue(20);
        bSlider.setValue(10);
        mSlider.setValue(1);
        nSlider.setValue(1);
        updateParameterValues();
    }

    /**
     * Генерирует случайный трек с произвольными параметрами.
     */
    private void setRandomTrack() {
        aSlider.setValue(10 + (int)(Math.random() * 16));
        bSlider.setValue(5 + (int)(Math.random() * 16));
        mSlider.setValue(1 + (int)(Math.random() * 4));
        nSlider.setValue(1 + (int)(Math.random() * 4));
        updateParameterValues();
    }

    private void showControlPanel() {
        if (parametersPanel.getParent() != null) {
            remove(parametersPanel);
        }
        add(controlPanel, BorderLayout.SOUTH);
        revalidate();
        repaint();
    }

    private void showParametersPanel() {
        if (controlPanel.getParent() != null) {
            remove(controlPanel);
        }
        add(parametersPanel, BorderLayout.SOUTH);
        revalidate();
        repaint();
    }

    /**
     * Начинает игру с текущим выбранным треком.
     */
    private void startGameWithCurrentTrack() {
        // Create track with current calculator
        RaceTrack selectedTrack = new RaceTrack();
        selectedTrack.setTrackCalculator(currentCalculator);

        // Update selected track in start menu
        startMenuPanel.setSelectedTrack(selectedTrack);

        // Start game with selected car and track
        Car selectedCar = startMenuPanel.getSelectedCar();
        GameplayPanel gameplayPanel = new GameplayPanel(parentFrame, selectedCar, selectedTrack);
        switchToPanel(gameplayPanel);
        gameplayPanel.startGame();
    }

    private void goBackToMainMenu() {
        switchToPanel(startMenuPanel);
    }

    private void switchToPanel(JPanel newPanel) {
        parentFrame.getContentPane().removeAll();
        parentFrame.getContentPane().add(newPanel);
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}