package coskun.ahmet.runner;

import coskun.ahmet.controller.GameController;
import coskun.ahmet.controller.IGameController;
import coskun.ahmet.model.gameboard.GameBoard;
import coskun.ahmet.utils.PropertiesManager;
import coskun.ahmet.view.GameView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameRunner {

    private final static Logger LOGGER = LoggerFactory.getLogger(GameRunner.class);

    public static void main(String[] args) {

        if (args.length == 1) {
            LOGGER.info("Argument passed: " + args[0]);
            PropertiesManager.GAME_CONFIG_FILE = args[0];
            PropertiesManager.DEFAULT_CONFIG = false;
            LOGGER.info("Properties Game Config File was set to " + PropertiesManager.GAME_CONFIG_FILE);
        } else if (args.length > 1) {
            System.out.println("More than one argument");
            LOGGER.error("More than one argument");
            return;
        }

        PropertiesManager propertiesManagerInstance = PropertiesManager.getInstance();

        if (!propertiesManagerInstance.isPropertiesValid()) {
            for (String error :
                    propertiesManagerInstance.errorMessages) {
                System.out.println(error);
            }
            return;
        }

        IGameController gameController = new GameController();
        GameView gameViewObserver = new GameView();
        GameBoard gameBoardObserver = new GameBoard();

        gameController.init();
        gameController.start();


    }
}
