package coskun.ahmet.runner;

import coskun.ahmet.controller.GameController;
import coskun.ahmet.controller.IGameController;
import coskun.ahmet.model.gameboard.GameBoard;
import coskun.ahmet.utils.PropertiesManager;
import coskun.ahmet.view.GameView;

public class GameRunner {

    public static void main(String[] args) {
        PropertiesManager propertiesManagerInstance = PropertiesManager.getInstance();

        if (!propertiesManagerInstance.isPropertiesValid()) {
            //TODO put a message on console
            return;
        }

        IGameController gameController = new GameController();
        GameView gameViewObserver = new GameView();
        GameBoard gameBoardObserver = new GameBoard();

        gameController.init();
        gameController.start();


    }
}
