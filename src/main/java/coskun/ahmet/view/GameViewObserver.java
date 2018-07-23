package coskun.ahmet.view;

import coskun.ahmet.Observer;
import coskun.ahmet.model.playground.PlaygroundTile;

public class GameViewObserver extends Observer {

    private IGameView gameView = new GameView();

    public void update(int position, char newChar) {
        gameView.updatePlayground(position, newChar);
    }

    public IGameView getGameView() {
        return gameView;
    }

    public void setGameView(IGameView gameView) {
        this.gameView = gameView;
    }
}
