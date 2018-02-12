package cs340.ui.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cs340.shared.model.Player;
import cs340.ui.R;

/**
 * Created by sam on 2/7/18.
 */

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.ViewHolder> {

    private ArrayList<Player> _playerList;
    private Context _context;

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

    public PlayerListAdapter(ArrayList<Player> playerList, Context context) {
        _playerList = playerList;
        _context = context;
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

        System.out.println("OnBindViewHolder " + position);
        final Player player = _playerList.get(position);
        holder.playerName.setText(player.getUsername());
        //Set player color
        //holder.playerName.setTextColor(player.getcolor());
    }

    //Return size of dataset
    @Override
    public int getItemCount() {
        return _playerList.size();
    }


}