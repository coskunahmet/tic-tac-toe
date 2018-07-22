package coskun.ahmet.model.playground;

public class PlaygroundTile {

    private int position;
    private char currentCharOnTile;

    private PlaygroundTile rightTile;
    private PlaygroundTile leftTile;
    private PlaygroundTile upperTile;
    private PlaygroundTile lowerTile;
    private PlaygroundTile rightUpperTile;
    private PlaygroundTile rightLowerTile;
    private PlaygroundTile leftUpperTile;
    private PlaygroundTile leftLowerTile;


    public char getCurrentCharOnTile() {
        return currentCharOnTile;
    }

    public void setCurrentCharOnTile(char currentCharOnTile) {
        this.currentCharOnTile = currentCharOnTile;
    }

    public PlaygroundTile getRightTile() {
        return rightTile;
    }

    public void setRightTile(PlaygroundTile rightTile) {
        this.rightTile = rightTile;
    }

    public PlaygroundTile getLeftTile() {
        return leftTile;
    }

    public void setLeftTile(PlaygroundTile leftTile) {
        this.leftTile = leftTile;
    }

    public PlaygroundTile getUpperTile() {
        return upperTile;
    }

    public void setUpperTile(PlaygroundTile upperTile) {
        this.upperTile = upperTile;
    }

    public PlaygroundTile getLowerTile() {
        return lowerTile;
    }

    public void setLowerTile(PlaygroundTile lowerTile) {
        this.lowerTile = lowerTile;
    }

    public PlaygroundTile getRightUpperTile() {
        return rightUpperTile;
    }

    public void setRightUpperTile(PlaygroundTile rightUpperTile) {
        this.rightUpperTile = rightUpperTile;
    }

    public PlaygroundTile getRightLowerTile() {
        return rightLowerTile;
    }

    public void setRightLowerTile(PlaygroundTile rightLowerTile) {
        this.rightLowerTile = rightLowerTile;
    }

    public PlaygroundTile getLeftUpperTile() {
        return leftUpperTile;
    }

    public void setLeftUpperTile(PlaygroundTile leftUpperTile) {
        this.leftUpperTile = leftUpperTile;
    }

    public PlaygroundTile getLeftLowerTile() {
        return leftLowerTile;
    }

    public void setLeftLowerTile(PlaygroundTile leftLowerTile) {
        this.leftLowerTile = leftLowerTile;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPositionOnMatrix(int sizeOfPlayGround) {
        return getPosition() / sizeOfPlayGround + "*" + getPosition() % sizeOfPlayGround;
    }

}
