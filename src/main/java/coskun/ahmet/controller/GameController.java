package coskun.ahmet.controller;

import coskun.ahmet.enums.GameNotificationEnum;
import coskun.ahmet.exception.InvalidInputException;
import coskun.ahmet.model.GameNotification;
import coskun.ahmet.model.GameViewNotification;
import coskun.ahmet.model.gameboard.GameBoardTile;
import coskun.ahmet.model.player.ComputerPlayer;
import coskun.ahmet.model.player.HumanPlayer;
import coskun.ahmet.model.player.Player;
import coskun.ahmet.observer.Observer;
import coskun.ahmet.observer.ObserverManager;
import coskun.ahmet.utils.PropertiesManager;

import java.util.*;

public class GameController extends Observer implements IGameController {

    private List<Player> playerList = new ArrayList<Player>();

    private final String FIRST_HUMAN_PLAYER_NAME = "First Player";
    private final String SECOND_HUMAN_PLAYER_NAME = "Second Player";
    private final String COMPUTER_PLAYER_NAME = "Computer";

    private Player currentPlayer;
    private Player previousPlayer;
    private boolean isPlayerPlayed;

    private boolean isGameFinished = false;

    private int turnNumber;
    private final int numberOfPlayer = 3;

    public void init() {

        initPlayersTurn();
        turnNumber = -1;
        isGameFinished = false;
        isPlayerPlayed = false;

        ObserverManager.getInstance().attachObserver(this, PropertiesManager.getInstance().getTopicProperty(PropertiesManager.GAME_NOTIFICATIONS_TOPIC_NAME_KEY));
    }

    public void start() {

        updatePlayerTurn();

        while (!isGameFinished) {
            beforePlay();
            update();
            afterPlay();
        }
    }

    public void update() {

        GameBoardTile newGameBoardTile = null;

        try {
            currentPlayer.getInput();

            newGameBoardTile = new GameBoardTile();
            newGameBoardTile.setPosition(currentPlayer.getxPositionToPlay(), currentPlayer.getyPositionToPlay(), PropertiesManager.getInstance().getGameBoardSize());
            newGameBoardTile.setCurrentCharOnTile(playerList.get(turnNumber % numberOfPlayer).getSymbol());

            ObserverManager.getInstance().setGameBoardTile(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.GAME_MOVE_MODEL_TOPIC_NAME_KEY), newGameBoardTile);
        } catch (InvalidInputException | NoSuchElementException e) {
            ObserverManager.getInstance().setGameNotification(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.GAME_NOTIFICATIONS_TOPIC_NAME_KEY), new GameViewNotification(GameNotificationEnum.INVALID_INPUT, new ArrayList<>()));
        }

    }

    public void update(GameNotification gameNotification) {

        if (!(gameNotification instanceof GameViewNotification)) {
            if (gameNotification.getGameNotificationEnum().equals(GameNotificationEnum.NEXT_TURN)) {
                updatePlayerTurn();
            } else if (gameNotification.getGameNotificationEnum().equals(GameNotificationEnum.GAME_END)) {

                isGameFinished = true;
                GameViewNotification gameViewNotification = new GameViewNotification(GameNotificationEnum.GAME_END
                        , Arrays.asList(currentPlayer.getName(), Integer.toString(currentPlayer.getxPositionToPlay()), Integer.toString(currentPlayer.getyPositionToPlay())));
                ObserverManager.getInstance().setGameNotification(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.GAME_NOTIFICATIONS_TOPIC_NAME_KEY), gameViewNotification);
            } else if (gameNotification.getGameNotificationEnum().equals(GameNotificationEnum.GAME_END_WITHOUT_WINNER)) {
                isGameFinished = true;

                GameViewNotification gameViewNotification = new GameViewNotification(GameNotificationEnum.GAME_END_WITHOUT_WINNER
                        , Arrays.asList(currentPlayer.getName(), Integer.toString(currentPlayer.getxPositionToPlay()), Integer.toString(currentPlayer.getyPositionToPlay())));
                ObserverManager.getInstance().setGameNotification(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.GAME_NOTIFICATIONS_TOPIC_NAME_KEY), gameViewNotification);
            }
        }
    }

    private void updatePlayerTurn() {
        turnNumber++;
        previousPlayer = currentPlayer;
        currentPlayer = playerList.get(turnNumber % numberOfPlayer);
        isPlayerPlayed = true;
    }

    private void initPlayersTurn() {
        Random generator = new Random();
        int computerTurnNumber = generator.nextInt(numberOfPlayer);

        char firstPlayerSymbol = PropertiesManager.getInstance().getGameProperty(PropertiesManager.FIRST_PLAYER_CHAR_KEY).charAt(0);
        char secondPlayerSymbol = PropertiesManager.getInstance().getGameProperty(PropertiesManager.SECOND_PLAYER_CHAR_KEY).charAt(0);
        char computerPlayerSymbol = PropertiesManager.getInstance().getGameProperty(PropertiesManager.COMPUTER_PLAYER_CHAR_KEY).charAt(0);

        switch (computerTurnNumber) {
            case 0:
                playerList.add(new ComputerPlayer(COMPUTER_PLAYER_NAME, computerPlayerSymbol));
                playerList.add(new HumanPlayer(FIRST_HUMAN_PLAYER_NAME, firstPlayerSymbol));
                playerList.add(new HumanPlayer(SECOND_HUMAN_PLAYER_NAME, secondPlayerSymbol));
                break;
            case 1:
                playerList.add(new HumanPlayer(FIRST_HUMAN_PLAYER_NAME, firstPlayerSymbol));
                playerList.add(new ComputerPlayer(COMPUTER_PLAYER_NAME, computerPlayerSymbol));
                playerList.add(new HumanPlayer(SECOND_HUMAN_PLAYER_NAME, secondPlayerSymbol));
                break;
            case 2:
                playerList.add(new HumanPlayer(FIRST_HUMAN_PLAYER_NAME, firstPlayerSymbol));
                playerList.add(new HumanPlayer(SECOND_HUMAN_PLAYER_NAME, secondPlayerSymbol));
                playerList.add(new ComputerPlayer(COMPUTER_PLAYER_NAME, computerPlayerSymbol));
                break;

        }
    }

    private void afterPlay() {
        if (isPlayerPlayed)
            ObserverManager.getInstance().setGameNotification(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.GAME_NOTIFICATIONS_TOPIC_NAME_KEY)
                    , new GameViewNotification(GameNotificationEnum.PLAYER_PLAYED
                            , Arrays.asList(previousPlayer.getName(), Integer.toString(previousPlayer.getxPositionToPlay()), Integer.toString(previousPlayer.getyPositionToPlay()))));
    }

    private void beforePlay() {
        isPlayerPlayed = false;
        ObserverManager.getInstance().setGameNotification(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.GAME_NOTIFICATIONS_TOPIC_NAME_KEY)
                , new GameViewNotification(GameNotificationEnum.TURN_OF_PLAYER
                        , Arrays.asList(currentPlayer.getName())));
    }
}
