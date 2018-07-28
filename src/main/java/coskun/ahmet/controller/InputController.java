package coskun.ahmet.controller;

import coskun.ahmet.exception.InvalidInputException;

import java.util.Scanner;

public class InputController implements IInputController {

    private static final String COORDINATE_DELIMITER = ",";

    private final String regex = "^\\d+,\\d+$";

    public int[] getInput() throws InvalidInputException {

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        if (isInputValid(input)) {
            String[] coordinatesStr = input.split(InputController.COORDINATE_DELIMITER);
            int coordinates[] = new int[2];
            coordinates[0] = Integer.parseInt(coordinatesStr[0]);
            coordinates[1] = Integer.parseInt(coordinatesStr[1]);

            return coordinates;
        } else {
            throw new InvalidInputException();
        }
    }

    private boolean isInputValid(String input) {

        if (input.matches(regex))
            return true;

        return false;
    }
}
