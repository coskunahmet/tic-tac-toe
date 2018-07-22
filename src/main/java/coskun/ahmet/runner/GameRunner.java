package coskun.ahmet.runner;

import coskun.ahmet.controller.GameController;
import coskun.ahmet.model.playground.PlaygroundObserver;
import coskun.ahmet.model.playground.PlaygroundTile;
import coskun.ahmet.utils.PropertiesManager;
import coskun.ahmet.view.GameViewObserver;

public class GameRunner {

    public static void main(String[] args) {
        PropertiesManager propertiesManagerInstance = PropertiesManager.getInstance();

        if(!propertiesManagerInstance.isPropertiesValid()) {
            //TODO put a message on console
            return;
        }

        /*GameController gameController = new GameController();
        GameViewObserver gameViewObserver = new GameViewObserver();
        PlaygroundObserver playgroundObserver = new PlaygroundObserver();

        gameController.attach(gameViewObserver, PropertiesManager.getInstance().getTopicProperty(PropertiesManager.VIEW_TOPIC_NAME_KEY));
        gameController.attach(playgroundObserver, PropertiesManager.getInstance().getTopicProperty(PropertiesManager.MODEL_TOPIC_NAME_KEY));

        PlaygroundTile playgroundTile = new PlaygroundTile();
        playgroundTile.setPosition(5);
        playgroundTile.setCurrentCharOnTile('X');
        gameController.setPlaygroundTile(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.VIEW_TOPIC_NAME_KEY), playgroundTile);

        gameViewObserver.getGameView().showPlayground();*/


    }
}
