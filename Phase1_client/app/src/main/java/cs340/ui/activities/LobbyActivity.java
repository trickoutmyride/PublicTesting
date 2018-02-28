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
import java.util.HashMap;

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


    /**
     * On Activity creation, do the following:
     *      Get current player and game from the PreGameActivity
     *      Initialize LobbyPresenter
     *      Setup buttons, playerList RecyclerView, etc.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        Gson gson = new Gson();
        currentPlayer = gson.fromJson(getIntent().getStringExtra("currentPlayer"), Player.class);
        currentGame = gson.fromJson(getIntent().getStringExtra("currentGame"), Game.class);

        System.out.println("Current Player: " + currentPlayer.getUsername());
        System.out.println("Current Game: " + currentGame.getGameName());

        lobbyPresenter = new LobbyPresenter(this);
        startButton = findViewById(R.id.start_game_button);

        HashMap<String, String> hm =  currentGame.getColors();

        //Set currentGameName textView
        currentGameName = findViewById(R.id.current_game_name);
        final String currentGameNameText;
        currentGameNameText = "Current Game: " + currentGame.getGameName();
        currentGameName.setText(currentGameNameText);

        //Setup player list RecyclerView
        playerList = findViewById(R.id.lobby_player_list);
        playerList.setHasFixedSize(true);
        playerListLayoutManager = new LinearLayoutManager(this);
        playerList.setLayoutManager(playerListLayoutManager);
        playerListAdapter = new PlayerListAdapter(currentGame.getPlayers(), this, currentPlayer, currentGame.getColors());
        playerList.setAdapter(playerListAdapter);

        if (currentPlayer.getUsername().equals(currentGame.getPlayers().get(0).getUsername())){
            startButton.setEnabled(true);
        }
        else {
            startButton.setEnabled(false);
            startButton.setAlpha((float)0.5);
        }

        //Listener for the start button
        startButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentGame.getPlayers().size() == 1) {
                    onError("Cannot start game with one player.");
                }
                else {
                    lobbyPresenter.startGame();
                    onError("Game Started!");
                }

                /*
                //Eventually implement capacity check
                if (currentGame.getCapacity() == currentGame.getPlayers().size()){
                    lobbyPresenter.startGame();
                    onError("Game Started!");
                }
                else {
                    //onError("Not enough players to start. Waiting on " + Integer.toString(currentGame.getCapacity() - currentGame.getPlayers().size()) + "players.");
                    onError("Game Started!");
                }
                */
            }
        });
    }


    /**
     * Display an error message for the user
     */
    @Override
    public void onError(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * Tell the user that another player has started the game
     */
    @Override
    public void onGameStarted() {
        Toast toast = Toast.makeText(getApplicationContext(), "Start Game!", Toast.LENGTH_SHORT);
        toast.show();
        //lobbyPresenter.startGame();
    }

    /**
     * Update the game list by resetting the list adapter for the playerList RecyclerView
     */
    @Override
    public void onGameUpdated(Game game) {
        playerListAdapter = new PlayerListAdapter(game.getPlayers(), this, currentPlayer, currentGame.getColors());
        playerList.setAdapter(playerListAdapter);

    }
}
