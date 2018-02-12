package cs340.ui.activities;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
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
import cs340.ui.presenters.IPreGamePresenter;
import cs340.ui.presenters.MockPreGamePresenter;

public class PreGameActivity extends AppCompatActivity implements CreateGameDialogFragment.CreateGameDialogListener, IPreGameActivity {

    //TODO: Can't create a game with the same name, fix this

    private RecyclerView gameList;
    private RecyclerView.Adapter gameListAdapter;
    private RecyclerView.LayoutManager gameListLayoutManager;
    private Button createGameButton;
    private String newGameColor;
    private int newGameCapacity;
    private String newGameName;
    private IPreGamePresenter preGamePresenter;
    private Player currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Get current player from LoginActivity
        Bundle bundle = getIntent().getExtras();
        Gson gson = new Gson();
        currentPlayer = gson.fromJson(bundle.getString("currentPlayer"), Player.class);

        //Initialize preGamePresenter
        preGamePresenter = new MockPreGamePresenter(this);

        //Default is 2
        newGameCapacity = 2;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game);

        gameList = findViewById(R.id.games_list);
        gameList.setHasFixedSize(true);
        gameListLayoutManager = new LinearLayoutManager(this);
        gameList.setLayoutManager(gameListLayoutManager);
        createGameButton = findViewById(R.id.createGameButton);

        //Add a bunch of dummy game data
        GameList listOfGames = new GameList();
        ArrayList<Game> arrayListOfGames = new ArrayList<>();
        Game tempGame = new Game();
        tempGame.setCapacity(5);
        tempGame.setGameName("game1");
        Player tempPlayer = new Player("test", "test", "test");
        tempPlayer.setUsername("test");
        ArrayList<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(tempPlayer);
        tempGame.setPlayers(listOfPlayers);
        arrayListOfGames.add(tempGame);
        Game tempGame2 = new Game();
        tempGame2.setCapacity(5);
        tempGame2.setGameName("game2");
        Player tempPlayer2 = new Player("test2", "test2", "test2");
        Player tempPlayer3 = new Player("test3", "test3", "test3");
        tempPlayer3.setUsername("test3");
        tempPlayer2.setUsername("test2");
        ArrayList<Player> listOfPlayers2 = new ArrayList<>();
        listOfPlayers2.add(tempPlayer2);
        listOfPlayers.add(tempPlayer3);
        tempGame2.setPlayers(listOfPlayers2);
        arrayListOfGames.add(tempGame2);
        listOfGames.setGames(arrayListOfGames);

        gameListAdapter = new GameListAdapter(listOfGames, this);

        gameList.setAdapter(gameListAdapter);

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
        //Send join game request to Presenter
        preGamePresenter.joinGame(game);

    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        //confirm button clicked, everything was verified
        CreateGameDialogFragment cdf = (CreateGameDialogFragment)dialog;
        preGamePresenter.createGame(((CreateGameDialogFragment) dialog).getNewGameName(),
                ((CreateGameDialogFragment) dialog).getNewGameCapacity(), currentPlayer, ((CreateGameDialogFragment) dialog).getNewGamePlayerColor());
    }

    public void onError(String message) {
        //toast it up
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onGameListUpdated(GameList games) {

        //Add a bunch of dummy game data
        GameList listOfGames = new GameList();
        ArrayList<Game> arrayListOfGames = new ArrayList<>();
        Game tempGame = new Game();
        tempGame.setCapacity(5);
        tempGame.setGameName("GAME1");
        Player tempPlayer = new Player("TEST", "test", "test");
        tempPlayer.setUsername("TEST");
        ArrayList<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(tempPlayer);
        tempGame.setPlayers(listOfPlayers);
        arrayListOfGames.add(tempGame);
        Game tempGame2 = new Game();
        tempGame2.setCapacity(5);
        tempGame2.setGameName("GAME2");
        Player tempPlayer2 = new Player("TEST2", "test2", "test2");
        tempPlayer2.setUsername("TEST2");
        ArrayList<Player> listOfPlayers2 = new ArrayList<>();
        listOfPlayers2.add(tempPlayer2);
        tempGame2.setPlayers(listOfPlayers2);
        arrayListOfGames.add(tempGame2);
        listOfGames.setGames(arrayListOfGames);
        games = listOfGames;
        //End dummy data

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

}
