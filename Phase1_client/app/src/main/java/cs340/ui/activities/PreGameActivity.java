package cs340.ui.activities;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import cs340.shared.model.Game;
import cs340.shared.model.GameList;
import cs340.shared.model.Player;
import cs340.ui.R;
import cs340.ui.presenters.IPregamePresenter;
import cs340.ui.presenters.MockPreGamePresenter;
import cs340.ui.presenters.PregamePresenter;

public class PreGameActivity extends AppCompatActivity implements CreateGameDialogFragment.CreateGameDialogListener, IPreGameActivity, JoinGameDialogFragment.JoinGameDialogListener {

    //TODO: Can't join a game if it's full

    private RecyclerView gameList;
    private RecyclerView.Adapter gameListAdapter;
    private RecyclerView.LayoutManager gameListLayoutManager;
    private Button createGameButton;
    private int newGameCapacity;
    private IPregamePresenter preGamePresenter;
    private Player currentPlayer;
    private Game joinGame;
    private ArrayList<Game> currentGameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game);

        //Get current player from LoginActivity
        Bundle bundle = getIntent().getExtras();
        Gson gson = new Gson();
        currentPlayer = gson.fromJson(bundle.getString("currentPlayer"), Player.class);

        //Initialize preGamePresenter
        preGamePresenter = new PregamePresenter(this);

        //Default capacity is 2
        newGameCapacity = 2;

        //Initialize gameList view
        gameList = findViewById(R.id.games_list);
        gameList.setHasFixedSize(true);
        gameListLayoutManager = new LinearLayoutManager(this);
        gameList.setLayoutManager(gameListLayoutManager);
        createGameButton = findViewById(R.id.createGameButton);

        //Initialize adapter for RecyclerView
        gameList.setAdapter(gameListAdapter);

        //OnClickListener for Create Game button
        createGameButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                CreateGameDialogFragment createGameFragment = new CreateGameDialogFragment();
                FragmentManager fm = getFragmentManager();
                createGameFragment.show(fm, "creategame");
            }
        });
    }

    protected void joinGame(Game game) {
        joinGame = game;
        //Send join game request to Presenter
        JoinGameDialogFragment joinGameFragment = new JoinGameDialogFragment();
        FragmentManager fm = getFragmentManager();
        joinGameFragment.show(fm, "joingame");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

        //If called by CreateGameDialogFragment
        if (dialog.getClass() == CreateGameDialogFragment.class) {
            String newGameName = ((CreateGameDialogFragment) dialog).getNewGameName();
            Boolean inUse = false;

            //Loop through current game list
            for (int i = 0; i < currentGameList.size(); i++) {
                //If the new game name is already in use
                if (newGameName.equals(currentGameList.get(i).getGameName())) {
                    inUse = true;
                }
            }
            //If game in use, display an error.
            if (inUse) {
                onError("Try again- Game name in use");
            } else {
                final DialogFragment dialog2 = dialog;
                preGamePresenter.createGame(((CreateGameDialogFragment)dialog2).getNewGameName(),
                        currentPlayer,
                        ((CreateGameDialogFragment) dialog2).getNewGameCapacity(),
                        ((CreateGameDialogFragment) dialog2).getNewGamePlayerColor());

                /*
                new AsyncTask<Void, Void, Void>() {
                    protected void onPreExecute() {
                        // Pre Code
                    }
                    protected Void doInBackground(Void... unused) {
                        preGamePresenter.createGame(((CreateGameDialogFragment)dialog2).getNewGameName(),
                                currentPlayer,
                                ((CreateGameDialogFragment) dialog2).getNewGameCapacity(),
                                ((CreateGameDialogFragment) dialog2).getNewGamePlayerColor());
                        return null;
                    }
                    protected void onPostExecute(Void unused) {
                        // Post Code
                    }
                }.execute();
                */
            }
        }
        //If called by JoinGameDialogFragment
        else if (dialog.getClass() == JoinGameDialogFragment.class) {
            final DialogFragment dialog2 = dialog;
            preGamePresenter.joinGame(joinGame.getGameID(), currentPlayer, ((JoinGameDialogFragment) dialog2).getPlayerColor());

            /*
            new AsyncTask<Void, Void, Void>() {
                protected void onPreExecute() {
                    // Pre Code
                }
                protected Void doInBackground(Void... unused) {
                    preGamePresenter.joinGame(joinGame.getGameID(), currentPlayer, ((JoinGameDialogFragment) dialog2).getPlayerColor());
                    return null;
                }
                protected void onPostExecute(Void unused) {
                    // Post Code
                }
            }.execute();
            */
        }
    }

    public void onError(String message) {
        //toast it up
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onGameListUpdated(ArrayList<Game> games) {

        System.out.println("OnGameListUpdated");
        System.out.println(games.get(0).getGameName());

        currentGameList = games;
        gameListAdapter = new GameListAdapter(games, this);
        gameList.setAdapter(gameListAdapter);
    }

    @Override
    public void onGameJoined(Game game) {
        //Go to lobby activity
        Intent intent = new Intent(this, LobbyActivity.class);
        Gson gson = new Gson();
        //Pass game and current player to the lobby activity
        intent.putExtra("currentGame", gson.toJson(game));
        intent.putExtra("currentPlayer", gson.toJson(currentPlayer));
        startActivity(intent);
    }

    public Game getJoinGame(){
        return joinGame;
    }

}
