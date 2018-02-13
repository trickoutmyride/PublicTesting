package cs340.ui.presenters;

import java.util.ArrayList;

import cs340.client.services.CreateGameService;
import cs340.client.services.JoinGameService;
import cs340.client.services.PregameService;
import cs340.shared.model.ClientModel;
import cs340.shared.model.Game;
import cs340.shared.model.GameList;
import cs340.shared.model.Player;
import cs340.ui.activities.IPreGameActivity;

public class PregamePresenter implements IPregamePresenter {
    private IPreGameActivity activity;

    public PregamePresenter(IPreGameActivity activity) {
        this.activity = activity;
        ClientModel.getInstance().addGameListObserver(this);
        //Initialize the game list for the activity
        activity.onGameListUpdated(ClientModel.getInstance().getGameList());
    }

    @Override
    public void createGame(String name, Player player, int capacity, String color) {
        CreateGameService.createGame(name, player, capacity, color);
    }

    @Override
    public void detach() {
        ClientModel.getInstance().removeGameListObserver(this);
    }

    @Override
    public void joinGame(int gameID, Player player, String color) {
        JoinGameService.joinGame(gameID, player, color);
    }

    @Override
    public void onError(String message) {
        activity.onError(message);
    }

    @Override
    public void onGameListUpdated(ArrayList<Game> games) {
        activity.onGameListUpdated(games);
    }
}
