package coskun.ahmet.observer;

public interface IGameMoveObserver extends IObserver {

    void update(int position, char newChar);
}
