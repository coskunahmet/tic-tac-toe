package coskun.ahmet.runner;

import coskun.ahmet.controller.GameController;
import coskun.ahmet.controller.IGameController;
import coskun.ahmet.model.gameboard.GameBoardObserver;
import coskun.ahmet.utils.PropertiesManager;
import coskun.ahmet.view.GameViewObserver;

public class GameRunner {

    public static void main(String[] args) {
        PropertiesManager propertiesManagerInstance = PropertiesManager.getInstance();

        if (!propertiesManagerInstance.isPropertiesValid()) {
            //TODO put a message on console
            return;
        }

        IGameController gameController = new GameController();
        GameViewObserver gameViewObserver = new GameViewObserver();
        GameBoardObserver gameBoardObserver = new GameBoardObserver();

        gameController.attach(gameViewObserver, PropertiesManager.getInstance().getTopicProperty(PropertiesManager.VIEW_TOPIC_NAME_KEY));
        gameController.attach(gameBoardObserver, PropertiesManager.getInstance().getTopicProperty(PropertiesManager.MODEL_TOPIC_NAME_KEY));

        gameController.init();
        gameController.start();


    }
}
