package client.services;

import client.commands.LoginCommand;
import client.commands.ICommand;
import client.models.ClientModel;
import client.models.PlayerModel;

public class LoginService implements ILoginService {
    private static LoginService instance = new LoginService();
    private IService.Proxy proxy = null;

    private LoginService() {}

    public static LoginService getInstance() {
        return instance;
    }

    @Override
    public void login(String username, String password) {
        if (proxy != null) proxy.sendCommand(new LoginCommand());
    }

    @Override
    public void onLogin(PlayerModel player) {
        ClientModel.getInstance().setCurrentPlayer(player);
    }

    @Override
    public void register(String username, String password) {
        if (proxy != null) proxy.sendCommand(new LoginCommand());
    }

    @Override
    public void setProxy(IService.Proxy proxy) {
        this.proxy = proxy;
    }
}
