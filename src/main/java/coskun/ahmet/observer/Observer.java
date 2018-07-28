package coskun.ahmet.observer;

import coskun.ahmet.model.GameNotification;

public abstract class Observer implements IObserver {
    public abstract void update(GameNotification gameNotification);
}
