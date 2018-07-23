package coskun.ahmet.controller;

import coskun.ahmet.Observer;
import coskun.ahmet.model.gameboard.GameBoardTile;
import coskun.ahmet.model.player.ComputerPlayer;
import coskun.ahmet.model.player.HumanPlayer;
import coskun.ahmet.model.player.Player;
import coskun.ahmet.utils.PropertiesManager;

import java.util.*;

public class GameController implements IGameController {

    private Map<String, List<Observer>> observers = new HashMap<String, List<Observer>>();
    private IInputController inputController = new InputController();
    private List<Player> playerList = new ArrayList<Player>();

    private List<GameBoardTile> filledGameBoardTileList = new ArrayList<GameBoardTile>();

    private Player currentPlayer;

    private boolean isGamePlay = false;

    private int turnNumber;

    private static final String COORDINATE_DELIMITER = ",";
    private final int numberOfPlayer = 3;

    public GameController() {
        observers.put(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.MODEL_TOPIC_NAME_KEY), new ArrayList<Observer>());
        observers.put(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.VIEW_TOPIC_NAME_KEY), new ArrayList<Observer>());

    }


    private void setGameBoardTile(String topic, GameBoardTile gameBoardTile) {
        notifyAllObservers(topic, gameBoardTile);
    }

    public void attach(Observer observer, String topic) {
        observers.get(topic).add(observer);
    }

    public void notifyAllObservers(String topic, GameBoardTile gameBoardTile) {
        for (Observer observer : observers.get(topic)) {
            observer.update(gameBoardTile.getPosition(), gameBoardTile.getCurrentCharOnTile());
        }
    }

    public void init() {
        //TODO initialize game


        initPlayersTurn();

        turnNumber = -1;

        isGamePlay = true;
        start();
    }

    public void start() {

        updatePlayerTurn();

        while (isGamePlay) {
            beforePlay();
            update();
            afterPlay();

            updatePlayerTurn();
        }
    }

    public void update() {

        GameBoardTile newGameBoardTile = null;

        while(!isMoveValid(newGameBoardTile)) {
            if (currentPlayer instanceof HumanPlayer) {
                String input = inputController.getInput();
                String[] coordinates = input.split(GameController.COORDINATE_DELIMITER);
                int coordinateX = Integer.parseInt(coordinates[0]);
                int coordinateY = Integer.parseInt(coordinates[1]);

                currentPlayer.setxPositionToPlay(coordinateX);
                currentPlayer.setyPositionToPlay(coordinateY);
            }

            currentPlayer.play();

            newGameBoardTile = new GameBoardTile();
            newGameBoardTile.setPosition(currentPlayer.getxPositionToPlay(), currentPlayer.getyPositionToPlay(), PropertiesManager.getInstance().getGameBoardSize());
            newGameBoardTile.setCurrentCharOnTile(playerList.get(turnNumber % numberOfPlayer).getSymbol());

        }

        this.setGameBoardTile(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.MODEL_TOPIC_NAME_KEY), newGameBoardTile);
        this.setGameBoardTile(PropertiesManager.getInstance().getTopicProperty(PropertiesManager.VIEW_TOPIC_NAME_KEY), newGameBoardTile);

        filledGameBoardTileList.add(newGameBoardTile);
    }

    private void beforePlay() {
        System.out.println(currentPlayer.getName() + "'s Turn: ");
    }

    private void afterPlay() {
        System.out.println(currentPlayer.getName() + " played.");
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

            case 1:
                playerList.add(new HumanPlayer("Ahmet", firstPlayerSymbol));
                playerList.add(new ComputerPlayer("Computer", computerPlayerSymbol));
                playerList.add(new HumanPlayer("Murat", secondPlayerSymbol));

            case 2:
                playerList.add(new HumanPlayer("Ahmet", firstPlayerSymbol));
                playerList.add(new HumanPlayer("Murat", secondPlayerSymbol));
                playerList.add(new ComputerPlayer("Computer", computerPlayerSymbol));


        }
    }

    private boolean isMoveValid(GameBoardTile newPlayedGameBoardTile) {
        if(newPlayedGameBoardTile == null)
            return false;

        for(GameBoardTile filledGameBoardTile : filledGameBoardTileList) {
            if(newPlayedGameBoardTile.getPosition() == filledGameBoardTile.getPosition()) {
                if(currentPlayer instanceof HumanPlayer)
                    System.out.println("Selected Tile is Full. Please choose another tile. ");
                return false;
            }
        }

        return true;
    }

}
