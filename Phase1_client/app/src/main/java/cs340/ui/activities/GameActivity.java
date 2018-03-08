package cs340.ui.activities;

import android.app.DialogFragment;
import android.app.FragmentManager;
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
import cs340.shared.model.ClientModel;
import cs340.shared.model.DestinationCard;
import cs340.shared.model.Game;
import cs340.shared.model.Player;
import cs340.shared.model.TrainCard;
import cs340.ui.R;
import cs340.ui.fragments.ChatFragment;
import cs340.ui.fragments.DeckFragment;
import cs340.ui.fragments.DestinationCardFragment;
import cs340.ui.fragments.DestinationCardSelectionAdapter;
import cs340.ui.fragments.HistoryFragment;
import cs340.ui.fragments.IHandFragment;
import cs340.ui.fragments.IPlayersFragment;
import cs340.ui.presenters.GamePresenter;
import cs340.ui.presenters.IGamePresenter;

/**
 * GameActivity
 * This Activity controls the main UI for the actual game.
 *
 * Persistent fragments:
 *      MapFragment       displays the current map
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
 * This activity also implements listeners for the DestinationCard, Chat, and Deck fragments.
 *
 */
public class GameActivity extends AppCompatActivity implements IGameActivity, DestinationCardFragment.DestinationCardDialogListener,
        ChatFragment.ChatFragmentListener {


    //TODO: Display points on Destination Card
    //TODO: Detach presenters
    //TODO: Change the AWFUL green and yellow colors
    //TODO: Why can't the person who created the game start it
    //TODO: Implement draw destination card functionality (phase 3?)
    //TODO: Change hardcoded strings to resource values
    //TODO: Presenter onError methods
    //TODO: Implement capacity check (LobbyActivity)
    //Todo: Disable join full game "feature" (PreGameActivity)


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
    private ArrayList<TrainCard> currentFaceUpCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Get current player, game from previous activity, initialize presenter
        Gson gson = new Gson();
        currentPlayer = gson.fromJson(getIntent().getStringExtra("currentPlayer"), Player.class);
        currentGame = gson.fromJson(getIntent().getStringExtra("currentGame"), Game.class);
        gamePresenter = new GamePresenter(this);


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
        handFragment.onTrainCardsUpdated(currentPlayer.getCards());

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
        //  Currently creates fake hand cards, history items, chat items, destination cards, and face up deck cards.
        Button testButton = findViewById(R.id.testButton);
        testButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                createFakeHand();
                createFakeHistory();
                createFakeDestinationCards();
                //createFakeDeckCards();
                createFakeChat();
                addFakePlayerData();
                for (Player player : currentGame.getPlayers()) {
                    playersFragment.onPlayerUpdated(player);
                }
            }
        });
    }

    private void addFakePlayerData(){
        Random rand = new Random();
        for (Player player : currentGame.getPlayers()){
            player.setPoints(rand.nextInt(20));
            player.setTrainCars(rand.nextInt(50));
            player.setDestinations(fakeDestCards(rand.nextInt(3) + 1));
            player.setCards(makeFakeTrainCards());
        }
    }

    private ArrayList<TrainCard> makeFakeTrainCards(){

        //Create a bunch of fake train cards
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

        for (int i = 0; i < rand.nextInt(10); i++){
            trainCards.add(tcblack);
        }

        for (int i = 0; i < rand.nextInt(10); i++){
            trainCards.add(tcblue);
        }

        for (int i = 0; i < rand.nextInt(10); i++){
            trainCards.add(tcgreen);
        }

        for (int i = 0; i < rand.nextInt(10); i++){
            trainCards.add(tcorange);
        }

        for (int i = 0; i < rand.nextInt(10); i++){
            trainCards.add(tcpurple);
        }

        for (int i = 0; i < rand.nextInt(10); i++){
            trainCards.add(tcred);
        }

        for (int i = 0; i < rand.nextInt(10); i++){
            trainCards.add(tcyellow);
        }

        for (int i = 0; i < rand.nextInt(10); i++){
            trainCards.add(tcwhite);
        }

        for (int i = 0; i < rand.nextInt(10); i++){
            trainCards.add(tcwild);
        }
        return trainCards;
    }

    //Create fake train card data
    private void createFakeHand(){
        handFragment.onTrainCardsUpdated(makeFakeTrainCards());
    }

    //Create fake history data
    private void createFakeHistory(){

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

        ArrayList<String> fakeStrings = new ArrayList<>();
        fakeStrings.add(s1); fakeStrings.add(s2); fakeStrings.add(s3); fakeStrings.add(s4); fakeStrings.add(s5); fakeStrings.add(s6);
        fakeStrings.add(s7); fakeStrings.add(s8); fakeStrings.add(s9); fakeStrings.add(s10); fakeStrings.add(s11); fakeStrings.add(s12);
        fakeStrings.add(s13); fakeStrings.add(s14); fakeStrings.add(s15); fakeStrings.add(s16);

        Collections.shuffle(fakeStrings, new Random());

        currentHistory = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(15); i++){
            currentHistory.add(fakeStrings.get(i));
        }
    }

    //Create fake destination cards
    private void createFakeDestinationCards(){
        ArrayList<DestinationCard> cards = new ArrayList<>();
        DestinationCard card = new DestinationCard("SLC", "Vegas", 10);
        cards.add(card);
        card = new DestinationCard("NYC", "Boston", 15);
        cards.add(card);
        card = new DestinationCard("Miami", "Jacksonville", 8);
        cards.add(card);
        card = new DestinationCard("Pittsburgh", "Chicago", 12);
        cards.add(card);
        currentPlayer.setDestinations(cards);
    }

    private ArrayList<DestinationCard> fakeDestCards(int num){
        ArrayList<DestinationCard> cards = new ArrayList<>();
        DestinationCard card;
        switch (num) {
            case 1:
                card = new DestinationCard("SLC", "Vegas", 10);
                cards.add(card);
                break;
            case 2:
                card = new DestinationCard("NYC", "Boston", 15);
                cards.add(card);
                card = new DestinationCard("Miami", "Jacksonville", 8);
                cards.add(card);
                break;
            case 3:
                card = new DestinationCard("Pittsburgh", "Chicago", 12);
                cards.add(card);
                card = new DestinationCard("Austin", "Dallas", 2);
                cards.add(card);
                card = new DestinationCard("Seattle", "Portland", 5);
                cards.add(card);
                break;
            default:
                card = new DestinationCard("Anywhere", "Somewhere", 1);
                cards.add(card);
        }
        return cards;
    }



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

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    //Store updated history - will be displayed the next time the History dialog appears
    public void onHistoryUpdated(ArrayList<String> history){
        currentHistory = history;
    }

    //Destination Card Selection Confirmed
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

    @Override
    public void sendMessage(String message) {
        ChatService.chat(currentPlayer, message);
    }

    @Override
    public void updateHistory(String historyItem){
        this.currentHistory.add(historyItem);
    }

    public void setCurrentChat(ArrayList<String> currentChat){
        this.currentChat = currentChat;
    }


    public void onChatUpdated(String message){
        currentChat.add(message);
        if (chatFragment != null)
        {
            chatFragment.onChatUpdated(message);
        }
    }

    @Override
    public void onDrawnDestinationCards(ArrayList<DestinationCard> cards){

    }

    @Override
    public void onPlayerCardsUpdated(ArrayList<TrainCard> newCards){
        currentPlayer.setCards(newCards);
        handFragment.onTrainCardsUpdated(newCards);
    }



        /*
    //Create fake face up deck cards
    private void createFakeDeckCards(){
        ArrayList<TrainCard> deckCards = new ArrayList<>();
        TrainCard blueCard = new TrainCard("blue");
        deckCards.add(blueCard);
        TrainCard purpleCard = new TrainCard("purple");
        deckCards.add(purpleCard);
        TrainCard whiteCard = new TrainCard("white");
        deckCards.add(whiteCard);
        TrainCard yellowCard = new TrainCard("yellow");
        deckCards.add(yellowCard);
        TrainCard orangeCard = new TrainCard("orange");
        deckCards.add(orangeCard);
        TrainCard blackCard = new TrainCard("black");
        deckCards.add(blackCard);
        TrainCard redCard = new TrainCard("red");
        deckCards.add(redCard);
        TrainCard greenCard= new TrainCard("green");
        deckCards.add(greenCard);
        TrainCard wildCard = new TrainCard("wild");
        deckCards.add(wildCard);

        Collections.shuffle(deckCards, new Random());
        ArrayList<TrainCard> fakeCards = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            fakeCards.add(deckCards.get(i));
        }
        currentFaceUpCards = fakeCards;
        onDeckUpdated(fakeCards);
    }
    */
}

