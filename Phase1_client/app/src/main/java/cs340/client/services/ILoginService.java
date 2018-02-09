package client.services;

import client.models.PlayerModel;

public interface ILoginService extends IService {
    void login(String username, String password);
    void onLogin(PlayerModel player);
    void register(String username, String password);
}
