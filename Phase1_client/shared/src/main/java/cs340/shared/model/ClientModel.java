package cs340.shared.model;

import java.util.ArrayList;

public class ClientModel {
    private Game currentGame = null;
    private Player currentPlayer = null;
    private ArrayList<CurrentGameObserver> currentGameObservers = new ArrayList<>();
    private ArrayList<CurrentPlayerObserver> currentPlayerObservers = new ArrayList<>();
    private ArrayList<ErrorObserver> errorObservers = new ArrayList<>();
    private ArrayList<GameListObserver> gameListObservers = new ArrayList<>();
    private ArrayList<Game> games = new ArrayList<>();
    private static ClientModel instance = new ClientModel();

    private ClientModel() {}

    public void addErrorObserver(ErrorObserver observer) {
        errorObservers.add(observer);
    }

    public void addCurrentGameObserver(CurrentGameObserver observer) {
        currentGameObservers.add(observer);
        addErrorObserver(observer);
    }

    public void addCurrentPlayerObserver(CurrentPlayerObserver observer) {
        currentPlayerObservers.add(observer);
        addErrorObserver(observer);
    }

    public void addGameListObserver(GameListObserver observer) {
        gameListObservers.add(observer);
        addErrorObserver(observer);
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public ArrayList<Game> getGameList() {
        return games;
    }

    public static ClientModel getInstance() {
        return instance;
    }

    public void removeErrorObserver(ErrorObserver observer) {
        errorObservers.remove(observer);
    }

    public void removeCurrentGameObserver(CurrentGameObserver observer) {
        currentGameObservers.remove(observer);
        removeErrorObserver(observer);
    }

    public void removeCurrentPlayerObserver(CurrentPlayerObserver observer) {
        currentPlayerObservers.remove(observer);
        removeErrorObserver(observer);
    }

    public void removeGameListObserver(GameListObserver observer) {
        gameListObservers.remove(observer);
        removeErrorObserver(observer);
    }

    public void sendError(String message) {
        for (ErrorObserver observer: errorObservers) observer.onError(message);
    }

    public void setCurrentGame(Game game) {
        currentGame = game;
        for (CurrentGameObserver observer : currentGameObservers)
            observer.onCurrentGameSet(game);
    }

    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
        for (CurrentPlayerObserver observer : currentPlayerObservers)
            observer.onCurrentPlayerSet(player);
    }

    public void setGameList(ArrayList<Game> games) {
        this.games = games;
        for (GameListObserver observer : gameListObservers) observer.onGameListUpdated(games);
    }

    public interface ErrorObserver {
        void onError(String message);
    }

    public interface CurrentGameObserver extends ErrorObserver {
        void onCurrentGameSet(Game game);
    }

    public interface CurrentPlayerObserver extends ErrorObserver {
        void onCurrentPlayerSet(Player player);
    }

    public interface GameListObserver extends ErrorObserver {
        void onGameListUpdated(ArrayList<Game> games);
    }
}
