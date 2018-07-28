package coskun.ahmet.observer;

public abstract class GameMoveObserver implements IGameMoveObserver {

    public abstract void update(int position, char newChar);
}
