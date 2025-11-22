package ru.vsu.cs.khalibekov_a_b_racerGame.engine;

import ru.vsu.cs.khalibekov_a_b_racerGame.models.Car;
import ru.vsu.cs.khalibekov_a_b_racerGame.models.RaceTrack;

/**
 * Движок игры, управляющий основным игровым процессом.
 * Отвечает за обновление состояния игры, обработку столкновений, подсчет очков
 * и управление игровым состоянием (старт, пауза, завершение).
 *
 * @author Khalibekov A.B.
 */
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

    /**
     * Обновляет состояние игры за указанный промежуток времени.
     *
     * @param deltaTime время, прошедшее с предыдущего обновления (в секундах)
     * @param acceleration ускорение автомобиля (-1 до 1, где -1 - торможение, 1 - ускорение)
     * @param steering угол поворота руля (-1 до 1, где -1 - влево, 1 - вправо)
     */
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

    /**
     * Начинает новую игру, сбрасывая время и счет.
     */
    public void startGame() {
        isGameRunning = true;
        gameTime = 0.0;
        score = 0;
    }

    /**
     * Останавливает игру и возвращает финальный счет.
     *
     * @return финальное количество очков
     */
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