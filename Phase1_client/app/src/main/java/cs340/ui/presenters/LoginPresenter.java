package cs340.ui.presenters;

import android.app.Activity;

import cs340.client.services.LoginService;
import cs340.client.services.RegisterService;
import cs340.shared.model.ClientModel;
import cs340.shared.model.Player;
import cs340.ui.activities.interfaces.ILoginActivity;
import cs340.ui.presenters.interfaces.ILoginPresenter;

public class LoginPresenter implements ILoginPresenter, ClientModel.CurrentPlayerObserver, ClientModel.ErrorObserver {
    private ILoginActivity activity;

    public LoginPresenter(ILoginActivity activity) {
        this.activity = activity;
        ClientModel.getInstance().addCurrentPlayerObserver(this);
    }

    @Override
    public void detach() {
        ClientModel.getInstance().removeCurrentPlayerObserver(this);
    }

    @Override
    public void login(String username, String password, String address) {
        LoginService.login(username, password, address);
    }

    @Override
    public void onCurrentPlayerSet(Player player) {
        final Player player2 = player;
        ((Activity)activity).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.onLogin(player2);
            }
        });
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
    public void register(String username, String password, String address) {
        RegisterService.register(username, password, address);
    }

}
