package cs340.ui.mock;

import java.util.ArrayList;
import cs340.shared.model.Game;
import cs340.shared.model.Player;
import cs340.ui.activities.interfaces.ILobbyActivity;
import cs340.ui.activities.interfaces.ILoginActivity;
import cs340.ui.activities.interfaces.IPreGameActivity;
import cs340.ui.presenters.interfaces.ILobbyPresenter;
import cs340.ui.presenters.interfaces.ILoginPresenter;
import cs340.ui.presenters.interfaces.IPregamePresenter;


//Fake game data to speed past phase 1 functionality with no server
public class MockPhase1Presenter implements IPregamePresenter, ILoginPresenter, ILobbyPresenter {

    //Data Members
    private ArrayList<Game> _gameList;
    private ILoginActivity loginActivity;
    private IPreGameActivity preGameActivity;
    private ILobbyActivity lobbyActivity;





    /*
         Mock functions for LoginActivity class
    */

    public MockPhase1Presenter(ILoginActivity a){
        loginActivity = a;
    }

    @Override
    public void login(String username, String password) {
        loginActivity.onLogin(new Player(username, password, username.concat(password)));
    }

    @Override
    public void register(String username, String password) {
        loginActivity.onLogin(new Player(username, password, username.concat(password)));
    }






    /*
        Mock functions for PreGameActivity class
    */

    //When created by a PreGameActivity, dump mock game data into the activity
    public MockPhase1Presenter(IPreGameActivity a){
        preGameActivity = a;

        //Create fake Game data

        //Create fake Players
        Player player1 = new Player("player1", "player1", "player1");
        Player player2 = new Player("player2", "player2", "player2");
        Player player3 = new Player("player3", "player3", "player3");
        Player player4 = new Player("player4", "player4", "player4");

        //Create 4/5 capacity game
        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);
        Game game1 = new Game("game1", playerList, 5);
        game1.setColor("player1", "blue");
        game1.setColor("player2", "red");
        game1.setColor("player3", "green");
        game1.setColor("player4", "yellow");

        //Create 2/3 capacity game
        ArrayList<Player> playerList2 = new ArrayList<>();
        playerList2.add(player1);
        playerList2.add(player2);
        Game game2 = new Game("game2", playerList2, 3);
        game2.setColor("player1", "red");
        game2.setColor("player2", "yellow");

        //Add games to a list and update Activity
        ArrayList<Game> gameList = new ArrayList<>();
        gameList.add(game1);
        gameList.add(game2);
        preGameActivity.onGameListUpdated(gameList);

        //Update list of games
        _gameList = gameList;
    }

    //Create game and return it to activity
    @Override
    public void createGame(String name, Player player, int capacity, String color) {
        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(player);
        Game game = new Game(name, playerList, capacity);
        game.setColor(player.getUsername(), color);
        preGameActivity.onGameJoined(game);
    }

    @Override
    public void joinGame(int gameID, Player player, String color) {
        //Get game from gameID, join game, and return to activity
        for (Game g : _gameList){
            if (g.getGameID() == gameID){
                g.setColor(player.getUsername(), color);
                preGameActivity.onGameJoined(g);
                return;
            }
        }
    }








    /*
        Mock functions for PreGameActivity class
    */

    public MockPhase1Presenter(ILobbyActivity a){
        lobbyActivity = a;
    }

    //Start the game
    @Override
    public void startGame() {

    }









    //Unused for mock functionality

    @Override
    public void onGameListUpdated(ArrayList<Game> games) {}

    @Override
    public void detach() {}

    @Override
    public void onCurrentGameSet(Game game) {}

    @Override
    public void onError(String message) {}

    @Override
    public void onGameStarted(Game game) {

    }

    @Override
    public void onGameUpdated(Game game) {}
}
