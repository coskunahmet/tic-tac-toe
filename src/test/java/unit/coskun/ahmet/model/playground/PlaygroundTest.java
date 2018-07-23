package unit.coskun.ahmet.model.playground;

import coskun.ahmet.model.playground.IPlayground;
import coskun.ahmet.model.playground.Playground;
import coskun.ahmet.utils.PropertiesManager;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlaygroundTest {

    private static IPlayground playground;
    private static int sizeOfPlayGroundInt;

    @BeforeClass
    public static void initializePlayGround() {
        playground = Playground.getInstance();
        String sizeOfPlaygroundStr = PropertiesManager.getInstance().getGameProperty(PropertiesManager.SIZE_OF_PLAYGROUND);
        sizeOfPlayGroundInt = Integer.parseInt(sizeOfPlaygroundStr);
    }

    @Test
    public void testPlayGroundGivenPlaygroundIsEmptyGivenUpdateALowerEdgeCellWithFirstPlayerCharThenPutFirstPlayerCharToPlayground() {
        testPlayGroundGivenPlaygroundIsEmptyGivenUpdateWithFirstPlayerCharThenPutFirstPlayerCharToPlayground(sizeOfPlayGroundInt * sizeOfPlayGroundInt - 1);
    }

    @Test
    public void testPlayGroundGivenPlaygroundIsEmptyGivenUpdateAUpperEdgeCellWithFirstPlayerCharThenPutFirstPlayerCharToPlayground() {
        testPlayGroundGivenPlaygroundIsEmptyGivenUpdateWithFirstPlayerCharThenPutFirstPlayerCharToPlayground(1);
    }

    @Test
    public void testPlayGroundGivenPlaygroundIsEmptyGivenUpdateARightEdgeCellWithFirstPlayerCharThenPutFirstPlayerCharToPlayground() {
        testPlayGroundGivenPlaygroundIsEmptyGivenUpdateWithFirstPlayerCharThenPutFirstPlayerCharToPlayground(sizeOfPlayGroundInt - 1);
    }

    @Test
    public void testPlayGroundGivenPlaygroundIsEmptyGivenUpdateLeftEdgeCellWithFirstPlayerCharThenPutFirstPlayerCharToPlayground() {
        testPlayGroundGivenPlaygroundIsEmptyGivenUpdateWithFirstPlayerCharThenPutFirstPlayerCharToPlayground(0);
    }

    @Test
    public void testPlayGroundGivenPlaygroundIsEmptyGivenUpdateWithFirstPlayerCharThenPutFirstPlayerCharToPlayground(int position) {
        playground.updatePlayground(position, PropertiesManager.getInstance().getGameProperty("first.player.character").charAt(0));

        Assert.assertEquals(playground.getPlaygroundTileList().get(0).getCurrentCharOnTile(), 'X');
        Assert.assertEquals(playground.getPlaygroundTileList().get(1).getLeftTile().getCurrentCharOnTile(), 'X');
        Assert.assertEquals(playground.getPlaygroundTileList().get(1).getLeftTile(), playground.getPlaygroundTileList().get(0));
    }

    @Test
    public void testPlayGroundInitializeGivenLeftEdgeTilesWhenLeftTileIsNullThenPlaygroundIsOk() {
        int positionOfWrongTile = -1;

        for (int i = 0; i < sizeOfPlayGroundInt; i++) {
            if (playground.getPlaygroundTileList().get(i * sizeOfPlayGroundInt).getLeftTile() != null) {
                positionOfWrongTile = i;
                break;
            }
        }

        Assert.assertEquals(positionOfWrongTile, -1);
    }

    @Test
    public void testPlayGroundInitializeGivenRightEdgeTilesWhenRightTileIsNullThenPlaygroundIsOk() {

        int positionOfWrongTile = -1;

        for (int i = 0; i < sizeOfPlayGroundInt; i++) {
            if (playground.getPlaygroundTileList().get((i * sizeOfPlayGroundInt) + sizeOfPlayGroundInt - 1).getRightTile() != null) {
                positionOfWrongTile = i;
                break;
            }
        }

        Assert.assertEquals(positionOfWrongTile, -1);

    }

    @Test
    public void testPlayGroundInitializeGivenUpperEdgeTilesWhenUpperTileIsNullThenPlaygroundIsOk() {

        int positionOfWrongTile = -1;

        for (int j = 0; j < sizeOfPlayGroundInt; j++) {
            if (playground.getPlaygroundTileList().get(j).getUpperTile() != null) {
                positionOfWrongTile = j;
                break;
            }
        }

        Assert.assertEquals(positionOfWrongTile, -1);

    }

    @Test
    public void testPlayGroundInitializeGivenLowerEdgeTilesWhenLowerTileIsNullThenPlaygroundIsOk() {

        int positionOfWrongTile = -1;

        for (int j = 0; j < sizeOfPlayGroundInt; j++) {
            if (playground.getPlaygroundTileList().get(sizeOfPlayGroundInt * sizeOfPlayGroundInt - (j + 1)).getLowerTile() != null) {
                positionOfWrongTile = j;
                break;
            }
        }

        Assert.assertEquals(positionOfWrongTile, -1);

    }


    @Test
    public void showPlayGroundWhenInitializedProperly() {
        Playground playground = Playground.getInstance();
        System.out.println(playground.getPlayGroundStr());
    }

    @Test
    public void showEachPlayGroundTilesWithNeighboursWhenInitializedProperly() {
        Playground playground = Playground.getInstance();
        String sizeOfPlaygroundStr = PropertiesManager.getInstance().getGameProperty(PropertiesManager.SIZE_OF_PLAYGROUND);
        int sizeOfPlayGroundInt = Integer.parseInt(sizeOfPlaygroundStr);

        for (int i = 0; i < sizeOfPlayGroundInt; i++)
            for (int j = 0; j < sizeOfPlayGroundInt; j++)
                System.out.println(playground.getTileAndNeighboursStr(playground.getPlaygroundTileList().get(i * sizeOfPlayGroundInt + j)));
    }
}
