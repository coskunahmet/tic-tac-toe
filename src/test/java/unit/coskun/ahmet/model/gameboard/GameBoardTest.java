package unit.coskun.ahmet.model.gameboard;

import coskun.ahmet.model.gameboard.GameBoard;
import coskun.ahmet.model.gameboard.IGameBoard;
import coskun.ahmet.utils.PropertiesManager;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameBoardTest {

    private static IGameBoard gameBoard;
    private static int sizeOfGameBoardInt;

    @BeforeClass
    public static void initializeGameBoard() {
        gameBoard = new GameBoard();
        sizeOfGameBoardInt = PropertiesManager.getInstance().getGameBoardSize();
    }

    @Test
    public void testGameBoardGivenGameBoardIsEmptyGivenUpdateALowerEdgeCellWithFirstPlayerCharThenPutFirstPlayerCharToGameBoard() {
        testGameBoardGivenGameBoardIsEmptyGivenUpdateWithFirstPlayerCharThenPutFirstPlayerCharToGameBoard(sizeOfGameBoardInt * sizeOfGameBoardInt - 1);
    }

    @Test
    public void testGameBoardGivenGameBoardIsEmptyGivenUpdateAUpperEdgeCellWithFirstPlayerCharThenPutFirstPlayerCharToGameBoard() {
        testGameBoardGivenGameBoardIsEmptyGivenUpdateWithFirstPlayerCharThenPutFirstPlayerCharToGameBoard(1);
    }

    @Test
    public void testGameBoardGivenGameBoardIsEmptyGivenUpdateARightEdgeCellWithFirstPlayerCharThenPutFirstPlayerCharToGameBoard() {
        testGameBoardGivenGameBoardIsEmptyGivenUpdateWithFirstPlayerCharThenPutFirstPlayerCharToGameBoard(sizeOfGameBoardInt - 1);
    }

    @Test
    public void testGameBoardGivenGameBoardIsEmptyGivenUpdateLeftEdgeCellWithFirstPlayerCharThenPutFirstPlayerCharToGameBoard() {
        testGameBoardGivenGameBoardIsEmptyGivenUpdateWithFirstPlayerCharThenPutFirstPlayerCharToGameBoard(0);
    }

    public void testGameBoardGivenGameBoardIsEmptyGivenUpdateWithFirstPlayerCharThenPutFirstPlayerCharToGameBoard(int position) {
        gameBoard.update(position, PropertiesManager.getInstance().getGameProperty("first.player.character").charAt(0));

        Assert.assertEquals(gameBoard.getGameBoardTileList().get(0).getCurrentCharOnTile(), 'X');
        Assert.assertEquals(gameBoard.getGameBoardTileList().get(1).getLeftTile().getCurrentCharOnTile(), 'X');
        Assert.assertEquals(gameBoard.getGameBoardTileList().get(1).getLeftTile(), gameBoard.getGameBoardTileList().get(0));
    }

    @Test
    public void testGameBoardInitializeGivenLeftEdgeTilesWhenLeftTileIsNullThenGameBoardIsOk() {
        int positionOfWrongTile = -1;

        for (int i = 0; i < sizeOfGameBoardInt; i++) {
            if (gameBoard.getGameBoardTileList().get(i * sizeOfGameBoardInt).getLeftTile() != null) {
                positionOfWrongTile = i;
                break;
            }
        }

        Assert.assertEquals(positionOfWrongTile, -1);
    }

    @Test
    public void testGameBoardInitializeGivenRightEdgeTilesWhenRightTileIsNullThenGameBoardIsOk() {

        int positionOfWrongTile = -1;

        for (int i = 0; i < sizeOfGameBoardInt; i++) {
            if (gameBoard.getGameBoardTileList().get((i * sizeOfGameBoardInt) + sizeOfGameBoardInt - 1).getRightTile() != null) {
                positionOfWrongTile = i;
                break;
            }
        }

        Assert.assertEquals(positionOfWrongTile, -1);

    }

    @Test
    public void testGameBoardInitializeGivenUpperEdgeTilesWhenUpperTileIsNullThenGameBoardIsOk() {

        int positionOfWrongTile = -1;

        for (int j = 0; j < sizeOfGameBoardInt; j++) {
            if (gameBoard.getGameBoardTileList().get(j).getUpperTile() != null) {
                positionOfWrongTile = j;
                break;
            }
        }

        Assert.assertEquals(positionOfWrongTile, -1);

    }

    @Test
    public void testGameBoardInitializeGivenLowerEdgeTilesWhenLowerTileIsNullThenGameBoardIsOk() {

        int positionOfWrongTile = -1;

        for (int j = 0; j < sizeOfGameBoardInt; j++) {
            if (gameBoard.getGameBoardTileList().get(sizeOfGameBoardInt * sizeOfGameBoardInt - (j + 1)).getLowerTile() != null) {
                positionOfWrongTile = j;
                break;
            }
        }

        Assert.assertEquals(positionOfWrongTile, -1);

    }


    @Test
    public void showGameBoardWhenInitializedProperly() {
        String gameBoardStr = "";

        int sizeOfGameBoardInt = PropertiesManager.getInstance().getGameBoardSize();

        for (int i = 0; i < sizeOfGameBoardInt; i++) {

            for (int j = 0; j < sizeOfGameBoardInt; j++) {
                gameBoardStr += i * sizeOfGameBoardInt + j + " ";
            }
            gameBoardStr += "\n";
        }
        Assert.assertEquals(gameBoardStr, gameBoard.getGameBoardStr());
    }

    @Test
    public void showEachGameBoardTilesWithNeighboursWhenInitializedProperly() {
        int sizeOfGameBoardInt = PropertiesManager.getInstance().getGameBoardSize();

        for (int i = 0; i < sizeOfGameBoardInt; i++)
            for (int j = 0; j < sizeOfGameBoardInt; j++)
                System.out.println(gameBoard.getTileAndNeighboursStr(gameBoard.getGameBoardTileList().get(i * sizeOfGameBoardInt + j)));
    }
}
