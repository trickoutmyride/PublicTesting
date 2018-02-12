package cs340.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import cs340.shared.model.Game;
import cs340.shared.model.Player;
import cs340.ui.R;
import cs340.ui.presenters.ILobbyPresenter;
import cs340.ui.presenters.LobbyPresenter;

public class LobbyActivity extends AppCompatActivity implements ILobbyActivity {


    //TODO: Display the current player somewhere
    //maybe "(you)" after the current player on the list?

    private Player currentPlayer;
    private Game currentGame;
    private Button startButton;
    private TextView currentGameName;
    private RecyclerView playerList;
    private RecyclerView.LayoutManager playerListLayoutManager;
    private RecyclerView.Adapter playerListAdapter;
    private ILobbyPresenter lobbyPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        Gson gson = new Gson();
        currentPlayer = gson.fromJson(getIntent().getStringExtra("currentPlayer"), Player.class);
        currentGame = gson.fromJson(getIntent().getStringExtra("currentGame"), Game.class);
        System.out.println("Current Player: " + currentPlayer.getUsername());
        System.out.println("Current Game: " + currentGame.getGameName());

        lobbyPresenter = new LobbyPresenter(this, currentGame);

        startButton = findViewById(R.id.start_game_button);

        //Only enable the Create Game button if the current player is the owner of the game
        /*
        if (currentGame.getOwner().equals(currentPlayer.getUsername())) {
            startButton.setEnabled(true);
        }
        else {
            startButton.setEnabled(false);

        }
        */

        //Set currentGameName textView
        currentGameName = findViewById(R.id.current_game_name);
        String currentGameNameText;
        currentGameNameText = "Current Game: " + currentGame.getGameName();
        currentGameName.setText(currentGameNameText);

        //Setup player list RecyclerView
        playerList = findViewById(R.id.lobby_player_list);
        playerList.setHasFixedSize(true);
        playerListLayoutManager = new LinearLayoutManager(this);
        playerList.setLayoutManager(playerListLayoutManager);
        playerListAdapter = new PlayerListAdapter(currentGame.getPlayers(), this);
        playerList.setAdapter(playerListAdapter);

        //Listener for the start button
        startButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "Start Game!", Toast.LENGTH_SHORT);
                toast.show();
                //Test recycler view update
                lobbyPresenter.startGame();
            }
        });
    }

    @Override
    public void onError(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), "message", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onGameStarted() {
        Toast toast = Toast.makeText(getApplicationContext(), "Game Started!", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onRosterUpdated(ArrayList<Player> players) {
        playerListAdapter = new PlayerListAdapter(players, this);
        playerList.setAdapter(playerListAdapter);

    }
}
