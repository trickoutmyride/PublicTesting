package cs340.client.services;

import cs340.shared.interfaces.IServer;
import cs340.shared.model.ClientModel;
import cs340.shared.model.Player;
import cs340.shared.requests.SignInRequest;

public class LoginService implements ILoginService {
    private static LoginService instance = new LoginService();
    private IServer proxy = null;

    private LoginService() {}

    public static LoginService getInstance() {
        return instance;
    }

    @Override
    public void login(String username, String password) {
        if (proxy != null) proxy.login(new SignInRequest(username, password));
    }

    @Override
    public void onLogin(Player player) {
        ClientModel.getInstance().setCurrentPlayer(player);
    }

    @Override
    public void register(String username, String password) {
        if (proxy != null) proxy.register(new SignInRequest(username, password));
    }

    @Override
    public void setProxy(IServer proxy) {
        this.proxy = proxy;
    }
}