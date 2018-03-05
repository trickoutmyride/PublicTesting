package cs340.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cs340.shared.model.Game;
import cs340.shared.model.Player;
import cs340.ui.R;
import cs340.ui.activities.GameActivity;
import cs340.ui.presenters.IPlayersPresenter;
import cs340.ui.presenters.PlayersPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayersFragment extends Fragment implements IPlayersFragment {

    //TODO: Train car counts, player points

    private int playerCount;

    //Player names, counts
    private TextView playerName1, playerName2, playerName3, playerName4, playerName5;
    private TextView destCardCount1, destCardCount2, destCardCount3, destCardCount4, destCardCount5;
    private TextView trainCardCount1, trainCardCount2, trainCardCount3, trainCardCount4, trainCardCount5;
    private TextView carCount1, carCount2, carCount3, carCount4, carCount5;
    private TextView points1, points2, points3, points4, points5;

    //LinearLayouts for player color display, ImageView for current player display
    private LinearLayout player1Title, player2Title, player3Title, player4Title, player5Title;
    private ImageView pointsIcon1, pointsIcon2, pointsIcon3, pointsIcon4, pointsIcon5;

    //RelativeLayouts for disabling unused players
    private RelativeLayout player1Info, player2Info, player3Info, player4Info, player5Info;

    private IPlayersPresenter playersPresenter;
    private Player currentPlayer;
    private Game currentGame;


    public PlayersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inflate layout
        View v = inflater.inflate(R.layout.fragment_players, container, false);

        //Get presenter, current player
        playersPresenter = new PlayersPresenter(this);

        //Get names
        playerName1 = v.findViewById(R.id.player_name_1);
        playerName2 = v.findViewById(R.id.player_name_2);
        playerName3 = v.findViewById(R.id.player_name_3);
        playerName4 = v.findViewById(R.id.player_name_4);
        playerName5 = v.findViewById(R.id.player_name_5);

        //Get dest card counts
        destCardCount1 = v.findViewById(R.id.destcard_count_1);
        destCardCount2 = v.findViewById(R.id.destcard_count_2);
        destCardCount3 = v.findViewById(R.id.destcard_count_3);
        destCardCount4 = v.findViewById(R.id.destcard_count_4);
        destCardCount5 = v.findViewById(R.id.destcard_count_5);

        //Get train card counts
        trainCardCount1 = v.findViewById(R.id.traincard_count_1);
        trainCardCount2 = v.findViewById(R.id.traincard_count_2);
        trainCardCount3 = v.findViewById(R.id.traincard_count_3);
        trainCardCount4 = v.findViewById(R.id.traincard_count_4);
        trainCardCount5 = v.findViewById(R.id.traincard_count_5);

        //Get car counts
        carCount1 = v.findViewById(R.id.car_count_1);
        carCount2 = v.findViewById(R.id.car_count_2);
        carCount3 = v.findViewById(R.id.car_count_3);
        carCount4 = v.findViewById(R.id.car_count_4);
        carCount5 = v.findViewById(R.id.car_count_5);

        //Get points
        points1 = v.findViewById(R.id.points_count_1);
        points2 = v.findViewById(R.id.points_count_2);
        points3 = v.findViewById(R.id.points_count_3);
        points4 = v.findViewById(R.id.points_count_4);
        points5 = v.findViewById(R.id.points_count_5);

        //Get LinearLayouts
        player1Title = v.findViewById(R.id.player_1_name);
        player2Title = v.findViewById(R.id.player_2_name);
        player3Title = v.findViewById(R.id.player_3_name);
        player4Title = v.findViewById(R.id.player_4_name);
        player5Title = v.findViewById(R.id.player_5_name);

        //Get points icons
        pointsIcon1 = v.findViewById(R.id.points_icon_1);
        pointsIcon2 = v.findViewById(R.id.points_icon_2);
        pointsIcon3 = v.findViewById(R.id.points_icon_3);
        pointsIcon4 = v.findViewById(R.id.points_icon_4);
        pointsIcon5 = v.findViewById(R.id.points_icon_5);

        //Get RelativeLayouts
        player1Info = v.findViewById(R.id.player_1_info);
        player2Info = v.findViewById(R.id.player_2_info);
        player3Info = v.findViewById(R.id.player_3_info);
        player4Info = v.findViewById(R.id.player_4_info);
        player5Info = v.findViewById(R.id.player_5_info);

        // Return inflated layout
        return v;
    }

    @Override
    public void onPlayerUpdated(Player player) {
        int playerNumber = 0;

        //Get number of player
        for (int i = 0; i < currentGame.getPlayers().size(); i++){
            if (player.getUsername().equals(currentGame.getPlayers().get(i).getUsername())){
                playerNumber = i;
            }
        }

        switch(playerNumber) {
            case 0:
                destCardCount1.setText(player.getDestinations().size());
                trainCardCount1.setText(player.getCards().size());
                break;
            case 1:
                destCardCount2.setText(player.getDestinations().size());
                trainCardCount2.setText(player.getCards().size());
                break;
            case 2:
                destCardCount3.setText(player.getDestinations().size());
                trainCardCount3.setText(player.getCards().size());
                break;
            case 3:
                destCardCount4.setText(player.getDestinations().size());
                trainCardCount4.setText(player.getCards().size());
                break;
            case 4:
                destCardCount5.setText(player.getDestinations().size());
                trainCardCount5.setText(player.getCards().size());
                break;
        }
    }

    //Set background color and current player icon
    @Override
    public void initiatePlayers(ArrayList<Player> players) {
        playerCount = players.size();
        currentPlayer = ((GameActivity)getActivity()).getCurrentPlayer();
        currentGame = ((GameActivity)getActivity()).getCurrentGame();

        boolean onCurrentPlayer = false;
        for (int i = 0; i < playerCount; i++){
            //If we've found the current player
            if (players.get(i).getUsername().equals(currentPlayer.getUsername())){
                onCurrentPlayer = true;
            } else {
                onCurrentPlayer = false;
            }

            switch(i) {
                case 0:
                    if (onCurrentPlayer){
                        //Player1 is current player
                        pointsIcon1.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.your_points, null));
                    }
                    else {
                        pointsIcon1.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.points_icon_white, null));
                    }
                    playerName1.setText(players.get(i).getUsername());
                    player1Title.setBackgroundColor(colorStringtoInt(currentGame.getColors().get(players.get(i).getUsername())));
                    break;
                case 1:
                    if (onCurrentPlayer){
                        //Player2 is current player
                        pointsIcon2.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.your_points, null));
                    }
                    else {
                        pointsIcon2.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.points_icon_white, null));
                    }
                    playerName2.setText(players.get(i).getUsername());
                    player2Title.setBackgroundColor(colorStringtoInt(currentGame.getColors().get(players.get(i).getUsername())));
                    break;
                case 2:
                    if (onCurrentPlayer){
                        //Player3 is current player
                        pointsIcon3.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.your_points, null));
                    }
                    else {
                        pointsIcon3.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.points_icon_white, null));
                    }
                    playerName3.setText(players.get(i).getUsername());
                    player3Title.setBackgroundColor(colorStringtoInt(currentGame.getColors().get(players.get(i).getUsername())));
                    break;
                case 3:
                    if (onCurrentPlayer){
                        //Player4 is current player
                        pointsIcon4.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.your_points, null));
                    }
                    else {
                        pointsIcon4.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.points_icon_white, null));
                    }
                    playerName4.setText(players.get(i).getUsername());
                    player4Title.setBackgroundColor(colorStringtoInt(currentGame.getColors().get(players.get(i).getUsername())));
                    break;
                case 4:
                    if (onCurrentPlayer){
                        //Player5 is current player
                        pointsIcon5.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.your_points, null));
                    }
                    else {
                        pointsIcon5.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.points_icon_white, null));
                    }
                    playerName5.setText(players.get(i).getUsername());
                    player5Title.setBackgroundColor(colorStringtoInt(currentGame.getColors().get(players.get(i).getUsername())));
                    break;
            }
        }

        //Disable unused players
        disableUnused();
    }

    public void disableUnused(){
        switch (playerCount) {
            case 2:
                //disable 3-5
                player3Info.setVisibility(View.GONE);
                player4Info.setVisibility(View.GONE);
                player5Info.setVisibility(View.GONE);
                break;
            case 3:
                //disable 4-5
                player4Info.setVisibility(View.GONE);
                player5Info.setVisibility(View.GONE);
                break;
            case 4:
                //disable 5
                player5Info.setVisibility(View.GONE);
                break;
        }
    }


    public int colorStringtoInt(String color){
        switch (color) {
            case "blue":
                return Color.BLUE;
            case "red":
                return Color.RED;
            case "green":
                return Color.GREEN;
            case "yellow":
                return Color.YELLOW;
            case "orange":
                return Color.rgb(100, 73, 20);
            default:
                return Color.rgb(0, 0, 0);
        }
    }
}
