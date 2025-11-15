package ru.vsu.cs.khalibekov_a_b_racerGame.models;

import ru.vsu.cs.khalibekov_a_b_racerGame.trackCalculate.TrackCalculator;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class RaceTrack {
    private static final double SCALE = 100.0;
    private static final int POINT_COUNT = 128; // Увеличили на 1 для замыкания

    private TrackCalculator trackCalculator;
    private List<Point2D.Double> outerTrackPoints;
    private List<Point2D.Double> innerTrackPoints;

    public RaceTrack() {
        this.trackCalculator = new ru.vsu.cs.khalibekov_a_b_racerGame.trackCalculate.BasicTrackCalculator();
        calculateTrackPoints();
    }

    private void calculateTrackPoints() {
        outerTrackPoints = calculateTrackPath(SCALE);
        innerTrackPoints = calculateTrackPath(SCALE * 0.6);
    }

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

    private double computeRadius(double theta) {
        return trackCalculator.compute(theta);
    }

    private Point2D.Double polarToCartesian(double radius, double theta, double scale) {
        double x = radius * Math.cos(theta) * scale;
        double y = radius * Math.sin(theta) * scale;
        return new Point2D.Double(x, y);
    }

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