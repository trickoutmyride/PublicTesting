package cs340.ui.fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cs340.shared.model.DestinationCard;
import cs340.shared.model.Player;
import cs340.ui.R;

/**
 * Created by sam on 2/7/18.
 */

public class DestinationCardDisplayAdapter extends RecyclerView.Adapter<DestinationCardDisplayAdapter.ViewHolder> {

    private Player _currentPlayer;
    private ArrayList<DestinationCard> _cards;
    private Context _context;

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View layout;

        //public TextView gameNum;
        public TextView destinationFrom;
        public TextView destinationTo;
        public ImageView check;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            destinationFrom = v.findViewById(R.id.destination_list_item_from);
            destinationTo = v.findViewById(R.id.destination_list_item_to);
            check = v.findViewById(R.id.destcard_check);
        }
    }

    public DestinationCardDisplayAdapter(Player currentPlayer, Context context) {
        _currentPlayer = currentPlayer;
        _context = context;
        _cards = currentPlayer.getDestinations();
    }

    //Create new views
    @Override
    public DestinationCardDisplayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.destination_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //Replace contents of a view
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final DestinationCard card = _cards.get(position);

        holder.destinationTo.setText(card.getEndPoint());
        holder.destinationFrom.setText(card.getStartPoint());

        //If completed
        //holder.check.setVisibility(View.VISIBLE);
    }

    //Return size of dataset
    @Override
    public int getItemCount() {
        return _cards.size();
    }


}
