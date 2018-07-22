package unit.coskun.ahmet.utils;

import coskun.ahmet.utils.PropertiesManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class PropertiesManagerTest {

    @Test
    public void testPropertiesFileGivenAllInputsWhenHasValueThenGameCanStart() {
        PropertiesManager propertiesManager = null;
        propertiesManager = PropertiesManager.getInstance();

        String sizeOfPlaygroundPropertyName = "size.of.playground";
        String val = propertiesManager.getProperty(sizeOfPlaygroundPropertyName);
        assertNotEquals(sizeOfPlaygroundPropertyName + " value should be different than null", val, null);
        assertNotEquals(sizeOfPlaygroundPropertyName + " value should be different than empty", val, "");

        String firstPlayerCharacterPropertyName = "first.player.character";
        val = propertiesManager.getProperty(firstPlayerCharacterPropertyName);
        assertNotEquals(firstPlayerCharacterPropertyName + " value should be different than null", val, null);
        assertNotEquals(firstPlayerCharacterPropertyName + " value should be different than empty", val, "");

        String secondPlayerCharacterPropertyName = "second.player.character";
        val = propertiesManager.getProperty(secondPlayerCharacterPropertyName);
        assertNotEquals(secondPlayerCharacterPropertyName + " value should be different than null", val, null);
        assertNotEquals(secondPlayerCharacterPropertyName + " value should be different than empty", val, "");

        String computerPlayerCharacterPropertyName = "computer.player.character";
        val = propertiesManager.getProperty(computerPlayerCharacterPropertyName);
        assertNotEquals(computerPlayerCharacterPropertyName + " value should be different than null", val, null);
        assertNotEquals(computerPlayerCharacterPropertyName + " value should be different than empty", val, "");
    }

    @Test
    public void testPropertiesFileGivenTwoPlayerInputWhenSameCharThenGameCanNotStart() {
        PropertiesManager propertiesManager = null;
        propertiesManager = PropertiesManager.getInstance();

        assertTrue(propertiesManager.isPropertiesValid());
    }
}
