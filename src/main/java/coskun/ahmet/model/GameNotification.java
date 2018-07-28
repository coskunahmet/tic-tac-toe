package coskun.ahmet.model;

import coskun.ahmet.enums.GameNotificationEnum;

public class GameNotification {

    private GameNotificationEnum gameNotificationEnum;

    public GameNotification(GameNotificationEnum gameNotificationEnum) {
        this.gameNotificationEnum = gameNotificationEnum;
    }

    public GameNotificationEnum getGameNotificationEnum() {
        return gameNotificationEnum;
    }

    public void setGameNotificationEnum(GameNotificationEnum gameNotificationEnum) {
        this.gameNotificationEnum = gameNotificationEnum;
    }
}
