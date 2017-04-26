package com.kontreras.carousel.core;

import com.kontreras.carousel.core.enums.CommandType;

// Класс представляет собой команду, которую, мы будет передавать МК
public class Command {
    // Скорость
    private int speed;
    // Угол
    private int angle;
    // Тип команды
    private CommandType type;

    public Command(int angle, int speed, CommandType type) {
        this.angle = angle;
        this.speed = speed;
        setType(type);
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public long getTime() {
        return speed == 0 ? angle : Math.abs(1000 * angle * 60 / speed / 360);
    }

    public CommandType getType() {
        return type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "angle=" + angle +
                ", speed=" + speed +
                ", time=" + getTime() +
                '}';
    }
}
