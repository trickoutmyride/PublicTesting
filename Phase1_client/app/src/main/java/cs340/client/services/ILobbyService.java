package cs340.client.services;

import cs340.shared.model.Player;

public interface ILobbyService extends IService {
    void onGameStarted();
    void startGame(Player player);
}