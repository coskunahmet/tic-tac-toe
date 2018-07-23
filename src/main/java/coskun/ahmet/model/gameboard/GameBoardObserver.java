package coskun.ahmet.model.gameboard;

import coskun.ahmet.Observer;

public class GameBoardObserver extends Observer {

    public void update(int position, char newChar) {
        GameBoard.getInstance().updateGameBoard(position, newChar);
    }
}
