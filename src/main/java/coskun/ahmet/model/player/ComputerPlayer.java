package coskun.ahmet.model.player;

import coskun.ahmet.utils.PropertiesManager;

import java.util.Random;

public class ComputerPlayer extends Player {

    public ComputerPlayer(String name, char symbol) {
        super(name, symbol);
    }

    @Override
    public int[] getInput() {

        int[] coordinates = new int[2];
        coordinates[0] = generatePositionToPlay();
        coordinates[1] = generatePositionToPlay();
        this.setxPositionToPlay(coordinates[0]);
        this.setyPositionToPlay(coordinates[1]);

        return coordinates;

    }

    private int generatePositionToPlay() {
        Random generator = new Random();
        return generator.nextInt(PropertiesManager.getInstance().getGameBoardSize()) + 1;
    }
}
