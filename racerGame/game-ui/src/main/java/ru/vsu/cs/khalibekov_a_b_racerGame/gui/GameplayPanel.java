package ru.vsu.cs.khalibekov_a_b_racerGame.gui;

import ru.vsu.cs.khalibekov_a_b_racerGame.engine.GameEngine;
import ru.vsu.cs.khalibekov_a_b_racerGame.keyHandler.KeyHandler;
import ru.vsu.cs.khalibekov_a_b_racerGame.models.Car;
import ru.vsu.cs.khalibekov_a_b_racerGame.models.RaceTrack;
import ru.vsu.cs.khalibekov_a_b_racerGame.models.Spectator;
import ru.vsu.cs.khalibekov_a_b_racerGame.models.Tribune;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * Панель геймплея. Отображает гоночный трек, автомобиль, трибуны со зрителями.
 * Управляет игровым процессом и обработкой ввода.
 *
 * @author Khalibekov A.B.
 */
public class GameplayPanel extends JPanel implements ActionListener {
    private JButton goToMenu;
    private GameEngine gameEngine;
    private Tribune tribune;

    private KeyHandler keyHandler;
    private long lastTime;
    private Timer gameTimer;
    private JFrame parentFrame;

    /**
     * Создает игровую панель с выбранными автомобилем и треком.
     *
     * @param parentFrame родительское окно для навигации
     * @param selectedCar выбранный автомобиль для игры
     * @param selectedTrack выбранный трек для гонки
     */
    public GameplayPanel(JFrame parentFrame, Car selectedCar, RaceTrack selectedTrack) {
        this.parentFrame = parentFrame;
        this.gameEngine = new GameEngine(selectedCar, selectedTrack);
        this.tribune = new Tribune();
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        // Back button panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(81, 81, 81));

        this.goToMenu = new JButton("Вернуться назад");
        goToMenu.setFont(new Font("Arial", Font.BOLD, 10));
        goToMenu.setPreferredSize(new Dimension(125, 25));
        goToMenu.setBackground(new Color(188, 188, 188));

        goToMenu.addActionListener(e -> {
            StartMenuPanel startMenuPanel = new StartMenuPanel(parentFrame);
            switchToPanel(startMenuPanel);
        });

        topPanel.add(goToMenu);
        add(topPanel, BorderLayout.NORTH);

        setBackground(new Color(81, 81, 81));

        this.keyHandler = new KeyHandler();
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        gameTimer = new Timer(16, this); // ~60 FPS
    }

    /**
     * Отрисовывает все игровые элементы: трек, автомобиль, трибуны, зрителей и HUD.
     *
     * @param g графический контекст для отрисовки
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Draw game elements
        drawRaceTrack(g2d, panelWidth, panelHeight);
        drawCar(g2d);
        drawTribune(g2d, panelWidth, panelHeight);

        // Draw HUD
        drawHUD(g2d);
    }

    /**
     * Отрисовывает гоночный трек с внутренней и внешней границами.
     *
     * @param g2d графический контекст для отрисовки
     * @param panelWidth ширина панели
     * @param panelHeight высота панели
     */
    private void drawRaceTrack(Graphics2D g2d, int panelWidth, int panelHeight) {
        // Draw background
        drawCenteredRoundedRectangle(g2d, panelWidth, panelHeight, Color.BLACK, 0.9);
        Color fieldColor = new Color(79, 144, 24);
        drawCenteredRoundedRectangle(g2d, panelWidth, panelHeight, fieldColor, 0.7);

        // Draw track
        RaceTrack track = gameEngine.getCurrentTrack();
        List<Point2D.Double> outerPoints = track.getOuterTrackPoints();
        List<Point2D.Double> innerPoints = track.getInnerTrackPoints();

        int centerX = panelWidth / 2;
        int centerY = panelHeight / 2;

        // Draw road area
        Polygon roadPolygon = new Polygon();

        // Добавляем внешние точки по часовой стрелке
        for (Point2D.Double point : outerPoints) {
            roadPolygon.addPoint(centerX + (int)point.x, centerY - (int)point.y);
        }

        // Добавляем внутренние точки против часовой стрелки
        for (int i = innerPoints.size() - 1; i >= 0; i--) {
            Point2D.Double point = innerPoints.get(i);
            roadPolygon.addPoint(centerX + (int)point.x, centerY - (int)point.y);
        }

        g2d.setColor(Color.GRAY);
        g2d.fill(roadPolygon);

        // Draw track boundaries
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(3f));

        // Рисуем замкнутые полигоны для границ
        drawClosedTrackPath(g2d, outerPoints, centerX, centerY);
        drawClosedTrackPath(g2d, innerPoints, centerX, centerY);
    }

    private void drawClosedTrackPath(Graphics2D g2d, List<Point2D.Double> points, int centerX, int centerY) {
        if (points.isEmpty()) return;

        Polygon polygon = new Polygon();

        for (Point2D.Double point : points) {
            polygon.addPoint(centerX + (int) point.x, centerY - (int) point.y);
        }

        g2d.drawPolygon(polygon);
    }

    /**
     * Отрисовывает автомобиль с учетом его позиции и угла поворота.
     *
     * @param g2d графический контекст для отрисовки
     */
    private void drawCar(Graphics2D g2d) {
        Car car = gameEngine.getPlayerCar();
        double x = car.getX();
        double y = car.getY();
        double angle = car.getAngle();

        AffineTransform oldTransform = g2d.getTransform();

        // Apply rotation
        g2d.rotate(angle, x + 10, y + 5);

        // Draw car body
        g2d.setColor(Color.RED);
        g2d.fillRect((int) x, (int) y, 20, 10);

        // Draw wheels
        g2d.setColor(Color.BLACK);
        g2d.fillRect((int) x + 15, (int) y + 2, 3, 6);
        g2d.fillRect((int) x + 2, (int) y + 2, 3, 6);

        g2d.setTransform(oldTransform);

        // Draw car info
        g2d.setColor(Color.WHITE);
        g2d.drawString(String.format("Speed: %.1f", car.getSpeed()), (int) x - 30, (int) y - 10);
        g2d.drawString(String.format("Angle: %.1f", Math.toDegrees(angle)), (int) x - 30, (int) y - 25);
    }

    /**
     * Отрисовывает трибуны и зрителей вокруг трека.
     *
     * @param g2d графический контекст для отрисовки
     * @param panelWidth ширина панели
     * @param panelHeight высота панели
     */
    private void drawTribune(Graphics2D g2d, double panelWidth, double panelHeight) {
        if (tribune.getSpectators().isEmpty()) {
            tribune.createSpectators(panelWidth, panelHeight);
        }

        // Draw all four tribunes
        drawHorizontalTribune(g2d, panelWidth, panelHeight, true);  // Top
        drawHorizontalTribune(g2d, panelWidth, panelHeight, false); // Bottom
        drawVerticalTribune(g2d, panelWidth, panelHeight, true);    // Left
        drawVerticalTribune(g2d, panelWidth, panelHeight, false);   // Right

        // Draw spectators
        for (Spectator spectator : tribune.getSpectators()) {
            drawSpectator(g2d, spectator);
        }
    }

    /**
     * Отрисовывает горизонтальную трибуну (верхнюю или нижнюю).
     *
     * @param g2d графический контекст для отрисовки
     * @param panelWidth ширина панели
     * @param panelHeight высота панели
     * @param isTop true для верхней трибуны, false для нижней
     */
    private void drawHorizontalTribune(Graphics2D g2d, double panelWidth, double panelHeight, boolean isTop) {
        int x, y, width, height;

        if (isTop) {
            x = (int) (panelWidth * 0.5 - (panelHeight * 0.7) / 2);
            y = (int) (panelHeight * 0.05);
            width = (int)(panelHeight * 0.7);
            height = 80;
        } else {
            x = (int) (panelWidth * 0.5 - (panelHeight * 0.7) / 2);
            y = (int) (panelHeight * 0.85);
            width = (int)(panelHeight * 0.7);
            height = 80;
        }

        // Draw tribune base
        g2d.setColor(new Color(tribune.getTribuneColor()));
        g2d.fillRect(x, y, width, height);

        // Draw tribune pattern
        g2d.setColor(new Color(70, 70, 70));
        for (int i = 0; i < width; i += 18) {
            g2d.drawLine(x + i, y, x + i, y + height);
        }

        // Draw seats
        drawHorizontalSeats(g2d, x, y, width, height, isTop);

        // Draw guardrail
        g2d.setColor(new Color(tribune.getGuardrailColor()));
        if (isTop) {
            g2d.fillRect(x, y + height - 6, width, 6);
        } else {
            g2d.fillRect(x, y, width, 6);
        }
    }

    /**
     * Отрисовывает вертикальную трибуну (левую или правую).
     *
     * @param g2d графический контекст для отрисовки
     * @param panelWidth ширина панели
     * @param panelHeight высота панели
     * @param isLeft true для левой трибуны, false для правой
     */
    private void drawVerticalTribune(Graphics2D g2d, double panelWidth, double panelHeight, boolean isLeft) {
        int x, y, width, height;

        if (isLeft) {
            x = (int) (panelWidth * 0.15 - panelHeight * 0.1);
            y = (int) (panelHeight * 0.15);
            width = 80;
            height = (int)(panelHeight * 0.7);
        } else {
            x = (int) (panelWidth * 0.85);
            y = (int) (panelHeight * 0.15);
            width = 80;
            height = (int)(panelHeight * 0.7);
        }

        // Draw tribune base
        g2d.setColor(new Color(tribune.getTribuneColor()));
        g2d.fillRect(x, y, width, height);

        // Draw tribune pattern
        g2d.setColor(new Color(70, 70, 70));
        for (int i = 0; i < height; i += 18) {
            g2d.drawLine(x, y + i, x + width, y + i);
        }

        // Draw seats
        drawVerticalSeats(g2d, x, y, width, height, isLeft);

        // Draw guardrail
        g2d.setColor(new Color(tribune.getGuardrailColor()));
        if (isLeft) {
            g2d.fillRect(x + width - 6, y, 6, height);
        } else {
            g2d.fillRect(x, y, 6, height);
        }
    }

    private void drawHorizontalSeats(Graphics2D g2d, int x, int y, int width, int height, boolean isTop) {
        g2d.setColor(new Color(tribune.getSeatColor()));

        int seatSpacing = 4;
        int totalSeatsWidth = tribune.getSeatCols() * tribune.getSeatWidth() +
                (tribune.getSeatCols() - 1) * seatSpacing;
        int horizontalMargin = (width - totalSeatsWidth) / 2;

        for (int row = 0; row < tribune.getSeatRows(); row++) {
            for (int col = 0; col < tribune.getSeatCols(); col++) {
                int seatX = x + horizontalMargin + col * (tribune.getSeatWidth() + seatSpacing);
                int seatY;

                if (isTop) {
                    seatY = y + height - (row + 1) * (tribune.getSeatHeight() + seatSpacing) - 5;
                } else {
                    seatY = y + row * (tribune.getSeatHeight() + seatSpacing) + 9;
                }

                g2d.fillRect(seatX, seatY, tribune.getSeatWidth(), tribune.getSeatHeight());

                // Draw seat back
                g2d.setColor(new Color(150, 150, 150));
                if (isTop) {
                    g2d.fillRect(seatX, seatY + tribune.getSeatHeight(), tribune.getSeatWidth(), 3);
                } else {
                    g2d.fillRect(seatX, seatY - 3, tribune.getSeatWidth(), 3);
                }
                g2d.setColor(new Color(tribune.getSeatColor()));
            }
        }
    }

    private void drawVerticalSeats(Graphics2D g2d, int x, int y, int width, int height, boolean isLeft) {
        g2d.setColor(new Color(tribune.getSeatColor()));

        int seatSpacing = 4;
        int totalSeatsHeight = tribune.getSeatCols() * tribune.getSeatHeight() +
                (tribune.getSeatCols() - 1) * seatSpacing;
        int verticalMargin = (height - totalSeatsHeight) / 2;

        for (int row = 0; row < tribune.getSeatCols(); row++) {
            for (int col = 0; col < tribune.getSeatRows(); col++) {
                int seatY = y + verticalMargin + row * (tribune.getSeatHeight() + seatSpacing);
                int seatX;

                if (isLeft) {
                    seatX = x + width - (col + 1) * (tribune.getSeatWidth() + seatSpacing) - 5;
                } else {
                    seatX = x + col * (tribune.getSeatWidth() + seatSpacing) + 9;
                }

                g2d.fillRect(seatX, seatY, tribune.getSeatWidth(), tribune.getSeatHeight());

                // Draw seat back
                g2d.setColor(new Color(150, 150, 150));
                if (isLeft) {
                    g2d.fillRect(seatX + tribune.getSeatWidth(), seatY, 3, tribune.getSeatHeight());
                } else {
                    g2d.fillRect(seatX - 3, seatY, 3, tribune.getSeatHeight());
                }
                g2d.setColor(new Color(tribune.getSeatColor()));
            }
        }
    }

    /**
     * Отрисовывает отдельного зрителя на трибуне.
     *
     * @param g2d графический контекст для отрисовки
     * @param spectator зритель для отрисовки
     */
    private void drawSpectator(Graphics2D g2d, Spectator spectator) {
        int x = spectator.getX();
        int y = spectator.getY();
        int width = spectator.getWidth();
        int height = spectator.getHeight();

        // Body
        g2d.setColor(new Color(spectator.getBodyColor()));
        g2d.fillOval(x, y + height / 3, width, height * 2 / 3);

        // Clothes
        g2d.setColor(new Color(spectator.getClothesColor()));
        g2d.fillRect(x, y + height / 2, width, height / 3);

        // Head
        g2d.setColor(new Color(spectator.getHeadColor()));
        int headSize = height / 2;
        g2d.fillOval(x + (width - headSize) / 2, y, headSize, headSize);

        // Eyes
        g2d.setColor(Color.BLACK);
        int eyeSize = headSize / 5;
        g2d.fillOval(x + (width - headSize) / 2 + headSize / 3 - eyeSize / 2,
                y + headSize / 3, eyeSize, eyeSize);
        g2d.fillOval(x + (width - headSize) / 2 + headSize * 2 / 3 - eyeSize / 2,
                y + headSize / 3, eyeSize, eyeSize);
    }

    private void drawHUD(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));

        g2d.drawString(String.format("Time: %.1fs", gameEngine.getGameTime()), 20, 30);
        g2d.drawString(String.format("Score: %d", gameEngine.getScore()), 20, 50);

        if (!gameEngine.isGameRunning()) {
            g2d.setFont(new Font("Arial", Font.BOLD, 24));
            g2d.drawString("PAUSED", getWidth() / 2 - 40, getHeight() / 2);
        }
    }

    private void drawCenteredRoundedRectangle(Graphics2D g2d, double panelWidth, double panelHeight, Color color, double k) {
        int rectWidth = (int) (panelWidth * k);
        int rectHeight = (int) (panelHeight * k);

        int x = (int) ((panelWidth - rectWidth) / 2);
        int y = (int) ((panelHeight - rectHeight) / 2);

        int arcWidth = 40;
        int arcHeight = 40;

        g2d.setColor(color);
        g2d.fillRoundRect(x, y, rectWidth, rectHeight, arcWidth, arcHeight);
    }

    /**
     * Обрабатывает игровые события и обновляет состояние игры.
     *
     * @param e игровое событие
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gameTimer) {
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - lastTime) / 1_000_000_000.0;
            lastTime = currentTime;

            double acceleration = 0;
            double steering = 0;

            if (keyHandler.WPressed) acceleration = 1.0;
            if (keyHandler.SPressed) acceleration = -1.0;
            if (keyHandler.APressed) steering = -1.0;
            if (keyHandler.DPressed) steering = 1.0;

            gameEngine.update(deltaTime, acceleration, steering);
            repaint();
        }
    }

    /**
     * Запускает игровой процесс, активируя игровой движок и таймер.
     */
    public void startGame() {
        gameEngine.startGame();
        this.requestFocusInWindow();
        lastTime = System.nanoTime();
        gameTimer.start();
    }

    private void switchToPanel(JPanel newPanel) {
        parentFrame.getContentPane().removeAll();
        parentFrame.getContentPane().add(newPanel);
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}