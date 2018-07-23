package unit.coskun.ahmet.utils;

import coskun.ahmet.utils.PropertiesManager;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class PropertiesManagerTest {

    @Test
    public void testPropertiesFileGivenAllInputsWhenHasValueThenGameCanStart() {
        PropertiesManager propertiesManager = null;
        propertiesManager = PropertiesManager.getInstance();

        String sizeOfGameBoardPropertyName = "size.of.game.board";
        String val = propertiesManager.getGameProperty(sizeOfGameBoardPropertyName);
        assertNotEquals(sizeOfGameBoardPropertyName + " value should be different than null", val, null);
        assertNotEquals(sizeOfGameBoardPropertyName + " value should be different than empty", val, "");

        String firstPlayerCharacterPropertyName = "first.player.character";
        val = propertiesManager.getGameProperty(firstPlayerCharacterPropertyName);
        assertNotEquals(firstPlayerCharacterPropertyName + " value should be different than null", val, null);
        assertNotEquals(firstPlayerCharacterPropertyName + " value should be different than empty", val, "");

        String secondPlayerCharacterPropertyName = "second.player.character";
        val = propertiesManager.getGameProperty(secondPlayerCharacterPropertyName);
        assertNotEquals(secondPlayerCharacterPropertyName + " value should be different than null", val, null);
        assertNotEquals(secondPlayerCharacterPropertyName + " value should be different than empty", val, "");

        String computerPlayerCharacterPropertyName = "computer.player.character";
        val = propertiesManager.getGameProperty(computerPlayerCharacterPropertyName);
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
