package ru.vsu.cs.khalibekov_a_b_racerGame.trackCalculate;

public class BasicTrackCalculator implements TrackCalculator {
    private double a = 2.0;
    private double b = 1.0;
    private int m = 1;
    private int n = 1;

    public BasicTrackCalculator() {}

    public BasicTrackCalculator(double a, double b, int m, int n) {
        this.a = a;
        this.b = b;
        this.m = m;
        this.n = n;
    }

    @Override
    public double compute(double theta) {
        return a + b * Math.cos(n * theta) * Math.sin(m * theta);
    }

    public void setParameters(double a, double b, int m, int n) {
        this.a = a;
        this.b = b;
        this.m = m;
        this.n = n;
    }
}
