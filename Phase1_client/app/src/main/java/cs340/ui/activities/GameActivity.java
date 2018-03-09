package cs340.ui.activities;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import cs340.client.services.ChatService;
import cs340.client.services.DeckService;
import cs340.shared.model.DestinationCard;
import cs340.shared.model.Game;
import cs340.shared.model.Player;
import cs340.shared.model.TrainCard;
import cs340.ui.R;
import cs340.ui.activities.interfaces.IGameActivity;
import cs340.ui.fragments.ChatFragment;
import cs340.ui.fragments.DeckFragment;
import cs340.ui.fragments.DestinationCardFragment;
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
 *      Add Data                    adds dummy data
 *
 * This activity also implements listeners for the DestinationCard and Deck fragments.
 *
 */
public class GameActivity extends AppCompatActivity implements IGameActivity, DestinationCardFragment.DestinationCardDialogListener {

    //Not as important:
    //TODO: Detach presenters
    //TODO: Implement draw destination card functionality (phase 3?)
    //TODO: Presenter onError methods
    //TODO: Display points on Destination Card?
    //      neccesary for Phase 2?


    //Phase 1 to dos
    //TODO: Implement capacity check (LobbyActivity)
    //Todo: Disable join full game "feature" (PreGameActivity)


    //Data Members
    private IGamePresenter gamePresenter;
    private ImageButton chatButton, historyButton, destinationCardButton;
    private IHandFragment handFragment;
    private IPlayersFragment playersFragment;
    private HistoryFragment historyFragment;
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

        //Pop up Destination Card dialog for initial destination card selection
        Bundle bundle = new Bundle();
        Gson g = new Gson();
        bundle.putString("player", gson.toJson(currentPlayer));
        bundle.putBoolean("selection", true);
        destinationCardFragment = new DestinationCardFragment();
        FragmentManager fm = getFragmentManager();
        destinationCardFragment.setArguments(bundle);
        destinationCardFragment.show(fm, "destinationfragment");


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


        //  Fake data button
        Button testButton = findViewById(R.id.testButton);
        testButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (myTurn()) {

                    // Chat (10 points)

                    gamePresenter.sendMessage("I just sent a test message to everyone.");
                    Toast toast = Toast.makeText(getApplicationContext(), "Chat sent! Check device 2 chat log. 10s left", Toast.LENGTH_SHORT);
                    toast.show();

                    new Handler().postDelayed(new Runnable(){

                        @Override
                        public void run() {
                            // Indicate number of cards other players have (5 points)
                            // Hand of Current Player is displayed (5 points)
                            // Face up deck cards can change (5 points)
                            // Game History (5 points)

                            deckFragment.cardSelected(1);
                            Toast newToast = Toast.makeText(getApplicationContext(), "Selected face up card 1! 20s left", Toast.LENGTH_SHORT);
                            newToast.show();
                            newToast = Toast.makeText(getApplicationContext(), "See device 2 - card count update, face up deck, history. 18s left", Toast.LENGTH_SHORT);
                            newToast.show();
                            newToast = Toast.makeText(getApplicationContext(), "See current device 1 - hand changed. 16s left", Toast.LENGTH_SHORT);
                            newToast.show();

                            /*
                            new Handler().post(new Runnable(){
                                @Override
                                public void run() {
                                    // Routes claimed shows on map (10 points)
                                    //      Player's points can be changed
                                    //      Trains remaining can be changed

                                    //TODO: @brettbeatty implement map




                                    new Handler().post(new Runnable(){

                                        @Override
                                        public void run() {
                                           // Turn can be changed (5 points)
                                           //TODO: end turn automated test
                                        }
                                    }, 10000);
                                    */
                            /*
                                }
                            }, 15000);
                            */

                        }
                    }, 10000);

                }

            }
        });
    }


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     *  Fake History Data Methods:
     *      createFakeHistory()     - Makes/adds fake history items, updates fragment if currently shown
     */

    private void createFakeHistory(){

        //Make fake history items
        String s1 = "player1 claimed a Route";
        String s2 = "player1 drew 3 Destination cards";
        String s3 = "player1 drew 2 Train cards";
        String s4 = "player1 drew 1 Train card";
        String s5 = "player2 claimed a Route";
        String s6 = "player2 drew 3 Destination cards";
        String s7 = "player2 drew 2 Train cards";
        String s8 = "player2 drew 1 Train card";
        String s9 = "player3 claimed a Route";
        String s10 = "player3 drew 3 Destination cards";
        String s11 = "player3 drew 2 Train cards";
        String s12 = "player3 drew 1 Train card";
        String s13 = "player4 claimed a Route";
        String s14 = "player4 drew 3 Destination cards";
        String s15 = "player4 drew 2 Train cards";
        String s16 = "player4 drew 1 Train card";

        //Add fake history items to an array list
        ArrayList<String> fakeStrings = new ArrayList<>();
        fakeStrings.add(s1); fakeStrings.add(s2); fakeStrings.add(s3); fakeStrings.add(s4); fakeStrings.add(s5); fakeStrings.add(s6);
        fakeStrings.add(s7); fakeStrings.add(s8); fakeStrings.add(s9); fakeStrings.add(s10); fakeStrings.add(s11); fakeStrings.add(s12);
        fakeStrings.add(s13); fakeStrings.add(s14); fakeStrings.add(s15); fakeStrings.add(s16);

        //Shuffle up the array list
        Collections.shuffle(fakeStrings, new Random());

        //Add a random amount of history items to the history - between 1 and 16
        for (int i = 0; i < new Random().nextInt(15); i++){
            currentHistory.add(fakeStrings.get(i));
            if (historyFragment != null){
                historyFragment.updateHistory(fakeStrings.get(i));
            }
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     *  Fake Hand Data Methods:
     *      FakeHandRunnable.class  - sets current player's cards, updates fragment with @param ArrayList<TrainCard> cards
     *      createFakeHand()        - calls makeFakeTrainCards() and waits for 1s in-between adding the cards to the hand
     *      makeFakeTrainCards()    - makes between 0 and 1 (randomly) of each type of train card
     */

    public class FakeHandRunnable implements Runnable {
        private ArrayList<TrainCard> _cards;
        public FakeHandRunnable(ArrayList<TrainCard> cards){
            _cards = cards;
        }
        @Override
        public void run(){
            currentPlayer.setCards(_cards);
            handFragment.onTrainCardsUpdated(currentPlayer);
        }
    }

    private void createFakeHand(){
        //Get fake cards
        ArrayList<TrainCard> fakeCards = makeFakeTrainCards();

        //For each card, add it to the player's hand. Wait 1 second between each add.
        for (TrainCard card : fakeCards){
            ArrayList<TrainCard> playerCards = currentPlayer.getCards();
            playerCards.add(card);
            Handler h = new Handler();
            h.postDelayed(new FakeHandRunnable(playerCards),  1000);
        }
    }

    private ArrayList<TrainCard> makeFakeTrainCards(){

        //Create one of each type of train cards
        TrainCard tcblack, tcblue, tcgreen, tcorange, tcpurple, tcred, tcwhite, tcwild, tcyellow;
        tcblack = new TrainCard("black");
        tcblue = new TrainCard("blue");
        tcgreen = new TrainCard("green");
        tcorange = new TrainCard("orange");
        tcpurple = new TrainCard("purple");
        tcred = new TrainCard("red");
        tcwhite = new TrainCard("white");
        tcwild = new TrainCard("wild");
        tcyellow = new TrainCard("yellow");

        //Add random amount of cards
        ArrayList<TrainCard> trainCards = new ArrayList<>();

        Random rand = new Random();

        //Make between 0 and 1 of each card

        for (int i = 0; i < rand.nextInt(1); i++){trainCards.add(tcblack);}

        for (int i = 0; i < rand.nextInt(1); i++){trainCards.add(tcblue);}

        for (int i = 0; i < rand.nextInt(1); i++){trainCards.add(tcgreen);}

        for (int i = 0; i < rand.nextInt(1); i++){trainCards.add(tcorange);}

        for (int i = 0; i < rand.nextInt(1); i++){trainCards.add(tcpurple);}

        for (int i = 0; i < rand.nextInt(1); i++){trainCards.add(tcred);}

        for (int i = 0; i < rand.nextInt(1); i++){trainCards.add(tcyellow);}

        for (int i = 0; i < rand.nextInt(1); i++){trainCards.add(tcwhite);}

        for (int i = 0; i < rand.nextInt(1); i++){trainCards.add(tcwild);}

        return trainCards;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     *  Callbacks:
     *      updateHistory()             - Presenter is updating the fragment, this is to update the saved history log
     *      onDialogPositiveClick()     - Destination Card Fragment selection confirmed - send discarded to server
     *      onChatUpdated()             - New message - add to chat log and display if dialog visible
     *      onDrawnDestinationCards()   - Not implemented (Phase 3)
     *      onPlayerCardsUpdated()      - Player has drawn new cards - update cards in currentPlayer and display
     *      updateHistory()             -
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
            DeckService.discardDestination(currentGame.getGameID(), unselected, currentPlayer);
        }
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
    public void onDrawnDestinationCards(ArrayList<DestinationCard> cards){}

    //called by updateFaceUpDeck in DeckPresenter
    @Override
    public void onPlayerCardsUpdated(final int index, final TrainCard oldCard, final TrainCard newCard, final Player player){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (currentPlayer.getUsername().equals(player.getUsername())){
                    currentPlayer = player;
                    handFragment.onTrainCardsUpdated(currentPlayer);
                }
                playersFragment.onPlayerUpdated(player);
                deckFragment.onFaceUpCardUpdated(newCard, index);
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
    public void onDestinationCardsUpdated(final Player player){
        System.out.println("GameActivity onDestinationCardsUpdated() ");

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

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     *  Getters:
     *      getCurrentPlayer()      - gets current player
     *      getCurrentGame()        - gets current game
     *      getPresenter()          - gets GamePresenter
     */

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public Game getCurrentGame() {
        return currentGame;
    }
    public IGamePresenter getGamePresenter() { return gamePresenter; }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    public boolean myTurn(){
        return currentGame.getPlayers().get(currentGame.getTurn()).getUsername().equals(currentPlayer.getUsername());
    }

    public int getTurnIndex(){
        return currentGame.getTurn();
    }

    //Not sure if we need these:

    private void createFakeChat(){

        ArrayList<String> fakeChat = new ArrayList<>();

        String s1 = "player1: hey guys";
        String s2 = "player2: hey";
        String s3 = "player3: gang's all here!";
        String s4 = "player1: is it my turn?";
        String s5 = "player3: no";
        String s6 = "player2: gonna claim this here route";
        String s7 = "player3: hey where's player4";
        String s8 = "player4: okay let's do this. LEEEEEROOOOYYYY... JEEEENNNNKKKKIIIINNNSSSSSS";

        fakeChat.add(s1); fakeChat.add(s2); fakeChat.add(s3);
        fakeChat.add(s4); fakeChat.add(s5); fakeChat.add(s6);
        fakeChat.add(s7); fakeChat.add(s8);
        currentChat = fakeChat;
    }
    public void setCurrentChat(ArrayList<String> currentChat){this.currentChat = currentChat;}

}

