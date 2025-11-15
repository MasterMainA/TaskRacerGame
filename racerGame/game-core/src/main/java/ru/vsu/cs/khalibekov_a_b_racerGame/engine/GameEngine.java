package ru.vsu.cs.khalibekov_a_b_racerGame.engine;

import ru.vsu.cs.khalibekov_a_b_racerGame.models.Car;
import ru.vsu.cs.khalibekov_a_b_racerGame.models.RaceTrack;

public class GameEngine {
    private Car playerCar;
    private RaceTrack currentTrack;
    private boolean isGameRunning;
    private double gameTime;
    private int score;

    public GameEngine(Car car, RaceTrack track) {
        this.playerCar = car;
        this.currentTrack = track;
        this.isGameRunning = false;
        this.gameTime = 0.0;
        this.score = 0;
    }

    public void update(double deltaTime, double acceleration, double steering) {
        if (!isGameRunning) return;

        gameTime += deltaTime;

        playerCar.update(acceleration, steering, deltaTime);

        checkCollisions();

        updateScore();
    }

    private void checkCollisions() {

    }

    private void updateScore() {
        score = (int)(gameTime * 10 + Math.abs(playerCar.getSpeed()) * 0.5);
    }

    public void startGame() {
        isGameRunning = true;
        gameTime = 0.0;
        score = 0;
    }

    public int stopGame() {
        isGameRunning = false;
        return score;
    }

    public void pauseGame() {
        isGameRunning = false;
    }

    public void resumeGame() {
        isGameRunning = true;
    }

    public Car getPlayerCar() {
        return playerCar;
    }

    public RaceTrack getCurrentTrack() {
        return currentTrack;
    }

    public boolean isGameRunning() {
        return isGameRunning;
    }

    public double getGameTime() {
        return gameTime;
    }

    public int getScore() {
        return score;
    }
}