package coskun.ahmet.view;

import coskun.ahmet.enums.GameNotification;
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
                gameBoard[i][j] = '~';
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
    }

    public void update(int position, char newChar) {
        gameBoard[position / sizeOfGameBoardInt][position % sizeOfGameBoardInt] = newChar;

        showGameBoard();
    }

    public void update(GameNotification gameNotification) {
        //TODO show end message
    }

}
