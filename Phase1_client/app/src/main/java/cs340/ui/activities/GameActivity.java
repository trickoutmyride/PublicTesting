package cs340.ui.activities;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import cs340.client.services.DeckService;
import cs340.shared.model.ClientModel;
import cs340.shared.model.DestinationCard;
import cs340.shared.model.Game;
import cs340.shared.model.Player;
import cs340.shared.model.TrainCard;
import cs340.ui.R;
import cs340.ui.activities.interfaces.IGameActivity;
import cs340.ui.fragments.ChatFragment;
import cs340.ui.fragments.DeckFragment;
import cs340.ui.fragments.DestinationCardFragment;
import cs340.ui.fragments.GameMapFragment;
import cs340.ui.fragments.adapters.DestinationCardSelectionAdapter;
import cs340.ui.fragments.HistoryFragment;
import cs340.ui.fragments.interfaces.IHandFragment;
import cs340.ui.fragments.interfaces.IPlayersFragment;
import cs340.ui.presenters.GamePresenter;
import cs340.ui.presenters.interfaces.IGamePresenter;

/**
 * GameActivity
 * This Activity controls the main UI for the actual game.
 *
 * Persistent fragments:
 *      GameMapFragment       displays the current map
 *      PlayersFragment   displays information about the game's players
 *      HandFragment      displays cards in the current player's hand
 *      DeckFragment      displays deck, face up cards that can be drawn on a turn
 *
 * Buttons:
 *      Destination Card Button     displays the current player's destination cards (DestinationCardFragment)
 *      Chat Button                 displays the chat (ChatFragment)
 *      History Button              displays the game history (HistoryFragment)
 *      Test Driver                 runs phase 2 test driver
 *
 */
public class GameActivity extends AppCompatActivity implements IGameActivity, DestinationCardFragment.DestinationCardDialogListener {

    //Phase 3 To Dos:
    //TODO: Presenter Classes onError
    //TODO: Draw Additional Destination Cards
        //When you draw additional, you must keep at least one
        //End turn after draw

    //Phase 2 to dos
    //TODO: Detach presenters
    //TODO: Implement draw destination card functionality (phase 3?)
    //TODO: Display points on Destination Card?

    //Phase 1 to dos
    //TODO: Implement capacity check (LobbyActivity)
    //Todo: Disable join full game "feature" (PreGameActivity)


    //Data Members
    private IGamePresenter gamePresenter;
    private ImageButton chatButton, historyButton, destinationCardButton;
    private IHandFragment handFragment;
    private IPlayersFragment playersFragment;
    private HistoryFragment historyFragment;
    private GameMapFragment mapFragment;
    private DeckFragment deckFragment;
    private ChatFragment chatFragment;
    private DestinationCardFragment destinationCardFragment;
    private Player currentPlayer;
    private Game currentGame;
    private ArrayList<String> currentHistory;
    private ArrayList<String> currentChat;


    //Initialize Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Get current player, game from previous activity, initialize presenter
        Gson gson = new Gson();
        currentPlayer = gson.fromJson(getIntent().getStringExtra("currentPlayer"), Player.class);
        currentGame = gson.fromJson(getIntent().getStringExtra("currentGame"), Game.class);
        gamePresenter = new GamePresenter(this);

        currentHistory = new ArrayList<>();
        currentChat = new ArrayList<>();

        //Pop up destination card selection
        onDrawnDestinationCards(currentPlayer.getDestinations(), false);

        //Initialize the rest of the fragments

        //Populate the player's hand
        handFragment = (IHandFragment)getFragmentManager().findFragmentById(R.id.handFragment);
        handFragment.onTrainCardsUpdated(currentPlayer);

        //Populate the other player's stuff
        playersFragment = (IPlayersFragment)getFragmentManager().findFragmentById(R.id.playersFragment);
        playersFragment.initiatePlayers(currentGame.getPlayers());

        //Populate the face up cards
        deckFragment = (DeckFragment)getFragmentManager().findFragmentById(R.id.deckFragment);
        deckFragment.initializeFaceUpCards(currentGame.getTrainFaceup());

        //Get chat, history, destination card buttons
        chatButton = findViewById(R.id.chatButton);
        historyButton = findViewById(R.id.historyButton);
        destinationCardButton = findViewById(R.id.destinationCardButton);

        //Chat button listener - open Chat dialog fragment
        chatButton.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Pop up Chat dialog
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("chat", currentChat);
                chatFragment = new ChatFragment();
                FragmentManager fm = getFragmentManager();
                chatFragment.setArguments(bundle);
                chatFragment.show(fm, "chatfragment");
            }
        });

        //History button listener - open History dialog fragment
        historyButton.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Pop up History dialog
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("history", currentHistory);
                historyFragment = new HistoryFragment();
                FragmentManager fm = getFragmentManager();
                historyFragment.setArguments(bundle);
                historyFragment.show(fm, "historyfragment");
            }
        });

        //Destination button listener - open Destination dialog fragment in Display mode
        destinationCardButton.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Pop up Destination Card dialog
                Bundle bundle = new Bundle();
                Gson gson = new Gson();
                bundle.putString("player", gson.toJson(currentPlayer));
                bundle.putBoolean("selection", false);
                destinationCardFragment = new DestinationCardFragment();
                FragmentManager fm = getFragmentManager();
                destinationCardFragment.setArguments(bundle);
                destinationCardFragment.show(fm, "destinationfragment");
            }
        });

        mapFragment = (GameMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        ClientModel.getInstance().setCurrentGame(currentGame);
    }



    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     *  Callbacks:
     *      onHistoryItemUpdated()      - Adds new history item to fragment and log
     *      onDialogPositiveClick()     - Destination Card Fragment selection confirmed - send discarded to server
     *      onChatUpdated()             - New message - add to chat log and display if dialog visible
     *      onDrawnDestinationCards()   - Not implemented (Phase 3)
     *      onPlayerCardsUpdated()      - Player has drawn new cards - update cards in currentPlayer and display
     *          Two methods - first updates deck AND player, second just updates player
     *      onPlayerUpdated()           - Updates player info in PlayerFragment
     *      onTurnUpdated()             - Updates whose turn it is
     *      onDestinationCardsUpdated() - New destination cards received from the server
     */

    @Override
    public void onHistoryItemUpdated(final String item){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                currentHistory.add(item);
                if (historyFragment != null)
                {
                    historyFragment.updateHistory(item);
                }
            }
        });
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        DestinationCardFragment dcf = (DestinationCardFragment)dialog;

        DestinationCardSelectionAdapter dcsa = dcf.getDestinationCardSelectionAdapter();
        ArrayList<DestinationCard> unselected = dcsa.getUnselectedCards();
        //If a card was discarded, send it to the server
        if (unselected.size() != 0) {
            DeckService.discardDestination(currentGame.getGameID(), unselected, currentPlayer, dcf.getGameStarted());
        }
    }

    @Override
    public void onDrawNewDestinationCardsSelected() {
        DeckService.drawDestination(currentGame.getGameID(), currentPlayer);
    }

    public void onChatUpdated(final String message){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                currentChat.add(message);
                if (chatFragment != null)
                {
                    chatFragment.onChatUpdated(message);
                }
            }
        });
    }

    @Override
    public void onDrawnDestinationCards(ArrayList<DestinationCard> cards, boolean gameStarted) {
        //Pop up Destination Card dialog for initial destination card selection
        Bundle bundle = new Bundle();
        Gson gson = new Gson();
        bundle.putString("player", gson.toJson(currentPlayer));
        final Type type = new TypeToken<ArrayList<DestinationCard>>(){}.getType();
        bundle.putString("newCards", gson.toJson(cards, type));
        bundle.putBoolean("gameStarted", gameStarted);
        bundle.putBoolean("selection", true);
        destinationCardFragment = new DestinationCardFragment();
        FragmentManager fm = getFragmentManager();
        destinationCardFragment.setArguments(bundle);
        destinationCardFragment.show(fm, "destinationfragment");
    }

    //called by updateFaceUpDeck in DeckPresenter
    @Override
    public void onPlayerCardsUpdated(final int index, final TrainCard oldCard, final TrainCard newCard, final Player player, final ArrayList<TrainCard> faceUpCards){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (currentPlayer.getUsername().equals(player.getUsername())){
                    currentPlayer = player;
                    handFragment.onTrainCardsUpdated(currentPlayer);
                }
                playersFragment.onPlayerUpdated(player);

                int compareCount = 0;
                //Were the face up cards shuffled?
                for (int i = 0; i < currentGame.getTrainFaceup().size(); i++) {
                    //If the old card at index =/= new card at index for > 1 card
                    if (!currentGame.getTrainFaceup().get(i).getColor().equals(faceUpCards.get(i).getColor())) {
                        compareCount++;
                    }
                }
                //Multiple cards changed, it was shuffled
                if (compareCount > 1) {
                    deckFragment.initializeFaceUpCards(faceUpCards);
                }
                //Only one card changed (the drawn card)
                else {
                    deckFragment.onFaceUpCardUpdated(newCard, index);
                }
            }
        });
    }

    @Override
    public void onPlayerCardsUpdated(final Player player){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (currentPlayer.getUsername().equals(player.getUsername())){
                    currentPlayer = player;
                }
                playersFragment.onPlayerUpdated(player);
            }
        });
    }

    @Override
    public void onPlayerUpdated(final Player player){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (playersFragment != null) {
                    playersFragment.onPlayerUpdated(player);
                }
            }
        });
    }

    @Override
    public void onTurnUpdated(final Game game){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("onTurnUpdated!");
                currentGame = game;
                for (Player p : game.getPlayers()) {
                    onPlayerUpdated(p);
                }
            }
        });
    }

    @Override
    public void onDestinationCardsUpdated(final Player player){
        for (DestinationCard c : player.getDestinations()){
            System.out.println(c.getStartPoint() + " to " + c.getEndPoint());
        }

        runOnUiThread(new Runnable(){
            @Override
            public void run() {
                if (currentPlayer.getUsername().equals(player.getUsername())){
                    currentPlayer = player;
                }
                playersFragment.onPlayerUpdated(player);
            }
        });
    }

    @Override
    public void onGameEnded() {
        //Go to game over activity
        Intent intent = new Intent(this, EndGameActivity.class);
        Gson gson = new Gson();

        //Pass game and current player to the game over activity
        intent.putExtra("currentGame", gson.toJson(currentGame));
        intent.putExtra("currentPlayer", gson.toJson(currentPlayer));
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(String message){
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     *  Getters:
     *      getCurrentPlayer()      - gets current player
     *      getCurrentGame()        - gets current game
     *      getGamePresenter()      - gets GamePresenter
     *      myTurn()                - gets whether or not it's the current player's turn
     *      getTurnIndex()          - gets index of the player whose turn it is
     */

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public Game getCurrentGame() {
        return currentGame;
    }
    public IGamePresenter getGamePresenter() { return gamePresenter; }
    public boolean myTurn(){
        return currentGame.getPlayers().get(currentGame.getTurn()).getUsername().equals(currentPlayer.getUsername());
    }
    public int getTurnIndex(){
        return currentGame.getTurn();
    }
    public void setCurrentGame(Game game){currentGame = game;}

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

}

