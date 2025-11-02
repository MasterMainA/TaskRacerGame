package ru.vsu.cs.khalibekov_a_b.racerGame.models;

import java.awt.*;
import java.awt.geom.AffineTransform;

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

    public void updateControls(boolean accelerate, boolean brake, boolean turnLeft, boolean turnRight, double deltaTime) {
        if (accelerate) {
            speed += ACCELERATION * deltaTime;
        } else if (brake) {
            speed -= BRAKING * deltaTime;
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
            if (turnLeft) {
                angle -= TURN_SPEED * deltaTime * turnFactor;
            }
            if (turnRight) {
                angle += TURN_SPEED * deltaTime * turnFactor;
            }
        }

        x -= speed * Math.cos(angle) * deltaTime;
        y -= speed * Math.sin(angle) * deltaTime;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        AffineTransform oldTransform = g2d.getTransform();

        g2d.rotate(angle, x + 10, y + 5);

        g2d.setColor(Color.RED);
        g2d.fillRect((int) x, (int) y, 20, 10);

        g2d.setColor(Color.BLACK);
        g2d.fillRect((int) x + 15, (int) y + 2, 3, 6);
        g2d.fillRect((int) x + 2, (int) y + 2, 3, 6);

        g2d.setTransform(oldTransform);

        g2d.setColor(Color.WHITE);
        g2d.drawString(String.format("Speed: %.1f", speed), (int) x - 30, (int) y - 10);
        g2d.drawString(String.format("Angle: %.1f", Math.toDegrees(angle)), (int) x - 30, (int) y - 25);
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
}