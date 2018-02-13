package cs340.ui.mock;

import java.util.Random;

import cs340.shared.model.Player;
import cs340.ui.activities.LoginActivity;
import cs340.ui.presenters.ILoginPresenter;

/**
 * Created by sam on 2/13/18.
 */

public class MockLoginPresenter implements ILoginPresenter {

    private LoginActivity _loginActivity;

    public MockLoginPresenter(LoginActivity loginActivity) {
        _loginActivity = loginActivity;
    }

    @Override
    public void detach() {

    }

    @Override
    public void login(String username, String password) {
        Random r = new Random();
        int choice = r.nextInt(2) + 1;

        if (choice == 1) {
            _loginActivity.onLogin(new Player("sbeck14", "a", "a"));
        }
        else {
            _loginActivity.onError("loginError");
        }

    }

    @Override
    public void register(String username, String password) {
        Random r = new Random();
        int choice = r.nextInt(2) + 1;

        if (choice == 1) {
            _loginActivity.onLogin(new Player("sbeck14", "a", "a"));
        }
        else {
            _loginActivity.onError("loginError");
        }
    }
}
