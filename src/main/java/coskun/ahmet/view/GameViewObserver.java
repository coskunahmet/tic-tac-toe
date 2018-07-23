package coskun.ahmet.view;

import coskun.ahmet.Observer;

public class GameViewObserver extends Observer {

    private IGameView gameView = new GameView();

    public void update(int position, char newChar) {
        gameView.updateGameBoard(position, newChar);
    }

    public IGameView getGameView() {
        return gameView;
    }

    public void setGameView(IGameView gameView) {
        this.gameView = gameView;
    }
}
