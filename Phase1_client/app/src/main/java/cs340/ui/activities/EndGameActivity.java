package cs340.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;

import cs340.shared.model.Game;
import cs340.shared.model.Player;
import cs340.ui.R;
import cs340.ui.activities.interfaces.ILobbyActivity;
import cs340.ui.fragments.adapters.EndGameListAdapter;
import cs340.ui.fragments.adapters.PlayerListAdapter;
import cs340.ui.presenters.LobbyPresenter;
import cs340.ui.presenters.interfaces.ILobbyPresenter;

public class EndGameActivity extends AppCompatActivity {


    private Player currentPlayer;
    private Game currentGame;
    private RecyclerView endGameList;
    private LinearLayoutManager endGameLayoutManager;
    private EndGameListAdapter endGameListAdapter;


    /**
     * On Activity creation, do the following:
     *      Get current player and game from the PreGameActivity
     *      Initialize LobbyPresenter
     *      Setup buttons, playerList RecyclerView, etc.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        Gson gson = new Gson();
        currentPlayer = gson.fromJson(getIntent().getStringExtra("currentPlayer"), Player.class);
        currentGame = gson.fromJson(getIntent().getStringExtra("currentGame"), Game.class);

        //Display results
        endGameList = findViewById(R.id.end_game_score_list);
        endGameLayoutManager = new LinearLayoutManager(this);
        endGameList.setLayoutManager(endGameLayoutManager);
        endGameListAdapter = new EndGameListAdapter(this, currentGame.getPlayers(), currentGame);
        endGameList.setAdapter(endGameListAdapter);

    }


}
