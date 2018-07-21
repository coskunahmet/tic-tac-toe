package coskun.ahmet.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {

    private final static Logger LOGGER = LoggerFactory.getLogger(PropertiesManager.class);

    private final static String GAME_CONFIG_FILE = "config" + File.separator + "config.properties";

    private Properties gameProperties;

    private static PropertiesManager instance = null;

    private PropertiesManager() {
        gameProperties = new Properties();
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

    public String getProperty(String key) {
        String val = null;
        if (key != null) {
            if (gameProperties != null)
                val = gameProperties.getProperty(key);
        }
        return val;

    }

}
