package client.commands;

import client.models.PlayerModel;
import client.services.LoginService;

public class LoginCommand implements ICommand {
    @Override
    public void execute() {
        PlayerModel player = new PlayerModel();
        player.setName("Brett");
        LoginService.getInstance().onLogin(player);
    }
}
