package ru.vsu.cs.khalibekov_a_b_racerGame.models;

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

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

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