package coskun.ahmet.observer;

import coskun.ahmet.enums.GameNotification;

public interface IObserver {

    void update(GameNotification gameNotification);
}
