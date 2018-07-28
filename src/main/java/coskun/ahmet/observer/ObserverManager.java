package coskun.ahmet.observer;

import coskun.ahmet.model.GameNotification;
import coskun.ahmet.model.gameboard.GameBoardTile;
import coskun.ahmet.utils.PropertiesManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObserverManager {

    private Map<String, List<IGameMoveObserver>> gameMoveObservers = new HashMap<String, List<IGameMoveObserver>>();
    private Map<String, List<IObserver>> gameMessageObservers = new HashMap<String, List<IObserver>>();

    private static ObserverManager instance = null;


    public static ObserverManager getInstance() {
        if (instance == null) {
            instance = new ObserverManager();
        }

        return instance;
    }


    public void setGameBoardTile(String topic, GameBoardTile gameBoardTile) {
        notifyAllGameMoveObservers(topic, gameBoardTile);
    }

    public void setGameNotification(String topic, GameNotification gameNotification) {
        notifyAllObservers(topic, gameNotification);
    }

    public ObserverManager() {
        gameMoveObservers.put(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.GAME_MOVE_MODEL_TOPIC_NAME_KEY), new ArrayList<IGameMoveObserver>());
        gameMoveObservers.put(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.GAME_MOVE_VIEW_TOPIC_NAME_KEY), new ArrayList<IGameMoveObserver>());
        gameMessageObservers.put(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.GAME_NOTIFICATIONS_TOPIC_NAME_KEY), new ArrayList<IObserver>());
        gameMessageObservers.put(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.GAME_NOTIFICATIONS_TOPIC_NAME_KEY), new ArrayList<IObserver>());
        gameMessageObservers.put(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.GAME_NOTIFICATIONS_TOPIC_NAME_KEY), new ArrayList<IObserver>());
    }

    public void attachObserver(IObserver observer, String topic) {
        gameMessageObservers.get(topic).add(observer);
    }

    public void attachGameMoveObserver(IGameMoveObserver observer, String topic) {
        gameMoveObservers.get(topic).add(observer);
    }

    public void notifyAllGameMoveObservers(String topic, GameBoardTile gameBoardTile) {
        for (IGameMoveObserver observer : gameMoveObservers.get(topic)) {
            observer.update(gameBoardTile.getPosition(), gameBoardTile.getCurrentCharOnTile());
        }
    }

    public void notifyAllObservers(String topic, GameNotification gameNotification) {
        for (IObserver observer : gameMessageObservers.get(topic)) {
            observer.update(gameNotification);
        }
    }
}
