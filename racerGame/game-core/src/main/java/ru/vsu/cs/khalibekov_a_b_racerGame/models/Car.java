package ru.vsu.cs.khalibekov_a_b_racerGame.models;

<<<<<<< HEAD
/**
 * Представляет игровую машину с физикой движения.
 * Моделирует ускорение, торможение, трение и повороты автомобиля на трассе.
 * Управляет позицией, скоростью и направлением движения транспортного средства.
 *
 * @author Khalibekov A.B.
 */
=======
>>>>>>> 01a6e8d5470b89a5d9177e2d319653e989cf2832
public class Car {
    private double x;
    private double y;
    private double speed;
    private double angle;

    private final double MAX_FORWARD_SPEED;
    private final double MAX_REVERSE_SPEED;
    private final double ACCELERATION;
    private final double BRAKING;
    private final double FRICTION;
    private final double TURN_SPEED;

<<<<<<< HEAD
    /**
     * Создает новый автомобиль с заданной начальной позицией.
     *
     * @param startX начальная координата X
     * @param startY начальная координата Y
     */
=======
>>>>>>> 01a6e8d5470b89a5d9177e2d319653e989cf2832
    public Car(double startX, double startY) {
        this.x = startX;
        this.y = startY;
        this.speed = 0;
        this.angle = 0;

        this.MAX_FORWARD_SPEED = 180.0;
        this.MAX_REVERSE_SPEED = -90.0;
        this.ACCELERATION = 100.0;
        this.BRAKING = 150.0;
        this.FRICTION = 0.8;
        this.TURN_SPEED = 2.5;
    }

<<<<<<< HEAD
    /**
     * Обновляет состояние автомобиля на основе управления и времени.
     *
     * @param acceleration ускорение (-1 до 1)
     * @param steering поворот (-1 до 1)
     * @param deltaTime время с последнего обновления
     */
=======
>>>>>>> 01a6e8d5470b89a5d9177e2d319653e989cf2832
    public void update(double acceleration, double steering, double deltaTime) {
        if (acceleration > 0) {
            speed += ACCELERATION * deltaTime * acceleration;
        } else if (acceleration < 0) {
            speed += BRAKING * deltaTime * acceleration;
        } else {
            if (speed > 0) {
                speed = Math.max(0, speed - FRICTION * deltaTime * speed);
            } else if (speed < 0) {
                speed = Math.min(0, speed - FRICTION * deltaTime * speed);
            }
        }

        speed = Math.min(Math.max(speed, MAX_REVERSE_SPEED), MAX_FORWARD_SPEED);

        if (Math.abs(speed) > 5) {
            double turnFactor = Math.min(1.0, Math.abs(speed) / 50.0);
            angle += TURN_SPEED * deltaTime * steering * turnFactor;
        }

        x -= speed * Math.cos(angle) * deltaTime;
        y -= speed * Math.sin(angle) * deltaTime;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSpeed() {
        return speed;
    }

    public double getAngle() {
        return angle;
    }

<<<<<<< HEAD
    /**
     * Устанавливает новую позицию автомобиля.
     *
     * @param x координата X
     * @param y координата Y
     */
=======
>>>>>>> 01a6e8d5470b89a5d9177e2d319653e989cf2832
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

<<<<<<< HEAD
    /**
     * Устанавливает новый угол направления автомобиля.
     *
     * @param angle угол в радианах
     */
=======
>>>>>>> 01a6e8d5470b89a5d9177e2d319653e989cf2832
    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getWidth() {
        return 20.0;
    }

    public double getHeight() {
        return 10.0;
    }
}