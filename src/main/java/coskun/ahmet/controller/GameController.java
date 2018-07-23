package coskun.ahmet.controller;

import coskun.ahmet.model.gameboard.GameBoardTile;
import coskun.ahmet.Observer;
import coskun.ahmet.utils.PropertiesManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameController implements IGameController{

    private Map<String, List<Observer>> observers = new HashMap<String, List<Observer>>();
    private IInputController inputController = new InputController();
    private boolean isGamePlay = false;

    private static final String COORDINATE_DELIMITER = ",";

    public GameController() {
        observers.put(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.MODEL_TOPIC_NAME_KEY), new ArrayList<Observer>());
        observers.put(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.VIEW_TOPIC_NAME_KEY), new ArrayList<Observer>());

    }


    public void setGameBoardTile(String topic, GameBoardTile gameBoardTile) {
        notifyAllObservers(topic, gameBoardTile);
    }

    public void attach(Observer observer, String topic){
        observers.get(topic).add(observer);
    }

    public void notifyAllObservers(String topic, GameBoardTile gameBoardTile){
        for (Observer observer : observers.get(topic)) {
            observer.update(gameBoardTile.getPosition(), gameBoardTile.getCurrentCharOnTile());
        }
    }

    public void init() {
        //TODO initialize game

        isGamePlay = true;
        start();
    }

    public void start() {

        while(isGamePlay) {
            update();
        }
    }

    public void update() {

        String input = inputController.getInput();
        String[] coordinates = input.split(GameController.COORDINATE_DELIMITER);
        int coordinateX = Integer.parseInt(coordinates[0]);
        int coordinateY = Integer.parseInt(coordinates[1]);

        GameBoardTile newGameBoardTile = new GameBoardTile();
        newGameBoardTile.setPosition(coordinateX, coordinateY, PropertiesManager.getInstance().getGameBoardSize());
        //TODO get current player' s char and put it
        newGameBoardTile.setCurrentCharOnTile(PropertiesManager.getInstance().getGameProperty(PropertiesManager.FIRST_PLAYER_CHAR_KEY).charAt(0));

        this.setGameBoardTile(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.MODEL_TOPIC_NAME_KEY), newGameBoardTile);
        this.setGameBoardTile(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.VIEW_TOPIC_NAME_KEY), newGameBoardTile);

    }
}
