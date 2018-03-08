package cs340.ui.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cs340.shared.model.TrainCard;
import cs340.ui.R;
import cs340.ui.fragments.interfaces.IHandFragment;
import cs340.ui.presenters.HandPresenter;
import cs340.ui.presenters.interfaces.IHandPresenter;


public class HandFragment extends Fragment implements IHandFragment {

    private IHandPresenter handPresenter;

    private ImageView blackCard, blueCard, greenCard, orangeCard, purpleCard, redCard, whiteCard, wildCard, yellowCard;
    private TextView blackLabel, blueLabel, greenLabel, orangeLabel, purpleLabel, redLabel, whiteLabel, wildCount, yellowLabel;

    private Animation fadeOut;
    private Animation fadeIn;


    public HandFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate layout
        View v = inflater.inflate(R.layout.fragment_hand, container, false);

        //Initialize presenter
        handPresenter = new HandPresenter(this);

        //Get card slots, labels
        blackCard = v.findViewById(R.id.hand_black_card); blackLabel = v.findViewById(R.id.hand_black_label);
        blueCard = v.findViewById(R.id.hand_blue_card); blueLabel = v.findViewById(R.id.hand_blue_label);
        greenCard = v.findViewById(R.id.hand_green_card); greenLabel = v.findViewById(R.id.hand_green_label);
        orangeCard = v.findViewById(R.id.hand_orange_card); orangeLabel = v.findViewById(R.id.hand_orange_label);
        purpleCard = v.findViewById(R.id.hand_purple_card); purpleLabel = v.findViewById(R.id.hand_purple_label);
        redCard = v.findViewById(R.id.hand_red_card); redLabel = v.findViewById(R.id.hand_red_label);
        whiteCard = v.findViewById(R.id.hand_white_card); whiteLabel = v.findViewById(R.id.hand_white_label);
        yellowCard = v.findViewById(R.id.hand_yellow_card); yellowLabel = v.findViewById(R.id.hand_yellow_label);
        wildCard = v.findViewById(R.id.hand_wild_card); wildCount = v.findViewById(R.id.hand_wild_label);

        //Create animations
        fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fadein);
        fadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.fadeout);

        // Inflate the layout for this fragment
        return v;
    }

    //Hand updated, redraw hand
    public void onTrainCardsUpdated(ArrayList<TrainCard> cards){

        //Initialize card counts
        int blackCount = 0, bluecount = 0, greenCount = 0, orangeCount = 0,
                purpleCount = 0, redCount = 0, whiteCount = 0, yellowCount = 0, wildCount = 0;

        //Count cards
        for (TrainCard card : cards) {
            switch (card.getColor()) {
                case "black":
                    blackCount++;
                    break;
                case "blue":
                    bluecount++;
                    break;
                case "green":
                    greenCount++;
                    break;
                case "orange":
                    orangeCount++;
                    break;
                case "purple":
                    purpleCount++;
                    break;
                case "red":
                    redCount++;
                    break;
                case "white":
                    whiteCount++;
                    break;
                case "yellow":
                    yellowCount++;
                    break;
                case "wild":
                    wildCount++;
                    break;
                default:
                    break;
            }
        }

        //Update cards based on count
        updateCard(blackCard, blackLabel, blackCount);
        updateCard(blueCard, blueLabel, bluecount);
        updateCard(greenCard, greenLabel, greenCount);
        updateCard(orangeCard, orangeLabel, orangeCount);
        updateCard(purpleCard, purpleLabel, purpleCount);
        updateCard(redCard, redLabel, redCount);
        updateCard(whiteCard, whiteLabel, whiteCount);
        updateCard(yellowCard, yellowLabel, yellowCount);
        updateCard(wildCard, this.wildCount, wildCount);
    }


    //  Update cards based on count
    private void updateCard(ImageView card, TextView label, int count){
        if (count == 0){
            //If count was previously != 0
            if (label.getText() == null || !(label.getText().equals("0"))){
                //Do fade out animation
                card.startAnimation(fadeOut);
                card.setAlpha(0.4f);
                label.startAnimation(fadeOut);
                label.setAlpha(0.4f);

                //Set label text to 0
                label.setText("0");
            }
        } else {
            //If count was previously == 0
            if (label.getText() != null && label.getText().equals("0")){
                //Do fade in animation
                card.startAnimation(fadeIn);
                card.setAlpha(1f);
                label.startAnimation(fadeIn);
                label.setAlpha(1f);

                //Set label text to count
                label.setText(Integer.toString(count));
            }
            //If count was previously != 0
            else if (label.getText() != null && !(label.getText().equals(Integer.toString(count)))) {
                //Flash in and out
                card.startAnimation(fadeOut);
                label.startAnimation(fadeOut);
                card.startAnimation(fadeIn);
                label.startAnimation(fadeIn);

                //Set label text to count
                label.setText(Integer.toString(count));
            }
        }
    }
}
