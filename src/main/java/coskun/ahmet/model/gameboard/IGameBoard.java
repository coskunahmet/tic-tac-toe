package coskun.ahmet.model.gameboard;

import java.util.Map;

public interface IGameBoard {

    Map<Integer, GameBoardTile> getGameBoardTileList();

    void updateGameBoard(int position, char newChar);
}
