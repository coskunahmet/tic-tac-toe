package coskun.ahmet.model.gameboard;

import coskun.ahmet.utils.PropertiesManager;

import java.util.HashMap;
import java.util.Map;

public class GameBoard implements IGameBoard {

    private Map<Integer, GameBoardTile> gameBoardTileList;
    private int sizeOfGameBoardInt;

    private static GameBoard instance = null;

    private GameBoard() {
        String sizeOfGameBoardStr = PropertiesManager.getInstance().getGameProperty(PropertiesManager.SIZE_OF_GAME_BOARD);
        sizeOfGameBoardInt = Integer.parseInt(sizeOfGameBoardStr);
        initGameBoard(sizeOfGameBoardInt);
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

    public void updateGameBoard(int position, char newChar) {

        GameBoardTile newGameBoardTile = new GameBoardTile();
        newGameBoardTile.setPosition(position);
        newGameBoardTile.setCurrentCharOnTile(newChar);

        GameBoardTile oldGameBoardTile = gameBoardTileList.get(newGameBoardTile.getPosition());

        GameBoardTile rightTile = oldGameBoardTile.getRightTile();
        newGameBoardTile.setRightTile(rightTile);
        if(rightTile != null)
            rightTile.setLeftTile(newGameBoardTile);

        GameBoardTile leftTile = oldGameBoardTile.getLeftTile();
        newGameBoardTile.setLeftTile(leftTile);
        if(leftTile != null)
            leftTile.setRightTile(newGameBoardTile);

        GameBoardTile upperTile = oldGameBoardTile.getUpperTile();
        newGameBoardTile.setUpperTile(upperTile);
        if(upperTile != null)
            upperTile.setLowerTile(newGameBoardTile);

        GameBoardTile lowerTile = oldGameBoardTile.getLowerTile();
        newGameBoardTile.setLowerTile(lowerTile);
        if(lowerTile != null)
            lowerTile.setUpperTile(newGameBoardTile);

        GameBoardTile rightUpperTile = oldGameBoardTile.getRightUpperTile();
        newGameBoardTile.setRightUpperTile(rightUpperTile);
        if(rightUpperTile != null)
            rightUpperTile.setLeftLowerTile(newGameBoardTile);

        GameBoardTile rightLowerTile = oldGameBoardTile.getRightLowerTile();
        newGameBoardTile.setRightLowerTile(rightLowerTile);
        if(rightLowerTile != null)
            rightLowerTile.setLeftUpperTile(newGameBoardTile);

        GameBoardTile leftUpperTile = oldGameBoardTile.getLeftUpperTile();
        newGameBoardTile.setLeftUpperTile(leftUpperTile);
        if(leftUpperTile != null)
            leftUpperTile.setRightLowerTile(newGameBoardTile);

        GameBoardTile leftLowerTile = oldGameBoardTile.getLeftLowerTile();
        newGameBoardTile.setLeftLowerTile(leftLowerTile);
        if(leftLowerTile != null)
            leftLowerTile.setRightUpperTile(newGameBoardTile);

        gameBoardTileList.remove(newGameBoardTile.getPosition());
        gameBoardTileList.put(newGameBoardTile.getPosition(), newGameBoardTile);
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


    public static GameBoard getInstance() {
        if (instance == null) {
            instance = new GameBoard();
        }

        return instance;
    }

    public Map<Integer, GameBoardTile> getGameBoardTileList() {
        return gameBoardTileList;
    }
}
