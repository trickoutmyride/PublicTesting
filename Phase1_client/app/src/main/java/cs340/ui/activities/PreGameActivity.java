package cs340.ui.activities;

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

import java.util.ArrayList;

import cs340.shared.model.Game;
import cs340.shared.model.GameList;
import cs340.shared.model.Player;
import cs340.ui.R;

public class PreGameActivity extends AppCompatActivity implements CreateGameDialogFragment.CreateGameDialogListener, IPreGameActivity {

    private RecyclerView gameList;
    private RecyclerView.Adapter gameListAdapter;
    private RecyclerView.LayoutManager gameListLayoutManager;
    private Button createGameButton;
    private String newGameColor;
    private int newGameCapacity;
    private String newGameName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        tempPlayer2.setUsername("test2");
        ArrayList<Player> listOfPlayers2 = new ArrayList<>();
        listOfPlayers2.add(tempPlayer2);
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
        System.out.println("Join Game Attempt: " + game.getGameName());
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        //confirm button clicked
        CreateGameDialogFragment cdf = (CreateGameDialogFragment)dialog;
        System.out.println("New game!");
        System.out.println("Name: " + cdf.getNewGameName());
        System.out.println("Capacity: " + cdf.getNewGameCapacity());
        System.out.println("Color: " + cdf.getNewGameColor());
        onGameListUpdated(null);
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

        gameListAdapter = new GameListAdapter(games, this);
        gameList.setAdapter(gameListAdapter);
    }

    @Override
    public void onGameJoined(Game game) {
        //Go to lobby activity
        Intent intent = new Intent(this, LobbyActivity.class);
        startActivity(intent);
    }

}
