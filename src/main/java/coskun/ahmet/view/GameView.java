package coskun.ahmet.view;

import coskun.ahmet.utils.PropertiesManager;

public class GameView implements IGameView {

    private char[][] playground;
    private int sizeOfPlaygroundInt;

    public GameView() {
        String sizeOfPlaygroundStr = PropertiesManager.getInstance().getGameProperty(PropertiesManager.SIZE_OF_PLAYGROUND);
        sizeOfPlaygroundInt = Integer.parseInt(sizeOfPlaygroundStr);

        playground = new char[sizeOfPlaygroundInt][sizeOfPlaygroundInt];

        for (int i = 0; i < sizeOfPlaygroundInt; i++) {
            for (int j = 0; j < sizeOfPlaygroundInt; j++) {
                playground[i][j] = '~';
            }
        }
    }

    public void showPlayground() {

        for (int i = 0; i < sizeOfPlaygroundInt; i++) {
            System.out.println();
            for (int j = 0; j < sizeOfPlaygroundInt; j++) {
                System.out.print(playground[i][j]);
            }
        }
    }

    public void updatePlayground(int position, char newChar) {
        playground[position / sizeOfPlaygroundInt][position % sizeOfPlaygroundInt] = newChar;
    }

}
