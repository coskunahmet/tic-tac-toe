package coskun.ahmet.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PropertiesManager {

    private final static Logger LOGGER = LoggerFactory.getLogger(PropertiesManager.class);

    public static String GAME_CONFIG_FILE = "config" + File.separator + "config.properties";
    private final static String TOPIC_CONFIG_FILE = "topic" + File.separator + "topic.properties";

    private final static String SIZE_OF_GAME_BOARD_KEY = "size.of.game.board";
    public final static String FIRST_PLAYER_CHAR_KEY = "first.player.character";
    public final static String SECOND_PLAYER_CHAR_KEY = "second.player.character";
    public final static String COMPUTER_PLAYER_CHAR_KEY = "computer.player.character";
    public final static String EMPTY_TILE_CHAR_KEY = "empty.tile.char";
    public final static String GAME_MOVE_MODEL_TOPIC_NAME_KEY = "game.move.model.topic.name";
    public final static String GAME_MOVE_VIEW_TOPIC_NAME_KEY = "game.move.view.topic.name";
    public final static String GAME_NOTIFICATIONS_TOPIC_NAME_KEY = "game.notifications.topic.name";

    private Properties gameProperties;

    private Properties topicProperties;

    private static PropertiesManager instance = null;

    public static boolean DEFAULT_CONFIG = true;

    public List<String> errorMessages = new ArrayList<>();

    private PropertiesManager() {
        gameProperties = new Properties();
        topicProperties = new Properties();
    }

    public static PropertiesManager getInstance() {
        if (instance == null) {
            instance = new PropertiesManager();
            LOGGER.info("Config files are going to be loaded");
            instance.loadProperties();
        }

        return instance;
    }

    private void loadProperties() {
        InputStream input = null;
        try {

            LOGGER.info(GAME_CONFIG_FILE + " is going to be loaded.");
            if (DEFAULT_CONFIG) {
                input = PropertiesManager.class.getClassLoader().getResourceAsStream(GAME_CONFIG_FILE);
                if (input == null) {
                    LOGGER.error("Unable to find configuration file: " + GAME_CONFIG_FILE);
                    return;
                }
            } else {
                input = new FileInputStream(GAME_CONFIG_FILE);
            }
            gameProperties.load(input);

            LOGGER.info(TOPIC_CONFIG_FILE + " is going to be loaded.");
            input = PropertiesManager.class.getClassLoader().getResourceAsStream(TOPIC_CONFIG_FILE);
            if (input == null) {
                LOGGER.error("Unable to find configuration file: " + TOPIC_CONFIG_FILE);
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
    public boolean isPropertiesValid() {

        if (!isThereSameCharForDifferentPlayer()) {
            errorMessages.add("Same Char For Different Player.");
            return false;
        } else if (!isSizeOfGameBoardValid()) {
            errorMessages.add("Size of Board parameter in config file must be between 3 and 10.");
            return false;
        } else if (!isEveryPropertyIsFilled()) {
            errorMessages.add("One of parameters in config file is missing or empty.");
            return false;
        } else if (!isPlayerCharPropertiesMoreThanOneChar()) {
            errorMessages.add("Empty Tile Char or One of Player Characters in config file is more than one character");
            return false;
        }

        return true;
    }

    private boolean isEveryPropertyIsFilled() {
        if (this.gameProperties.getProperty(SIZE_OF_GAME_BOARD_KEY) == null
                || this.gameProperties.getProperty(SIZE_OF_GAME_BOARD_KEY).equals(""))
            return false;
        if (this.gameProperties.getProperty(FIRST_PLAYER_CHAR_KEY) == null
                || this.gameProperties.getProperty(FIRST_PLAYER_CHAR_KEY).equals(""))
            return false;
        if (this.gameProperties.getProperty(SECOND_PLAYER_CHAR_KEY) == null
                || this.gameProperties.getProperty(SECOND_PLAYER_CHAR_KEY).equals(""))
            return false;
        if (this.gameProperties.getProperty(COMPUTER_PLAYER_CHAR_KEY) == null
                || this.gameProperties.getProperty(COMPUTER_PLAYER_CHAR_KEY).equals(""))
            return false;
        if (this.gameProperties.getProperty(EMPTY_TILE_CHAR_KEY) == null
                || this.gameProperties.getProperty(EMPTY_TILE_CHAR_KEY).equals(""))
            return false;

        return true;
    }

    private boolean isPlayerCharPropertiesMoreThanOneChar() {
        if (this.gameProperties.getProperty(FIRST_PLAYER_CHAR_KEY).length() > 1)
            return false;
        if (this.gameProperties.getProperty(SECOND_PLAYER_CHAR_KEY).length() > 1)
            return false;
        if (this.gameProperties.getProperty(COMPUTER_PLAYER_CHAR_KEY).length() > 1)
            return false;
        if (this.gameProperties.getProperty(EMPTY_TILE_CHAR_KEY).length() > 1)
            return false;

        return true;
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
    private boolean isSizeOfGameBoardValid() {
        String sizeOfGameBoardStr = this.gameProperties.getProperty(SIZE_OF_GAME_BOARD_KEY);

        try {
            int sizeOfGameBoardInt = Integer.parseInt(sizeOfGameBoardStr);
            return sizeOfGameBoardInt >= 3 && sizeOfGameBoardInt <= 10;

        } catch (NumberFormatException ex) {
            errorMessages.add("Size of Board parameter in config file must be integer.");
            return false;
        }
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
