package coskun.ahmet.model.playground;

import coskun.ahmet.Observer;
import coskun.ahmet.controller.GameController;
import coskun.ahmet.utils.PropertiesManager;

public class PlaygroundObserver extends Observer {

    public void update(int position, char newChar) {
        Playground.getInstance().updatePlayground(position, newChar);
    }
}
