package coskun.ahmet.view;

import coskun.ahmet.enums.GameNotificationEnum;
import coskun.ahmet.model.GameNotification;
import coskun.ahmet.model.GameViewNotification;
import coskun.ahmet.observer.GameMoveObserver;
import coskun.ahmet.observer.ObserverManager;
import coskun.ahmet.utils.PropertiesManager;

public class GameView extends GameMoveObserver implements IGameView {

    private char[][] gameBoard;
    private int sizeOfGameBoardInt;

    public GameView() {
        sizeOfGameBoardInt = PropertiesManager.getInstance().getGameBoardSize();

        gameBoard = new char[sizeOfGameBoardInt][sizeOfGameBoardInt];

        for (int i = 0; i < sizeOfGameBoardInt; i++) {
            for (int j = 0; j < sizeOfGameBoardInt; j++) {
                gameBoard[i][j] = PropertiesManager.getInstance().getGameProperty(PropertiesManager.EMPTY_TILE_CHAR_KEY).charAt(0);
            }
        }

        ObserverManager.getInstance().attachGameMoveObserver(this, PropertiesManager.getInstance().getTopicProperty(PropertiesManager.GAME_MOVE_VIEW_TOPIC_NAME_KEY));
        ObserverManager.getInstance().attachObserver(this, PropertiesManager.getInstance().getTopicProperty(PropertiesManager.GAME_NOTIFICATIONS_TOPIC_NAME_KEY));
    }

    public void showGameBoard() {

        for (int i = 0; i < sizeOfGameBoardInt; i++) {
            System.out.println();
            for (int j = 0; j < sizeOfGameBoardInt; j++) {
                System.out.print(gameBoard[i][j]);
            }
        }
        System.out.println();
        System.out.println();
    }

    public void update(int position, char newChar) {
        gameBoard[position / sizeOfGameBoardInt][position % sizeOfGameBoardInt] = newChar;

        showGameBoard();
    }

    public void update(GameNotification gameNotification) {
        if (gameNotification instanceof GameViewNotification) {
            if (gameNotification.getGameNotificationEnum().equals(GameNotificationEnum.TURN_OF_PLAYER)) {
                System.out.print(((GameViewNotification) gameNotification).getParameterList().get(0) + "'s Turn: ");
            } else if (gameNotification.getGameNotificationEnum().equals(GameNotificationEnum.PLAYER_PLAYED)) {
                System.out.println(((GameViewNotification) gameNotification).getParameterList().get(0)
                        + " played to "
                        + ((GameViewNotification) gameNotification).getParameterList().get(1)
                        + ","
                        + ((GameViewNotification) gameNotification).getParameterList().get(2));
                System.out.println();
                System.out.println("---------------------------");
            } else if (gameNotification.getGameNotificationEnum().equals(GameNotificationEnum.GAME_END)) {
                System.out.println("Game END. Winner: " + ((GameViewNotification) gameNotification).getParameterList());
            } else if (gameNotification.getGameNotificationEnum().equals(GameNotificationEnum.INVALID_INPUT)) {
                System.out.println("Invalid Input. Valid Input Pattern: <integer between 1 and " + PropertiesManager.getInstance().getGameBoardSize() + ">,<integer between 1 and " + PropertiesManager.getInstance().getGameBoardSize() + ">");
            } else if (gameNotification.getGameNotificationEnum().equals(GameNotificationEnum.GAME_END_WITHOUT_WINNER)) {
                System.out.println("Game END. No Winner.");
            }
        } else if (gameNotification.getGameNotificationEnum().equals(GameNotificationEnum.MOVE_IS_NOT_VALID)) {
            System.out.println("Tile is full.");
        }
    }

}
