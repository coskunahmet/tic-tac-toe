package coskun.ahmet.controller;

import coskun.ahmet.Observer;
import coskun.ahmet.model.gameboard.GameBoardTile;

public interface IGameController {

    void attach(Observer observer, String topic);

    void notifyAllObservers(String topic, GameBoardTile gameBoardTile);

    void update();
    void start();
    void init();
}
