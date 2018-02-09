package cs340.ui;

import cs340.shared.model.Player;

/**
 * Created by sam on 2/5/18
 */

public interface ILoginActivity {

    public void onLoginSuccess(Player currentPlayer);

    public void onError(String msg);

    public void onRegistrationSuccess(Player currentPlayer);
}
