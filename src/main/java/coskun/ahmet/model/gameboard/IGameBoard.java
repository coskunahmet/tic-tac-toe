package coskun.ahmet.model.gameboard;

import java.util.Map;

public interface IGameBoard {

    Map<Integer, GameBoardTile> getGameBoardTileList();

    void update(int position, char newChar);

    String getGameBoardStr();

    String getTileAndNeighboursStr(GameBoardTile gameBoardTile);
}
