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
import cs340.ui.activities.PreGameActivity;
import cs340.ui.presenters.HandPresenter;
import cs340.ui.presenters.IHandPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HandFragment extends Fragment implements IHandFragment {

    private IHandPresenter handPresenter;

    private ImageView blackCard, blueCard, greenCard, orangeCard, purpleCard, redCard, whiteCard, wildCard, yellowCard;
    private TextView blackCount, blueCount, greenCount, orangeCount, purpleCount, redCount, whiteCount, wildCount, yellowCount;

    private Animation fadeOut;
    private Animation fadeIn;


    public HandFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        handPresenter = new HandPresenter(this);

        //Get card slots and text

        View v = inflater.inflate(R.layout.fragment_hand, container, false);

        blackCard = v.findViewById(R.id.hand_black_card);
        blackCount = v.findViewById(R.id.hand_black_count);

        blueCard = v.findViewById(R.id.hand_blue_card);
        blueCount = v.findViewById(R.id.hand_blue_count);

        greenCard = v.findViewById(R.id.hand_green_card);
        greenCount = v.findViewById(R.id.hand_green_count);

        orangeCard = v.findViewById(R.id.hand_orange_card);
        orangeCount = v.findViewById(R.id.hand_orange_count);

        purpleCard = v.findViewById(R.id.hand_purple_card);
        purpleCount = v.findViewById(R.id.hand_purple_count);

        redCard = v.findViewById(R.id.hand_red_card);
        redCount = v.findViewById(R.id.hand_red_count);

        whiteCard = v.findViewById(R.id.hand_white_card);
        whiteCount = v.findViewById(R.id.hand_white_count);

        yellowCard = v.findViewById(R.id.hand_yellow_card);
        yellowCount = v.findViewById(R.id.hand_yellow_count);

        wildCard = v.findViewById(R.id.hand_wild_card);
        wildCount = v.findViewById(R.id.hand_wild_count);

        fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fadein);
        fadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.fadeout);

        // Inflate the layout for this fragment
        return v;
    }

    //Redraw hand
    public void onHandUpdated(ArrayList<TrainCard> cards){

        int nblk = 0, nblu = 0, ngre = 0, nora = 0, npur = 0, nred = 0, nwhi = 0, nyel = 0, nwil = 0;



        //Find out how many of each color
        for (TrainCard card : cards){
            switch (card.getColor()){
                case "black":   nblk++; break;
                case "blue":    nblu++; break;
                case "green":   ngre++; break;
                case "orange":  nora++; break;
                case "purple":  npur++; break;
                case "red":     nred++; break;
                case "white":   nwhi++; break;
                case "yellow":  nyel++; break;
                case "wild":    nwil++; break;
                default:                break;
            }
        }

        //  If there are no cards of that color in the hand:
        //      grey the card out and set the count to 0

        //  Otherwise, reset opacity and display count
        if (nblk == 0){
            //If count isn't currently 0
            if (blackCount.getText() == null || !(blackCount.getText().equals("0"))){
                blackCard.startAnimation(fadeOut);
                blackCount.startAnimation(fadeOut);
                blackCount.setText("0");
                blackCard.setAlpha(0.4f);
                blackCount.setAlpha(0.4f);
            }
        } else {
            //If count used to be 0
            if (blackCount.getText() != null && blackCount.getText().equals("0")){
                blackCard.startAnimation(fadeIn);
                blackCount.startAnimation(fadeIn);
                blackCount.setText(Integer.toString(nblk));
                blackCard.setAlpha(1f);
                blackCount.setAlpha(1f);
            }
            //If count changed
            else if (blackCount.getText() != null && !(blackCount.getText().equals(Integer.toString(nblk)))) {
                blackCard.startAnimation(fadeOut);
                blackCount.startAnimation(fadeOut);
                blackCard.startAnimation(fadeIn);
                blackCount.startAnimation(fadeIn);
                blackCount.setText(Integer.toString(nblk));
            }

        }

        if (nblu == 0){
            blueCard.setAlpha(0.4f);
            blueCount.setText("0");
            blueCount.setAlpha(0.4f);
        } else {
            blueCard.setAlpha(1f);
            blueCount.setText(Integer.toString(nblu));
            blueCount.setAlpha(1f);
        }

        if (ngre == 0){
            greenCard.setAlpha(0.4f);
            greenCount.setText("0");
            greenCount.setAlpha(0.4f);
        } else {
            greenCard.setAlpha(1f);
            greenCount.setText(Integer.toString(ngre));
            greenCount.setAlpha(1f);
        }

        if (nora == 0){
            orangeCard.setAlpha(0.4f);
            orangeCount.setText("0");
            orangeCount.setAlpha(0.4f);
        } else {
            orangeCard.setAlpha(1f);
            orangeCount.setText(Integer.toString(nora));
            orangeCount.setAlpha(1f);
        }

        if (npur == 0){
            purpleCard.setAlpha(0.4f);
            purpleCount.setText("0");
            purpleCount.setAlpha(0.4f);
        } else {
            purpleCard.setAlpha(1f);
            purpleCount.setText(Integer.toString(npur));
            purpleCount.setAlpha(1f);
        }

        if (nred == 0){
            redCard.setAlpha(0.4f);
            redCount.setText("0");
            redCount.setAlpha(0.4f);
        } else {
            redCard.setAlpha(1f);
            redCount.setText(Integer.toString(nred));
            redCount.setAlpha(1f);
        }

        if (nwhi == 0) {
            whiteCard.setAlpha(0.4f);
            whiteCount.setText("0");
            whiteCount.setAlpha(0.4f);
        } else {
            whiteCard.setAlpha(1f);
            whiteCount.setText(Integer.toString(nwhi));
            whiteCount.setAlpha(1f);
        }

        if (nyel == 0) {
            yellowCard.setAlpha(0.4f);
            yellowCount.setText("0");
            yellowCard.setAlpha(0.4f);
        } else {
            yellowCard.setAlpha(1f);
            yellowCount.setText(Integer.toString(nyel));
            yellowCard.setAlpha(1f);
        }

        if (nwil == 0) {
            wildCard.setAlpha(0.4f);
            wildCount.setText("0");
            wildCount.setAlpha(0.4f);
        } else {
            wildCard.setAlpha(1f);
            wildCount.setText(Integer.toString(nwil));
            wildCount.setAlpha(1f);
        }


    }

}
