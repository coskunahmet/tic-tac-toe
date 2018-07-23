package coskun.ahmet.model.player;

public abstract class Player {

    private String name;

    private int xPositionToPlay;
    private int yPositionToPlay;


    private char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public abstract void play();

    public int getxPositionToPlay() {
        return xPositionToPlay;
    }

    public void setxPositionToPlay(int xPositionToPlay) {
        this.xPositionToPlay = xPositionToPlay;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getyPositionToPlay() {
        return yPositionToPlay;
    }

    public void setyPositionToPlay(int yPositionToPlay) {
        this.yPositionToPlay = yPositionToPlay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
