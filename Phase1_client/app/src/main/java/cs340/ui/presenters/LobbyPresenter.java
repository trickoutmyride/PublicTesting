package cs340.ui.presenters;

import android.app.Activity;

import java.util.ArrayList;

import cs340.client.services.StartGameService;
import cs340.shared.model.ClientModel;
import cs340.shared.model.Game;
import cs340.shared.model.Player;
import cs340.ui.activities.ILobbyActivity;

public class LobbyPresenter implements ILobbyPresenter {
    private ILobbyActivity activity;

    public LobbyPresenter(ILobbyActivity activity) {
        this.activity = activity;
        ClientModel.getInstance().addGameLobbyObserver(this);
    }

    @Override
    public void detach() {
        ClientModel.getInstance().removeGameLobbyObserver(this);
    }

    @Override
    public void onError(final String message) {
        ((Activity)activity).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.onError(message);
            }
        });
    }

    @Override
    public void onGameStarted() {
        ((Activity)activity).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.onGameStarted();
            }
        });
    }

    @Override
    public void onGameUpdated(final Game game) {
        ((Activity)activity).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.onGameUpdated(game);
            }
        });
    }

    @Override
    public void startGame() {
        StartGameService.startGame(ClientModel.getInstance().getCurrentPlayer());
    }
}
