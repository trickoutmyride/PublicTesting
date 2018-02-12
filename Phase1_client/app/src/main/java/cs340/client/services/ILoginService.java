package cs340.client.services;

import cs340.shared.model.Player;

public interface ILoginService extends IService {
    void login(String username, String password);
    void onLogin(Player player);
    void register(String username, String password);
}
