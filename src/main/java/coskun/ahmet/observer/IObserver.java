package coskun.ahmet.observer;

import coskun.ahmet.model.GameNotification;

public interface IObserver {

    void update(GameNotification gameNotification);
}
