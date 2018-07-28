package coskun.ahmet.controller;

import coskun.ahmet.exception.InvalidInputException;
import coskun.ahmet.utils.PropertiesManager;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputController implements IInputController {

    private static final String COORDINATE_DELIMITER = ",";

    private final String regex = "^\\d+,\\d+$";

    public int[] getInput() throws InvalidInputException, NoSuchElementException {

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        if (isInputValid(input)) {
            return splitInput(input);
        } else {
            throw new InvalidInputException();
        }
    }

    private boolean isInputValid(String input) {

        if (!input.matches(regex))
            return false;

        int[] inputIntArray = splitInput(input);

        if (inputIntArray[0] > PropertiesManager.getInstance().getGameBoardSize()
                || inputIntArray[1] > PropertiesManager.getInstance().getGameBoardSize())
            return false;

        return true;
    }

    private int[] splitInput(String input) {
        String[] coordinatesStr = input.split(InputController.COORDINATE_DELIMITER);
        int coordinates[] = new int[2];
        coordinates[0] = Integer.parseInt(coordinatesStr[0]);
        coordinates[1] = Integer.parseInt(coordinatesStr[1]);

        return coordinates;
    }
}
