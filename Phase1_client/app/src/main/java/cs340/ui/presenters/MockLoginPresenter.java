package cs340.ui.presenters;

import cs340.shared.model.Player;
import cs340.ui.activities.ILoginActivity;
import cs340.ui.activities.LoginActivity;

/**
 * Created by sam on 2/10/18.
 */

public class MockLoginPresenter implements ILoginPresenter {

    ILoginActivity loginActivity;

    public MockLoginPresenter(ILoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    @Override
    public void detach() {
        //stub
    }

    @Override
    public void login(String username, String password) {
        //Successful Login Test
        loginActivity.onLogin(new Player("sbeck14", "pass", "auth"));

        //Error Test
        //loginActivity.onError("Login Failed. Invalid credentials.");
    }

    @Override
    public void register(String username, String password) {
        //Successful Register Test
        loginActivity.onLogin(new Player("sbeck14", "pass", "auth"));

        //Error Test
        //loginActivity.onError("Login Failed. Invalid credentials.");

    }
}
