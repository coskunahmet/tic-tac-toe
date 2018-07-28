package coskun.ahmet.model;

import coskun.ahmet.enums.GameNotificationEnum;

import java.util.List;

public class GameViewNotification extends GameNotification {

    private List<String> parameterList;

    public GameViewNotification(GameNotificationEnum gameNotificationEnum, List<String> parameterList) {
        super(gameNotificationEnum);
        this.parameterList = parameterList;
    }

    public List<String> getParameterList() {
        return parameterList;
    }
}
