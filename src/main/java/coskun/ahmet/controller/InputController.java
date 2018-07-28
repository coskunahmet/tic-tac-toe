package coskun.ahmet.controller;

import java.util.Scanner;

public class InputController implements IInputController {

    private static final String COORDINATE_DELIMITER = ",";

    public int[] getInput() {

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        checkInput();

        String[] coordinatesStr = input.split(InputController.COORDINATE_DELIMITER);
        int coordinates[] = new int[2];
        coordinates[0] = Integer.parseInt(coordinatesStr[0]);
        coordinates[1] = Integer.parseInt(coordinatesStr[1]);

        return coordinates;

    }

    private void checkInput() {

        //TODO check if input valid
    }
}
