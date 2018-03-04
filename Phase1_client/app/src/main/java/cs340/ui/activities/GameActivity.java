package cs340.ui.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Random;

import cs340.shared.model.TrainCard;
import cs340.ui.R;
import cs340.ui.fragments.HandFragment;
import cs340.ui.fragments.IHandFragment;
import cs340.ui.presenters.GamePresenter;
import cs340.ui.presenters.IGamePresenter;

public class GameActivity extends AppCompatActivity implements IGameActivity {

    private IGamePresenter gamePresenter;
    private ImageButton chatButton, historyButton, destinationCardButton;
    private IHandFragment handFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gamePresenter = new GamePresenter(this);
        handFragment = (IHandFragment)getFragmentManager().findFragmentById(R.id.handFragment);

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
                //Pop up History fragment
            }
        });

        destinationCardButton.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Pop up Destination Card fragment
            }
        });

        Button testButton = findViewById(R.id.testButton);
        testButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                createFakeHand();
            }
        });


    }


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
}
