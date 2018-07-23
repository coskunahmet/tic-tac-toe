package coskun.ahmet.runner;

import coskun.ahmet.utils.PropertiesManager;

public class GameRunner {

    public static void main(String[] args) {
        PropertiesManager propertiesManagerInstance = PropertiesManager.getInstance();

        if (!propertiesManagerInstance.isPropertiesValid()) {
            //TODO put a message on console
            return;
        }
/*
        GameController gameController = new GameController();
        GameViewObserver gameViewObserver = new GameViewObserver();
        GameBoardObserver gameBoardObserver = new GameBoardObserver();

        gameController.attach(gameViewObserver, PropertiesManager.getInstance().getTopicProperty(PropertiesManager.VIEW_TOPIC_NAME_KEY));
        gameController.attach(gameBoardObserver, PropertiesManager.getInstance().getTopicProperty(PropertiesManager.MODEL_TOPIC_NAME_KEY));

        GameBoardTile gameBoardTile = new GameBoardTile();
        gameBoardTile.setPosition(5);
        gameBoardTile.setCurrentCharOnTile('X');
        gameController.setGameBoardTile(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.VIEW_TOPIC_NAME_KEY), gameBoardTile);

        gameViewObserver.getGameView().showGameBoard();*/


    }
}
