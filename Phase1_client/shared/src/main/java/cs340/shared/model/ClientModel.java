package client.models;

import java.util.ArrayList;

public class ClientModel implements IModel {
    private PlayerModel currentPlayer = null;
    private ArrayList<CurrentPlayerObserver> currentPlayerObservers = new ArrayList<>();
    private ArrayList<ErrorObserver> errorObservers = new ArrayList<>();
    private ArrayList<GameListObserver> gameListObservers = new ArrayList<>();
    private ArrayList<GameModel> games = new ArrayList<>();
    private static ClientModel instance = new ClientModel();

    private ClientModel() {}

    public void addErrorObserver(ErrorObserver observer) {
        errorObservers.add(observer);
    }

    public void addCurrentPlayerObserver(CurrentPlayerObserver observer) {
        currentPlayerObservers.add(observer);
        addErrorObserver(observer);
    }

    public void addGameListObserver(GameListObserver observer) {
        gameListObservers.add(observer);
        addErrorObserver(observer);
    }

    public PlayerModel getCurrentPlayer() {
        return currentPlayer;
    }

    public ArrayList<GameModel> getGameList() {
        return games;
    }

    public static ClientModel getInstance() {
        return instance;
    }

    public void removeErrorObserver(ErrorObserver observer) {
        errorObservers.remove(observer);
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

    public void setCurrentPlayer(PlayerModel player) {
        currentPlayer = player;
        for (CurrentPlayerObserver observer : currentPlayerObservers)
            observer.onCurrentPlayerSet(player);
    }

    public void setGameList(ArrayList<GameModel> games) {
        this.games = games;
        for (GameListObserver observer : gameListObservers) observer.onGameListUpdated(games);
    }

    public interface ErrorObserver {
        void onError(String message);
    }

    public interface CurrentPlayerObserver extends ErrorObserver {
        void onCurrentPlayerSet(PlayerModel player);
    }

    public interface GameListObserver extends ErrorObserver {
        void onGameListUpdated(ArrayList<GameModel> games);
    }
}
