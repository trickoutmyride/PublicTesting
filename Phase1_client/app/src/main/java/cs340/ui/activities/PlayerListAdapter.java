package cs340.ui.activities;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import cs340.shared.model.Player;
import cs340.ui.R;

/**
 * Created by sam on 2/7/18.
 */

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.ViewHolder> {

    private ArrayList<Player> _playerList;
    private Context _context;
    private Player _currentPlayer;
    private HashMap<String, String> _playerColors;

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View layout;

        public TextView playerName;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            playerName = v.findViewById(R.id.player_name);
        }
    }

    public PlayerListAdapter(ArrayList<Player> playerList, Context context, Player currentPlayer, HashMap<String, String> playerColors) {
        _playerList = playerList;
        _context = context;
        _currentPlayer = currentPlayer;
        _playerColors = playerColors;
    }

    //Create new views
    @Override
    public PlayerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.lobby_player_list, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //Replace contents of a view
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Player player = _playerList.get(position);

        //If player == currentPlayer, add (you) to the end.
        String playerNameText;
        playerNameText = player.getUsername();
        if (_currentPlayer.getUsername().equals(player.getUsername())) {
            playerNameText = playerNameText + " (you)";
        }
        holder.playerName.setText(playerNameText);

        System.out.println("playerNameText: " + playerNameText);
        //Set player color
        System.out.println(Integer.toString(colorToID(_playerColors.get(player.getUsername()))));
        System.out.println(_playerColors.get(player.getUsername()));
        //holder.playerName.setTextColor(colorToID(_playerColors.get(player.getUsername())));
        //holder.playerName.setTextColor(().getResources().getColor(android.R.color.black));
    }

    //Return size of dataset
    @Override
    public int getItemCount() {
        return _playerList.size();
    }

    //Get color int from string
    public int colorToID(String color){
        switch(color) {
            case "blue": return android.R.color.holo_blue_dark;
            case "red": return android.R.color.holo_red_dark;
            case "green": return android.R.color.holo_green_dark;
            case "yellow": return Color.YELLOW;
            case "black": return android.R.color.black;
            default: return 0;
        }
    }

}