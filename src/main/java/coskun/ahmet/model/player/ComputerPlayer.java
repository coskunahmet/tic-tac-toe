package coskun.ahmet.model.player;

import coskun.ahmet.utils.PropertiesManager;

import java.util.Random;

public class ComputerPlayer extends Player{

    public ComputerPlayer(String name, char symbol) {
        super(name, symbol);
    }

    @Override
    public void play() {
        this.setxPositionToPlay(generatePositionToPlay());
        this.setyPositionToPlay(generatePositionToPlay());
    }

    public int generatePositionToPlay() {
        Random generator = new Random();
        return generator.nextInt(PropertiesManager.getInstance().getGameBoardSize()) + 1;
    }
}
