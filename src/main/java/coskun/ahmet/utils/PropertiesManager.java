package coskun.ahmet.utils;

import coskun.ahmet.exception.PropertiesNotValidException;
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

    public final static String SIZE_OF_PLAYGROUND = "size.of.playground";

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

    //To check if there is the same char for different player or computer
    public boolean isPropertiesValid() throws NumberFormatException{

        if(!isThereSameCharForDifferentPlayer())
            return false;

        if(!isSizeOfPlayGroundValid())
            return false;

        return true;
    }

    public boolean isThereSameCharForDifferentPlayer() {
        Map<String, String> propertiesMap = new HashMap<String, String>();
        for(Object k: this.gameProperties.keySet()) {
            String key = (String)k;
            if(propertiesMap.get(this.gameProperties.getProperty(key)) != null) {
                return false;
            }

            propertiesMap.put(this.gameProperties.getProperty(key), this.gameProperties.getProperty(key));
        }

        return true;
    }

    //size of playground should be between 3x3 and 10x10
    public boolean isSizeOfPlayGroundValid() throws NumberFormatException{
        String sizeOfPlaygroundStr = this.gameProperties.getProperty(SIZE_OF_PLAYGROUND);

        int sizeOfPlaygroundInt = Integer.parseInt(sizeOfPlaygroundStr);

        if(sizeOfPlaygroundInt < 3  || sizeOfPlaygroundInt > 10) {
            return false;
        }

        return true;
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
