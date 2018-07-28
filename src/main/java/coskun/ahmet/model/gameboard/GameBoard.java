package coskun.ahmet.model.gameboard;

import coskun.ahmet.enums.GameNotification;
import coskun.ahmet.observer.GameMoveObserver;
import coskun.ahmet.observer.ObserverManager;
import coskun.ahmet.utils.PropertiesManager;

import java.util.HashMap;
import java.util.Map;

public class GameBoard extends GameMoveObserver implements IGameBoard {

    private Map<Integer, GameBoardTile> gameBoardTileList;
    private int sizeOfGameBoardInt;

    private boolean isGameComplete;

    public GameBoard() {
        sizeOfGameBoardInt = PropertiesManager.getInstance().getGameBoardSize();
        initGameBoard(sizeOfGameBoardInt);


        ObserverManager.getInstance().attachGameMoveObserver(this, PropertiesManager.getInstance().getTopicProperty(PropertiesManager.GAME_MOVE_MODEL_TOPIC_NAME_KEY));
        ObserverManager.getInstance().attachObserver(this, PropertiesManager.getInstance().getTopicProperty(PropertiesManager.GAME_NOTIFICATIONS_TOPIC_NAME_KEY));
    }

    private void initGameBoard(int size) {

        gameBoardTileList = new HashMap<Integer, GameBoardTile>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                GameBoardTile newTile = new GameBoardTile();
                int positionOfNewTile = i * sizeOfGameBoardInt + j;
                newTile.setPosition(positionOfNewTile);

                //new tile has an left neighbour
                if (j != 0) {
                    newTile.setLeftTile(gameBoardTileList.get(positionOfNewTile - 1));
                    gameBoardTileList.get(positionOfNewTile - 1).setRightTile(newTile);
                }

                //new tile has an upper neighbour
                if (i != 0) {
                    newTile.setUpperTile(gameBoardTileList.get(positionOfNewTile - sizeOfGameBoardInt));
                    gameBoardTileList.get(positionOfNewTile - sizeOfGameBoardInt).setLowerTile(newTile);
                }

                //new tile has left upper neighbour
                if (i != 0 && j != 0) {
                    newTile.setLeftUpperTile(gameBoardTileList.get(positionOfNewTile - sizeOfGameBoardInt - 1));
                    gameBoardTileList.get(positionOfNewTile - sizeOfGameBoardInt - 1).setRightLowerTile(newTile);
                }

                //new tile has right upper neighbour
                if (i != 0 && j != sizeOfGameBoardInt - 1) {
                    newTile.setRightUpperTile(gameBoardTileList.get(positionOfNewTile - sizeOfGameBoardInt + 1));
                    gameBoardTileList.get(positionOfNewTile - sizeOfGameBoardInt + 1).setLeftLowerTile(newTile);
                }

                gameBoardTileList.put(positionOfNewTile, newTile);
            }
        }

    }

    public void update(GameNotification gameNotification) {
        //TODO notification update
    }

    public void update(int position, char newChar) {

        GameBoardTile newGameBoardTile = new GameBoardTile();
        newGameBoardTile.setPosition(position);
        newGameBoardTile.setCurrentCharOnTile(newChar);

        GameBoardTile oldGameBoardTile = gameBoardTileList.get(newGameBoardTile.getPosition());

        if (!isMoveValid(newGameBoardTile)) {
            ObserverManager.getInstance().setGameNotification(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.GAME_NOTIFICATIONS_TOPIC_NAME_KEY), GameNotification.MOVE_IS_NOT_VALID);
        } else {

            GameBoardTile rightTile = oldGameBoardTile.getRightTile();
            newGameBoardTile.setRightTile(rightTile);
            if (rightTile != null)
                rightTile.setLeftTile(newGameBoardTile);

            GameBoardTile leftTile = oldGameBoardTile.getLeftTile();
            newGameBoardTile.setLeftTile(leftTile);
            if (leftTile != null)
                leftTile.setRightTile(newGameBoardTile);

            GameBoardTile upperTile = oldGameBoardTile.getUpperTile();
            newGameBoardTile.setUpperTile(upperTile);
            if (upperTile != null)
                upperTile.setLowerTile(newGameBoardTile);

            GameBoardTile lowerTile = oldGameBoardTile.getLowerTile();
            newGameBoardTile.setLowerTile(lowerTile);
            if (lowerTile != null)
                lowerTile.setUpperTile(newGameBoardTile);

            GameBoardTile rightUpperTile = oldGameBoardTile.getRightUpperTile();
            newGameBoardTile.setRightUpperTile(rightUpperTile);
            if (rightUpperTile != null)
                rightUpperTile.setLeftLowerTile(newGameBoardTile);

            GameBoardTile rightLowerTile = oldGameBoardTile.getRightLowerTile();
            newGameBoardTile.setRightLowerTile(rightLowerTile);
            if (rightLowerTile != null)
                rightLowerTile.setLeftUpperTile(newGameBoardTile);

            GameBoardTile leftUpperTile = oldGameBoardTile.getLeftUpperTile();
            newGameBoardTile.setLeftUpperTile(leftUpperTile);
            if (leftUpperTile != null)
                leftUpperTile.setRightLowerTile(newGameBoardTile);

            GameBoardTile leftLowerTile = oldGameBoardTile.getLeftLowerTile();
            newGameBoardTile.setLeftLowerTile(leftLowerTile);
            if (leftLowerTile != null)
                leftLowerTile.setRightUpperTile(newGameBoardTile);

            System.out.println("Is Win: " + isWin(newGameBoardTile));

            gameBoardTileList.remove(newGameBoardTile.getPosition());
            gameBoardTileList.put(newGameBoardTile.getPosition(), newGameBoardTile);


            ObserverManager.getInstance().setGameBoardTile(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.GAME_MOVE_VIEW_TOPIC_NAME_KEY), newGameBoardTile);
            ObserverManager.getInstance().setGameNotification(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.GAME_NOTIFICATIONS_TOPIC_NAME_KEY), GameNotification.NEXT_PLAYER);
        }
    }

    public boolean isWin(GameBoardTile newGameBoardTile) {
        return isRowWin(newGameBoardTile) || isColWin(newGameBoardTile) || isDiagWin(newGameBoardTile);
    }

    public boolean isRowWin(GameBoardTile newGameBoardTile) {

        int getYPosition = newGameBoardTile.getPosition() / sizeOfGameBoardInt;

        GameBoardTile firstTileOnYPosition = gameBoardTileList.get(getYPosition * sizeOfGameBoardInt);
        int numberOfCharRepeat = 0;

        for(int i = 0; i < sizeOfGameBoardInt && numberOfCharRepeat < 3; i++) {
            if(firstTileOnYPosition.getCurrentCharOnTile() == newGameBoardTile.getCurrentCharOnTile())
                numberOfCharRepeat++;
            else
                numberOfCharRepeat = 0;

            firstTileOnYPosition = firstTileOnYPosition.getRightTile();

        }
        if(numberOfCharRepeat == 3)
            return true;

        return false;
    }
    public boolean isColWin(GameBoardTile newGameBoardTile) {

        return false;
    }
    public boolean isDiagWin(GameBoardTile newGameBoardTile) {

        return false;
    }

    private boolean isMoveValid(GameBoardTile newPlayedGameBoardTile) {
        if (newPlayedGameBoardTile == null)
            return false;

        return gameBoardTileList.get(newPlayedGameBoardTile.getPosition()) == null;
    }

    public String getGameBoardStr() {

        StringBuilder gameBoard = new StringBuilder();

        for (int i = 0; i < sizeOfGameBoardInt; i++) {
            for (int j = 0; j < sizeOfGameBoardInt; j++) {
                gameBoard.append(gameBoardTileList.get(i * sizeOfGameBoardInt + j).getPosition()).append(" ");
            }
            gameBoard.append("\n");
        }

        return gameBoard.toString();
    }


    public String getTileAndNeighboursStr(GameBoardTile gameBoardTile) {

        String tileAndNeighbours = "";

        tileAndNeighbours += "----------" + gameBoardTile.getPositionOnMatrix(sizeOfGameBoardInt) + " Tile----------";
        tileAndNeighbours += "\n";

        tileAndNeighbours += getTileInfStr(gameBoardTile.getLeftUpperTile());
        tileAndNeighbours += getTileInfStr(gameBoardTile.getUpperTile());
        tileAndNeighbours += getTileInfStr(gameBoardTile.getRightUpperTile());

        tileAndNeighbours += "\n";

        tileAndNeighbours += getTileInfStr(gameBoardTile.getLeftTile());
        tileAndNeighbours += getTileInfStr(gameBoardTile);
        tileAndNeighbours += getTileInfStr(gameBoardTile.getRightTile());

        tileAndNeighbours += "\n";

        tileAndNeighbours += getTileInfStr(gameBoardTile.getLeftLowerTile());
        tileAndNeighbours += getTileInfStr(gameBoardTile.getLowerTile());
        tileAndNeighbours += getTileInfStr(gameBoardTile.getRightLowerTile());

        tileAndNeighbours += "\n";
        tileAndNeighbours += "----------------------------";

        return tileAndNeighbours;

    }

    private String getTileInfStr(GameBoardTile gameBoardTile) {

        String tileInf = "";

        //Didn't put GameBoardTile a toString method because of not to keep size of gameboard in GameBoardTile
        if (gameBoardTile != null)
            tileInf += gameBoardTile.getPositionOnMatrix(sizeOfGameBoardInt) + "-" + gameBoardTile.getCurrentCharOnTile() + "\t";
        else
            tileInf += "~\t\t";

        return tileInf;
    }


    public Map<Integer, GameBoardTile> getGameBoardTileList() {
        return gameBoardTileList;
    }
}
