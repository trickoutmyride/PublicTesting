package cs340.ui.presenters;

import java.util.ArrayList;

import cs340.client.services.LobbyService;
import cs340.shared.model.ClientModel;
import cs340.shared.model.Game;
import cs340.shared.model.Player;
import cs340.ui.activities.ILobbyActivity;

public class LobbyPresenter implements ILobbyPresenter {
    private ILobbyActivity activity;
    private Game game;

    public LobbyPresenter(ILobbyActivity activity, Game game) {
        this.activity = activity;
        this.game = game;
        game.addLobbyObserver(this);
    }

    @Override
    public void detach() {
        game.removeLobbyObserver(this);
    }

    @Override
    public void onError(String message) {
        activity.onError(message);
    }

    @Override
    public void onGameStarted() {

    }

    @Override
    public void onRosterUpdated(ArrayList<Player> players) {
        activity.onRosterUpdated(players);
    }

    @Override
    public void startGame() {
        LobbyService.getInstance().startGame(ClientModel.getInstance().getCurrentPlayer());
    }
}
