package ru.vsu.cs.khalibekov_a_b.racerGame.models;

import ru.vsu.cs.khalibekov_a_b.racerGame.models.trackCalculate.*;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.util.List;

public class RaceTrack {
    private Path2D outerTrackPath;  // Внешние границы
    private Path2D innerTrackPath;  // Внутренние границы (бордюры)
    private Area roadArea;          // Область дороги

    private static final double SCALE = 100.0;
    // Параметры
    private double a = 2; // max - ~2
    private double b = 1; // max - ~2
    private int m = 1;
    private int n = 1;

    private TrackCalculator trackCalculator;

    public RaceTrack() {
        this.trackCalculator = new BasicTrackCalculator();
    }

    private Area createRoadArea() {
        Area area = new Area(outerTrackPath);
        area.subtract(new Area(innerTrackPath));
        return area;
    }

    public void draw(Graphics gr, double panelWidth, double panelHeight) {
        Graphics2D g = (Graphics2D) gr;
        drawCenteredRoundedRectangle(g, panelWidth, panelHeight, Color.BLACK, 0.9);
        Color colorFieldTerrain = new Color(79, 144, 24);
        drawCenteredRoundedRectangle(g, panelWidth, panelHeight, colorFieldTerrain, 0.7);

        outerTrackPath = createTrack(1536, 801, false);
        innerTrackPath = createTrack(1536, 801, true);
        roadArea = createRoadArea();

        g.setColor(Color.GRAY);
        g.fill(roadArea);

        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(3f));
        g.draw(outerTrackPath);
        g.draw(innerTrackPath);
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

    private Path2D createTrack(double panelWidth, double panelHeight, boolean isInner) {
        int centerX = (int) (panelWidth / 2);
        int centerY = (int) (panelHeight / 2);

        double trackScale = isInner ? SCALE * 0.6 : SCALE;

        List<Point2D.Double> points = new ArrayList<>();
        for (int i = 0; i < 127; i++) {
            double theta = i * 2 * Math.PI / 127;
            double radius = computeRadius(theta);
            Point2D.Double point = polarToCartesian(radius, theta);
            points.add(point);
        }

        Path2D.Double path = new Path2D.Double();
        boolean firstPoint = true;

        for (Point2D.Double point : points) {
            int x = centerX + (int) (point.x * trackScale);
            int y = centerY - (int) (point.y * trackScale);

            if (firstPoint) {
                path.moveTo(x, y);
                firstPoint = false;
            } else {
                path.lineTo(x, y);
            }
        }

        path.closePath();

        return path;
    }

    // Функция для вычисления радиуса в полярных координатах
    private double computeRadius(double theta) {
        return trackCalculator.compute(theta);
    }

    // Преобразование полярных координат в декартовы
    private Point2D.Double polarToCartesian(double radius, double theta) {
        double x = radius * Math.cos(theta);
        double y = radius * Math.sin(theta);
        return new Point2D.Double(x, y);
    }

    public void setTrackCalculator(TrackCalculator trackCalculator) {
        this.trackCalculator = trackCalculator;
    }

    public void randomizeParameters() {
        Random random = new Random();

        a = 1.0 + random.nextDouble();

        b = 0.5 + random.nextDouble();

        m = random.nextInt(4);

        n = random.nextInt(4);
    }

}