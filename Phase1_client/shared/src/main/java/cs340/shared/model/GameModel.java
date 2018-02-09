package client.models;

import java.util.ArrayList;

public class GameModel implements IModel {
    private String name = "";
    private ArrayList<PlayerModel> players = new ArrayList<>();

    public String getName() {
        return name;
    }

    public ArrayList<PlayerModel> getPlayers() {
        return players;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayers(ArrayList<PlayerModel> players) {
        this.players = players;
    }
}
