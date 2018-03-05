package cs340.ui.activities;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import cs340.shared.model.Game;
import cs340.shared.model.Player;
import cs340.shared.model.TrainCard;
import cs340.ui.R;
import cs340.ui.fragments.HistoryFragment;
import cs340.ui.fragments.IHandFragment;
import cs340.ui.fragments.IPlayersFragment;
import cs340.ui.presenters.GamePresenter;
import cs340.ui.presenters.IGamePresenter;

public class GameActivity extends AppCompatActivity implements IGameActivity {

    //TODO: fix action bar behavior

    private IGamePresenter gamePresenter;
    private ImageButton chatButton, historyButton, destinationCardButton;
    private IHandFragment handFragment;
    private IPlayersFragment playersFragment;
    private HistoryFragment historyFragment;
    private Player currentPlayer;
    private Game currentGame;
    private ArrayList<String> currentHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Get current player
        Gson gson = new Gson();
        currentPlayer = gson.fromJson(getIntent().getStringExtra("currentPlayer"), Player.class);
        currentGame = gson.fromJson(getIntent().getStringExtra("currentGame"), Game.class);

        createFakeHistory();

        //Get presenter, fragments
        gamePresenter = new GamePresenter(this);
        handFragment = (IHandFragment)getFragmentManager().findFragmentById(R.id.handFragment);
        playersFragment = (IPlayersFragment)getFragmentManager().findFragmentById(R.id.playersFragment);
        playersFragment.initiatePlayers(currentGame.getPlayers());


        //Hide status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        //Get buttons
        chatButton = findViewById(R.id.chatButton);
        historyButton = findViewById(R.id.historyButton);
        destinationCardButton = findViewById(R.id.destinationCardButton);

        //Setup button listeners
        chatButton.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Pop up Chat fragment
            }
        });

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

        destinationCardButton.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Pop up Destination Card fragment
            }
        });

        //  Fake data button
        //  (currently train cards, history)
        Button testButton = findViewById(R.id.testButton);
        testButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                createFakeHand();
                createFakeHistory();
            }
        });

    }


    //Create fake train card data
    private void createFakeHand(){
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

        handFragment.onHandUpdated(trainCards);
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

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    //Store updated history for the next time the history button is clicked
    public void onHistoryUpdated(ArrayList<String> history){
        currentHistory = history;
    }

}
