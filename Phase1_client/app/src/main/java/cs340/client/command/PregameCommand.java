package client.commands;

import java.util.ArrayList;

import client.models.GameModel;
import client.services.PregameService;

public class PregameCommand implements ICommand {
    @Override
    public void execute() {
        GameModel game = new GameModel();
        game.setName("New Game");
        ArrayList<GameModel> games = new ArrayList<>();
        games.add(game);
        PregameService.getInstance().onGameListUpdated(games);
    }
}
