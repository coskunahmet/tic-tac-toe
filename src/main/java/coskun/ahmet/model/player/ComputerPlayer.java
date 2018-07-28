package coskun.ahmet.model.player;

import coskun.ahmet.model.GameNotification;
import coskun.ahmet.observer.IGameMoveObserver;
import coskun.ahmet.observer.ObserverManager;
import coskun.ahmet.utils.PropertiesManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player implements IGameMoveObserver {

    private List<Integer> filledTilePositionList;

    public ComputerPlayer(String name, char symbol) {
        super(name, symbol);

        filledTilePositionList = new ArrayList<>();

        ObserverManager.getInstance().attachGameMoveObserver(this, PropertiesManager.getInstance().getTopicProperty(PropertiesManager.GAME_MOVE_MODEL_TOPIC_NAME_KEY));
    }

    @Override
    public int[] getInput() {

        int[] coordinates = new int[2];
        int generatedPosition = generatePositionToPlay();
        while (!isGeneratedPositionValid(generatedPosition)) {
            generatedPosition = generatePositionToPlay();
        }

        coordinates[0] = generatedPosition / PropertiesManager.getInstance().getGameBoardSize() + 1;
        coordinates[1] = generatedPosition % PropertiesManager.getInstance().getGameBoardSize() + 1;
        this.setxPositionToPlay(coordinates[0]);
        this.setyPositionToPlay(coordinates[1]);

        return coordinates;

    }

    private boolean isGeneratedPositionValid(int generatedPosition) {
        for (Integer position :
                filledTilePositionList) {
            if (position.equals(generatedPosition))
                return false;
        }

        return true;
    }

    private int generatePositionToPlay() {
        Random generator = new Random();
        return generator.nextInt(PropertiesManager.getInstance().getGameBoardSize() * PropertiesManager.getInstance().getGameBoardSize()) + 1;
    }

    @Override
    public void update(int position, char newChar) {
        filledTilePositionList.add(position);
    }

    @Override
    public void update(GameNotification gameNotification) {

    }
}
