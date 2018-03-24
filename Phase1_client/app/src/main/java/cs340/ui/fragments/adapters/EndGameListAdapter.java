package cs340.ui.fragments.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import cs340.shared.model.DestinationCard;
import cs340.shared.model.Game;
import cs340.shared.model.Player;
import cs340.ui.R;

public class EndGameListAdapter extends RecyclerView.Adapter<EndGameListAdapter.ViewHolder> {

    private ArrayList<Player> _players;
    private Game _game;
    private Context _context;

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View layout;
        public TextView username;
        public TextView points;
        public TextView routePoints;
        public TextView longestTrainYN;
        public TextView longestTrainPoints;
        public TextView destFinishedPoints;
        public TextView destFailedPoints;


        public ViewHolder(View v) {
            super(v);
            layout = v;
            username = v.findViewById(R.id.end_game_list_item_username);
            points = v.findViewById(R.id.end_game_list_item_points);
            routePoints = v.findViewById(R.id.end_game_list_item_routes_points);
            longestTrainYN = v.findViewById(R.id.end_game_list_item_longest_yn);
            longestTrainPoints = v.findViewById(R.id.end_game_list_item_longest_points);
            destFailedPoints = v.findViewById(R.id.end_game_list_item_dest_failed_points);
            destFinishedPoints = v.findViewById(R.id.end_game_list_item_dest_finished_points);
        }
    }


    public EndGameListAdapter(Context context, ArrayList<Player> players, Game game) {
        _context = context;
        _players = players;
        _game = game;
    }

    //Create new views
    @Override
    public EndGameListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.end_game_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //Replace contents of a view
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.username.setText(_players.get(position).getUsername());
        holder.points.setText(String.valueOf(_players.get(position).getPoints()));
        holder.routePoints.setText(String.valueOf(_players.get(position).getClaimedRoutePoints()));

        //This player has the longest road
        if (_game.getLongestRoadIndex() == position) {
            holder.longestTrainYN.setText("Yes: ");
            holder.longestTrainYN.setVisibility(View.VISIBLE);
            holder.longestTrainPoints.setText(String.valueOf(_players.get(position).getLongestRoutePoints()));
        }
        else {
            holder.longestTrainYN.setText("No");
        }

        holder.destFinishedPoints.setText(String.valueOf(_players.get(position).getReachedDestinationPoints()));
        holder.destFailedPoints.setText(String.valueOf(_players.get(position).getUnreachedDestinationPoints()));
    }

    //Return size of dataset
    @Override
    public int getItemCount() {
        return _players.size();
    }


}
