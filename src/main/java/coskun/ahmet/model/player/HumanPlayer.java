package coskun.ahmet.model.player;

import coskun.ahmet.controller.IInputController;
import coskun.ahmet.controller.InputController;
import coskun.ahmet.exception.InvalidInputException;

import java.util.NoSuchElementException;

public class HumanPlayer extends Player {


    private IInputController inputController;

    private static final String COORDINATE_DELIMITER = ",";

    public HumanPlayer(String name, char symbol) {
        super(name, symbol);
        inputController = new InputController();
    }

    @Override
    public int[] getInput() throws InvalidInputException, NoSuchElementException {

        int[] coordinates = inputController.getInput();
        this.setxPositionToPlay(coordinates[0]);
        this.setyPositionToPlay(coordinates[1]);

        return coordinates;

    }
}
