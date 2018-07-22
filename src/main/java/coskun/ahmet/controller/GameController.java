package coskun.ahmet.controller;

import coskun.ahmet.model.playground.PlaygroundTile;
import coskun.ahmet.Observer;
import coskun.ahmet.utils.PropertiesManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameController {

    private Map<String, List<Observer>> observers = new HashMap<String, List<Observer>>();

    private PlaygroundTile playgroundTile;

    public GameController() {
        observers.put(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.MODEL_TOPIC_NAME_KEY), new ArrayList<Observer>());
        observers.put(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.VIEW_TOPIC_NAME_KEY), new ArrayList<Observer>());

    }

    public PlaygroundTile getPlaygroundTile() {
        return playgroundTile;
    }

    public void setPlaygroundTile(String topic, PlaygroundTile playgroundTile) {
        this.playgroundTile = playgroundTile;
        notifyAllObservers(topic);
    }

    public void attach(Observer observer, String topic){
        observers.get(topic).add(observer);
    }

    public void notifyAllObservers(String topic){
        for (Observer observer : observers.get(topic)) {
            observer.update(this.playgroundTile.getPosition(), this.playgroundTile.getCurrentCharOnTile());
        }
    }
}
