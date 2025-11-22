package ru.vsu.cs.khalibekov_a_b_racerGame.models;

import ru.vsu.cs.khalibekov_a_b_racerGame.trackCalculate.TrackCalculator;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Модель гоночной трассы, генерируемой математически.
 * Создает внутреннюю и внешнюю границы трассы на основе полярных координат
 * с использованием вычисленного радиуса для каждой точки.
 *
 * @author Khalibekov A.B.
 */
public class RaceTrack {
    private static final double SCALE = 100.0;
    private static final int POINT_COUNT = 128; // Увеличили на 1 для замыкания

    private TrackCalculator trackCalculator;
    private List<Point2D.Double> outerTrackPoints;
    private List<Point2D.Double> innerTrackPoints;

    /**
     * Создает новую гоночную трассу с калькулятором по умолчанию.
     */
    public RaceTrack() {
        this.trackCalculator = new ru.vsu.cs.khalibekov_a_b_racerGame.trackCalculate.BasicTrackCalculator();
        calculateTrackPoints();
    }

    /**
     * Вычисляет точки для внутренней и внешней области дороги.
     */
    private void calculateTrackPoints() {
        outerTrackPoints = calculateTrackPath(SCALE);
        innerTrackPoints = calculateTrackPath(SCALE * 0.6);
    }

    /**
     * Вычисляет точки трассы на основе полярных координат.
     *
     * @param scale масштаб для преобразования координат
     * @return список точек трассы в декартовых координатах
     */
    private List<Point2D.Double> calculateTrackPath(double scale) {
        List<Point2D.Double> points = new ArrayList<>();

        for (int i = 0; i < POINT_COUNT; i++) {
            double theta = i * 2 * Math.PI / (POINT_COUNT - 1); // -1 чтобы замкнуть
            double radius = computeRadius(theta);
            Point2D.Double point = polarToCartesian(radius, theta, scale);
            points.add(point);
        }

        return points;
    }

    /**
     * Вычисляет радиус трассы для заданного угла.
     *
     * @param theta угол в радианах
     * @return радиус трассы в заданном направлении
     */
    private double computeRadius(double theta) {
        return trackCalculator.compute(theta);
    }

    /**
     * Преобразует полярные координаты в декартовы.
     *
     * @param radius радиус в полярной системе координат
     * @param theta угол в полярной системе координат
     * @param scale масштабный коэффициент
     * @return точка в декартовой системе координат
     */
    private Point2D.Double polarToCartesian(double radius, double theta, double scale) {
        double x = radius * Math.cos(theta) * scale;
        double y = radius * Math.sin(theta) * scale;
        return new Point2D.Double(x, y);
    }

    /**
     * Устанавливает новый калькулятор трассы и пересчитывает точки.
     *
     * @param trackCalculator калькулятор для генерации формы трассы
     */
    public void setTrackCalculator(TrackCalculator trackCalculator) {
        this.trackCalculator = trackCalculator;
        calculateTrackPoints();
    }

    public List<Point2D.Double> getOuterTrackPoints() {
        return outerTrackPoints;
    }

    public List<Point2D.Double> getInnerTrackPoints() {
        return innerTrackPoints;
    }

    public double getScale() {
        return SCALE;
    }

    public int getPointCount() {
        return POINT_COUNT;
    }
}