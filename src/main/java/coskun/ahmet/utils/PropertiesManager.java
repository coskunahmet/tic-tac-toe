package coskun.ahmet.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesManager {

    private final static Logger LOGGER = LoggerFactory.getLogger(PropertiesManager.class);

    private final static String GAME_CONFIG_FILE = "config" + File.separator + "config.properties";
    private final static String TOPIC_CONFIG_FILE = "topic" + File.separator + "topic.properties";

    public final static String SIZE_OF_GAME_BOARD_KEY = "size.of.game.board";
    public final static String FIRST_PLAYER_CHAR_KEY = "first.player.character";
    public final static String SECOND_PLAYER_CHAR_KEY = "second.player.character";
    public final static String COMPUTER_PLAYER_CHAR_KEY = "computer.player.character";
    public final static String MODEL_TOPIC_NAME_KEY = "model.topic.name";
    public final static String VIEW_TOPIC_NAME_KEY = "view.topic.name";

    private Properties gameProperties;

    private Properties topicProperties;

    private static PropertiesManager instance = null;

    private PropertiesManager() {
        gameProperties = new Properties();
        topicProperties = new Properties();
    }

    public static PropertiesManager getInstance() {
        if (instance == null) {
            instance = new PropertiesManager();
            instance.loadProperties();
        }

        return instance;
    }

    private void loadProperties() {
        InputStream input = null;
        try {
            input = PropertiesManager.class.getClassLoader().getResourceAsStream(GAME_CONFIG_FILE);
            if (input == null) {
                LOGGER.error("Unable to find configuration file: ", GAME_CONFIG_FILE);
                return;
            }
            gameProperties.load(input);

            input = PropertiesManager.class.getClassLoader().getResourceAsStream(TOPIC_CONFIG_FILE);
            if (input == null) {
                LOGGER.error("Unable to find configuration file: ", TOPIC_CONFIG_FILE);
                return;
            }
            topicProperties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //To check if there is the same char for different player or computer
    public boolean isPropertiesValid() throws NumberFormatException {

        if (!isThereSameCharForDifferentPlayer())
            return false;

        return isSizeOfGameBoardValid();
    }

    private boolean isThereSameCharForDifferentPlayer() {
        Map<String, String> propertiesMap = new HashMap<String, String>();
        for (Object k : this.gameProperties.keySet()) {
            String key = (String) k;
            if (propertiesMap.get(this.gameProperties.getProperty(key)) != null) {
                return false;
            }

            propertiesMap.put(this.gameProperties.getProperty(key), this.gameProperties.getProperty(key));
        }

        return true;
    }

    //size of gameboard should be between 3x3 and 10x10
    private boolean isSizeOfGameBoardValid() throws NumberFormatException {
        String sizeOfGameBoardStr = this.gameProperties.getProperty(SIZE_OF_GAME_BOARD_KEY);

        int sizeOfGameBoardInt = Integer.parseInt(sizeOfGameBoardStr);

        return sizeOfGameBoardInt >= 3 && sizeOfGameBoardInt <= 10;
    }

    public int getGameBoardSize() {
        String sizeOfGameBoardStr = getInstance().getGameProperty(PropertiesManager.SIZE_OF_GAME_BOARD_KEY);

        return Integer.parseInt(sizeOfGameBoardStr);
    }

    public String getGameProperty(String key) {
        String val = null;
        if (key != null) {
            if (gameProperties != null)
                val = gameProperties.getProperty(key);
        }
        return val;

    }

    public String getTopicProperty(String key) {
        String val = null;
        if (key != null) {
            if (topicProperties != null)
                val = topicProperties.getProperty(key);
        }
        return val;

    }
}
