package cs340.ui.presenters;

import cs340.ui.activities.ILoginActivity;
import client.models.ClientModel;
import client.models.PlayerModel;
import client.services.LoginService;

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
    public void login(String username, String password) {
        LoginService.getInstance().login(username, password);
    }

    @Override
    public void onCurrentPlayerSet(PlayerModel player) {
        activity.onLogin(player);
    }

    @Override
    public void onError(String message) {
        activity.onError(message);
    }

//    @Override
//    public void onLoginSuccess(PlayerModel player) {
//        activity.onLoginSuccess(player);
//    }
//
//    @Override
//    public void onRegistrationSuccess(PlayerModel player) {
//        activity.onRegistrationSuccess(player);
//    }

    @Override
    public void register(String username, String password) {
        LoginService.getInstance().register(username, password);
    }
}
