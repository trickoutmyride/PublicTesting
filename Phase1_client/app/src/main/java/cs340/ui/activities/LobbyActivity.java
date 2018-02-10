package cs340.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;

import cs340.shared.model.Game;
import cs340.shared.model.Player;
import cs340.ui.R;

public class LobbyActivity extends AppCompatActivity {

    private Player currentPlayer;
    private Game currentGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        Gson gson = new Gson();
        currentPlayer = gson.fromJson(getIntent().getStringExtra("currentPlayer"), Player.class);
        currentGame = gson.fromJson(getIntent().getStringExtra("currentGame"), Game.class);
        System.out.println("Current Player: " + currentPlayer.getUsername());
        System.out.println("Current Game: " + currentGame.getGameName());
    }
}
