package coskun.ahmet.controller;

import java.util.Scanner;

public class InputController implements IInputController {

    public String getInput() {

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        return input;

    }

    public void checkInput() {

        //TODO check if input valid
    }
}
