package coskun.ahmet.view;

import coskun.ahmet.utils.PropertiesManager;

public class GameView implements IGameView {

    private char[][] gameBoard;
    private int sizeOfGameBoardInt;

    public GameView() {
        String sizeOfGameBoardStr = PropertiesManager.getInstance().getGameProperty(PropertiesManager.SIZE_OF_GAME_BOARD);
        sizeOfGameBoardInt = Integer.parseInt(sizeOfGameBoardStr);

        gameBoard = new char[sizeOfGameBoardInt][sizeOfGameBoardInt];

        for (int i = 0; i < sizeOfGameBoardInt; i++) {
            for (int j = 0; j < sizeOfGameBoardInt; j++) {
                gameBoard[i][j] = '~';
            }
        }
    }

    public void showGameBoard() {

        for (int i = 0; i < sizeOfGameBoardInt; i++) {
            System.out.println();
            for (int j = 0; j < sizeOfGameBoardInt; j++) {
                System.out.print(gameBoard[i][j]);
            }
        }
    }

    public void updateGameBoard(int position, char newChar) {
        gameBoard[position / sizeOfGameBoardInt][position % sizeOfGameBoardInt] = newChar;
    }

}
