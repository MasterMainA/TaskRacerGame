import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

class GameplayPanel extends JPanel {
    private JButton goToMenu;
    private RaceTrack raceTrack;
    private Car playerCar;

    private KeyHandler keyHandler;
    private long lastTime;


    public GameplayPanel() {
        this.goToMenu = new JButton("Вернуться назад");

        Font buttonFont = new Font("Arial", Font.BOLD, 10);
        goToMenu.setFont(buttonFont);

        Dimension buttonSize = new Dimension(125, 25);
        goToMenu.setPreferredSize(buttonSize);

        Color buttonColor = new Color(188, 188, 188);
        goToMenu.setBackground(buttonColor);

        add(goToMenu);

        setBackground(new Color(81, 81, 81));

        goToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RacingGame mainFrame = (RacingGame) SwingUtilities.getWindowAncestor(GameplayPanel.this);
                StartMenuPanel startMenuPanel = new StartMenuPanel();

                mainFrame.changePanel(startMenuPanel);
            }
        });

        this.raceTrack = new RaceTrack();
        this.playerCar = new Car(700, 600);

        this.keyHandler = new KeyHandler();
        this.addKeyListener(keyHandler);

        this.setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        raceTrack.draw(g2d);
        playerCar.draw(g2d);
    }

    public void startGame() {
        this.requestFocusInWindow();

        lastTime = System.nanoTime();

        Timer gameTimer = new Timer(16, e -> {
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - lastTime) / 1_000_000_000.0;
            lastTime = currentTime;

            playerCar.updateControls(
                    keyHandler.WPressed,
                    keyHandler.SPressed,
                    keyHandler.APressed,
                    keyHandler.DPressed,
                    deltaTime
            );
            repaint();
        });
        gameTimer.start();
    }
}


