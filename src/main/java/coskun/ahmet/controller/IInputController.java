package coskun.ahmet.controller;

import coskun.ahmet.exception.InvalidInputException;

public interface IInputController {
    int[] getInput() throws InvalidInputException;
}
