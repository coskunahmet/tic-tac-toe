package coskun.ahmet.observer;

import coskun.ahmet.model.gameboard.GameBoardTile;

public interface IObserverManager {

    void attach(Observer observer, String topic);

    void notifyAllObservers(String topic, GameBoardTile gameBoardTile);
}
