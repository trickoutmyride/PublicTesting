package cs340.shared.model;

import java.util.ArrayList;

public class ClientModel {
    private Game currentGame = null;
    private Player currentPlayer = null;
    private ArrayList<CurrentPlayerObserver> currentPlayerObservers = new ArrayList<>();
    private ArrayList<ErrorObserver> errorObservers = new ArrayList<>();
    private ArrayList<GameListObserver> gameListObservers = new ArrayList<>();
    private ArrayList<GameLobbyObserver> gameLobbyObservers = new ArrayList<>();
    private ArrayList<Game> games = new ArrayList<>();
    private static ClientModel instance = new ClientModel();

    //Phase 2
    private ArrayList<HandObserver> handObservers = new ArrayList<>();

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

    public void addGameLobbyObserver(GameLobbyObserver observer) {
        gameLobbyObservers.add(observer);
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

    public void removeCurrentPlayerObserver(CurrentPlayerObserver observer) {
        currentPlayerObservers.remove(observer);
        removeErrorObserver(observer);
    }

    public void removeGameListObserver(GameListObserver observer) {
        gameListObservers.remove(observer);
        removeErrorObserver(observer);
    }

    public void removeGameLobbyObserver(GameLobbyObserver observer) {
        gameLobbyObservers.remove(observer);
        removeErrorObserver(observer);
    }

    public void sendError(String message) {
        for (ErrorObserver observer: errorObservers) observer.onError(message);
    }

    public void setCurrentGame(Game game) {
        currentGame = game;
        for (GameListObserver observer : gameListObservers) observer.onCurrentGameSet(game);
        for (GameLobbyObserver observer : gameLobbyObservers) observer.onGameUpdated(game);
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

    public void startGame() {
        for (GameLobbyObserver observer : gameLobbyObservers) observer.onGameStarted();
    }

    public interface ErrorObserver {
        void onError(String message);
    }

    public interface CurrentPlayerObserver extends ErrorObserver {
        void onCurrentPlayerSet(Player player);
    }

    public interface GameListObserver extends ErrorObserver {
        void onCurrentGameSet(Game game);
        void onGameListUpdated(ArrayList<Game> games);
    }

    public interface GameLobbyObserver extends ErrorObserver {
        void onGameStarted();
        void onGameUpdated(Game game);
    }


    //Phase 2

    //Hand Classes
    public interface HandObserver extends ErrorObserver {
        void onHandUpdated();
    }

    public void addHandObserver(HandObserver observer) {
        handObservers.add(observer);
        addErrorObserver(observer);
    }

    public void removeHandObserver(HandObserver observer){
        handObservers.remove(observer);
        removeErrorObserver(observer);
    }

    public void updateHand() {
        for (HandObserver observer : handObservers) { observer.onHandUpdated(); }
    }

    public void updateCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}