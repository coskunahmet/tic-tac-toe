package coskun.ahmet.controller;

import coskun.ahmet.enums.GameNotification;
import coskun.ahmet.model.gameboard.GameBoardTile;
import coskun.ahmet.model.player.ComputerPlayer;
import coskun.ahmet.model.player.HumanPlayer;
import coskun.ahmet.model.player.Player;
import coskun.ahmet.observer.Observer;
import coskun.ahmet.observer.ObserverManager;
import coskun.ahmet.utils.PropertiesManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController extends Observer implements IGameController {

    private List<Player> playerList = new ArrayList<Player>();

    private Player currentPlayer;

    private boolean isGamePlay = false;

    private int turnNumber;

    private final int numberOfPlayer = 3;

    public GameController() {


    }

    public void init() {
        //TODO initialize game


        initPlayersTurn();

        turnNumber = -1;

        isGamePlay = true;

        ObserverManager.getInstance().attachObserver(this, PropertiesManager.getInstance().getTopicProperty(PropertiesManager.GAME_NOTIFICATIONS_TOPIC_NAME_KEY));

        start();
    }

    public void start() {

        updatePlayerTurn();

        while (isGamePlay) {
            beforePlay();
            update();
        }
    }

    public void update() {

        GameBoardTile newGameBoardTile = null;

        currentPlayer.getInput();

        newGameBoardTile = new GameBoardTile();
        newGameBoardTile.setPosition(currentPlayer.getxPositionToPlay(), currentPlayer.getyPositionToPlay(), PropertiesManager.getInstance().getGameBoardSize());
        newGameBoardTile.setCurrentCharOnTile(playerList.get(turnNumber % numberOfPlayer).getSymbol());

        ObserverManager.getInstance().setGameBoardTile(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.GAME_MOVE_MODEL_TOPIC_NAME_KEY), newGameBoardTile);

    }

    public void update(GameNotification gameNotification) {

        if (gameNotification.equals(GameNotification.NEXT_PLAYER)) {
            updatePlayerTurn();
        }
    }

    private void updatePlayerTurn() {
        turnNumber++;
        currentPlayer = playerList.get(turnNumber % numberOfPlayer);
    }

    private void initPlayersTurn() {
        Random generator = new Random();
        int computerTurnNumber = generator.nextInt(numberOfPlayer);

        char firstPlayerSymbol = PropertiesManager.getInstance().getGameProperty(PropertiesManager.FIRST_PLAYER_CHAR_KEY).charAt(0);
        char secondPlayerSymbol = PropertiesManager.getInstance().getGameProperty(PropertiesManager.SECOND_PLAYER_CHAR_KEY).charAt(0);
        char computerPlayerSymbol = PropertiesManager.getInstance().getGameProperty(PropertiesManager.COMPUTER_PLAYER_CHAR_KEY).charAt(0);


        switch (computerTurnNumber) {
            case 0:
                playerList.add(new ComputerPlayer("Computer", computerPlayerSymbol));
                playerList.add(new HumanPlayer("Ahmet", firstPlayerSymbol));
                playerList.add(new HumanPlayer("Murat", secondPlayerSymbol));
                break;
            case 1:
                playerList.add(new HumanPlayer("Ahmet", firstPlayerSymbol));
                playerList.add(new ComputerPlayer("Computer", computerPlayerSymbol));
                playerList.add(new HumanPlayer("Murat", secondPlayerSymbol));
                break;
            case 2:
                playerList.add(new HumanPlayer("Ahmet", firstPlayerSymbol));
                playerList.add(new HumanPlayer("Murat", secondPlayerSymbol));
                playerList.add(new ComputerPlayer("Computer", computerPlayerSymbol));
                break;

        }
    }

    private void beforePlay() {
        System.out.println(currentPlayer.getName() + "'s Turn: ");
    }

    private void afterPlay() {
        System.out.println(currentPlayer.getName() + " played.");
    }
}
