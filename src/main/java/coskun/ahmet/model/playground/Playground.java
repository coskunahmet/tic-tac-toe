package coskun.ahmet.model.playground;

import coskun.ahmet.utils.PropertiesManager;

import java.util.HashMap;
import java.util.Map;

public class Playground {

    private Map<Integer, PlaygroundTile> playgroundTileList;
    private int sizeOfPlayGroundInt;

    private static Playground instance = null;

    private Playground() {
        String sizeOfPlaygroundStr = PropertiesManager.getInstance().getProperty(PropertiesManager.SIZE_OF_PLAYGROUND);
        sizeOfPlayGroundInt = Integer.parseInt(sizeOfPlaygroundStr);
        initPlayGround(sizeOfPlayGroundInt);
    }

    private void initPlayGround(int size) {

        playgroundTileList = new HashMap<Integer, PlaygroundTile>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                PlaygroundTile newTile = new PlaygroundTile();
                int positionOfNewTile = i * sizeOfPlayGroundInt + j;
                newTile.setPosition(positionOfNewTile);

                //new tile has an left neighbour
                if (j != 0) {
                    newTile.setLeftTile(playgroundTileList.get(positionOfNewTile - 1));
                    playgroundTileList.get(positionOfNewTile - 1).setRightTile(newTile);
                }

                //new tile has an upper neighbour
                if (i != 0) {
                    newTile.setUpperTile(playgroundTileList.get(positionOfNewTile - sizeOfPlayGroundInt));
                    playgroundTileList.get(positionOfNewTile - sizeOfPlayGroundInt).setLowerTile(newTile);
                }

                //new tile has left upper neighbour
                if (i != 0 && j != 0) {
                    newTile.setLeftUpperTile(playgroundTileList.get(positionOfNewTile - sizeOfPlayGroundInt - 1));
                    playgroundTileList.get(positionOfNewTile - sizeOfPlayGroundInt - 1).setRightLowerTile(newTile);
                }

                //new tile has right upper neighbour
                if (i != 0 && j != sizeOfPlayGroundInt - 1) {
                    newTile.setRightUpperTile(playgroundTileList.get(positionOfNewTile - sizeOfPlayGroundInt + 1));
                    playgroundTileList.get(positionOfNewTile - sizeOfPlayGroundInt + 1).setLeftLowerTile(newTile);
                }

                playgroundTileList.put(positionOfNewTile, newTile);
            }
        }

    }

    public String getPlayGroundStr() {

        StringBuilder playground = new StringBuilder();

        for (int i = 0; i < sizeOfPlayGroundInt; i++) {
            for (int j = 0; j < sizeOfPlayGroundInt; j++) {
                playground.append(playgroundTileList.get(i * sizeOfPlayGroundInt + j).getPosition()).append(" ");
            }
            playground.append("\n");
        }

        return playground.toString();
    }


    public String getTileAndNeighboursStr(PlaygroundTile playgroundTile) {

        String tileAndNeighbours = "";

        tileAndNeighbours += "----------" + playgroundTile.getPositionOnMatrix(sizeOfPlayGroundInt) + " Tile----------";
        tileAndNeighbours += "\n";

        tileAndNeighbours += getTileInfStr(playgroundTile.getLeftUpperTile());
        tileAndNeighbours += getTileInfStr(playgroundTile.getUpperTile());
        tileAndNeighbours += getTileInfStr(playgroundTile.getRightUpperTile());

        tileAndNeighbours += "\n";

        tileAndNeighbours += getTileInfStr(playgroundTile.getLeftTile());
        tileAndNeighbours += getTileInfStr(playgroundTile);
        tileAndNeighbours += getTileInfStr(playgroundTile.getRightTile());

        tileAndNeighbours += "\n";

        tileAndNeighbours += getTileInfStr(playgroundTile.getLeftLowerTile());
        tileAndNeighbours += getTileInfStr(playgroundTile.getLowerTile());
        tileAndNeighbours += getTileInfStr(playgroundTile.getRightLowerTile());

        tileAndNeighbours += "\n";
        tileAndNeighbours += "----------------------------";

        return tileAndNeighbours;

    }

    private String getTileInfStr(PlaygroundTile playgroundTile) {

        String tileInf = "";

        //Didn't put PlaygroundTile a toString method because of not to keep size of playground in PlaygroundTile
        if (playgroundTile != null)
            tileInf += playgroundTile.getPositionOnMatrix(sizeOfPlayGroundInt) + "-" + playgroundTile.getCurrentCharOnTile() + "\t";
        else
            tileInf += "~\t\t";

        return tileInf;
    }


    public static Playground getInstance() {
        if (instance == null) {
            instance = new Playground();
        }

        return instance;
    }

    public Map<Integer, PlaygroundTile> getPlaygroundTileList() {
        return playgroundTileList;
    }
}
