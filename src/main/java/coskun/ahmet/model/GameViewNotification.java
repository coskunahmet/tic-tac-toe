package coskun.ahmet.model;

import coskun.ahmet.enums.GameNotificationEnum;

public class GameViewNotification extends GameNotification {

    private String information;

    public GameViewNotification(GameNotificationEnum gameNotificationEnum, String information) {
        super(gameNotificationEnum);
        this.information = information;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
