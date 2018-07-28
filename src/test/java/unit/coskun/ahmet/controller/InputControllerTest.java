package unit.coskun.ahmet.controller;

import coskun.ahmet.controller.IInputController;
import coskun.ahmet.controller.InputController;
import coskun.ahmet.exception.InvalidInputException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;

public class InputControllerTest {

    private static IInputController inputController;

    @BeforeClass
    public static void initializeInputController() {
        inputController = new InputController();
    }

    @Test(expected = InvalidInputException.class)
    public void testGivenInputWhenWithPeriodCharThenInvalidInputException() throws InvalidInputException {

        String input = "3.2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        inputController.getInput();
    }

    @Test(expected = InvalidInputException.class)
    public void testGivenInputWhenWithNonIntegerValuesThenInvalidInputException() throws InvalidInputException {

        String input = "a,b";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        inputController.getInput();
    }

    @Test(expected = InvalidInputException.class)
    public void testGivenInputWhenWithMoreThanTwoValuesThenInvalidInputException() throws InvalidInputException {

        String input = "5,2,100";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        inputController.getInput();
    }

    @Test(expected = InvalidInputException.class)
    public void testGivenInputWhenWithOneValueThenInvalidInputException() throws InvalidInputException {

        String input = "5";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        inputController.getInput();
    }

    @Test(expected = NoSuchElementException.class)
    public void testGivenInputWhenEmptyThenInvalidInputException() throws InvalidInputException {

        String input = "";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        inputController.getInput();
    }

    @Test
    public void testGivenInputWhenIntegerCommaIntegerThenValid() throws InvalidInputException {

        String input = "2342,2342";
        int[] inputInt = new int[]{2342, 2342};

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);


        Assert.assertArrayEquals(inputInt, inputController.getInput());
    }
}
