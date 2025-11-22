package ru.vsu.cs.khalibekov_a_b_racerGame.trackCalculate;

<<<<<<< HEAD
/**
 * Базовый калькулятор для генерации формы гоночной трассы.
 * Использует математическую функцию для вычисления радиуса трассы
 * в зависимости от угла theta: r(θ) = a + b * cos(nθ) * sin(mθ)
 *
 * @author Khalibekov A.B.
 */
=======
>>>>>>> 01a6e8d5470b89a5d9177e2d319653e989cf2832
public class BasicTrackCalculator implements TrackCalculator {
    private double a = 2.0;
    private double b = 1.0;
    private int m = 1;
    private int n = 1;

<<<<<<< HEAD
    /**
     * Создает калькулятор с параметрами по умолчанию.
     */
    public BasicTrackCalculator() {}

    /**
     * Создает калькулятор с заданными параметрами.
     *
     * @param a базовый радиус трассы
     * @param b амплитуда колебаний
     * @param m первый множитель частоты
     * @param n второй множитель частоты
     */
=======
    public BasicTrackCalculator() {}

>>>>>>> 01a6e8d5470b89a5d9177e2d319653e989cf2832
    public BasicTrackCalculator(double a, double b, int m, int n) {
        this.a = a;
        this.b = b;
        this.m = m;
        this.n = n;
    }

<<<<<<< HEAD
    /**
     * Вычисляет радиус трассы для заданного угла.
     *
     * @param theta угол в радианах
     * @return радиус трассы в заданном направлении
     */
=======
>>>>>>> 01a6e8d5470b89a5d9177e2d319653e989cf2832
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
