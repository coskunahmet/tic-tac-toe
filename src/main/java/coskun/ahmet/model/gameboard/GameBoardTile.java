package coskun.ahmet.model.gameboard;

public class GameBoardTile {

    private int position;
    private char currentCharOnTile;

    private GameBoardTile rightTile;
    private GameBoardTile leftTile;
    private GameBoardTile upperTile;
    private GameBoardTile lowerTile;
    private GameBoardTile rightUpperTile;
    private GameBoardTile rightLowerTile;
    private GameBoardTile leftUpperTile;
    private GameBoardTile leftLowerTile;


    public char getCurrentCharOnTile() {
        return currentCharOnTile;
    }

    public void setCurrentCharOnTile(char currentCharOnTile) {
        this.currentCharOnTile = currentCharOnTile;
    }

    public GameBoardTile getRightTile() {
        return rightTile;
    }

    public void setRightTile(GameBoardTile rightTile) {
        this.rightTile = rightTile;
    }

    public GameBoardTile getLeftTile() {
        return leftTile;
    }

    public void setLeftTile(GameBoardTile leftTile) {
        this.leftTile = leftTile;
    }

    public GameBoardTile getUpperTile() {
        return upperTile;
    }

    public void setUpperTile(GameBoardTile upperTile) {
        this.upperTile = upperTile;
    }

    public GameBoardTile getLowerTile() {
        return lowerTile;
    }

    public void setLowerTile(GameBoardTile lowerTile) {
        this.lowerTile = lowerTile;
    }

    public GameBoardTile getRightUpperTile() {
        return rightUpperTile;
    }

    public void setRightUpperTile(GameBoardTile rightUpperTile) {
        this.rightUpperTile = rightUpperTile;
    }

    public GameBoardTile getRightLowerTile() {
        return rightLowerTile;
    }

    public void setRightLowerTile(GameBoardTile rightLowerTile) {
        this.rightLowerTile = rightLowerTile;
    }

    public GameBoardTile getLeftUpperTile() {
        return leftUpperTile;
    }

    public void setLeftUpperTile(GameBoardTile leftUpperTile) {
        this.leftUpperTile = leftUpperTile;
    }

    public GameBoardTile getLeftLowerTile() {
        return leftLowerTile;
    }

    public void setLeftLowerTile(GameBoardTile leftLowerTile) {
        this.leftLowerTile = leftLowerTile;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setPosition(int positionX, int positionY, int sizeOfGameBoard) {
        this.position = (positionX - 1) * sizeOfGameBoard + (positionY - 1);
    }

    public String getPositionOnMatrix(int sizeOfGameBoard) {
        return getPosition() / sizeOfGameBoard + "*" + getPosition() % sizeOfGameBoard;
    }

}
