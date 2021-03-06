package cs340.ui.fragments.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cs340.shared.model.DestinationCard;
import cs340.shared.model.Player;
import cs340.ui.R;

/**
 * Created by sam on 2/7/18.
 */

public class DestinationCardSelectionAdapter extends RecyclerView.Adapter<DestinationCardSelectionAdapter.ViewHolder> {

    private Player _currentPlayer;
    private ArrayList<DestinationCard> _cards;
    private Context _context;
    private ArrayList<DestinationCard> _selectedCards;

    public ArrayList<DestinationCard> getSelectedCards(){
        return _selectedCards;
    }

    public ArrayList<DestinationCard> getUnselectedCards(){
        ArrayList<DestinationCard> unSelectedCards = new ArrayList<>();
        //Loop through cards
        for (DestinationCard card : _cards){
            //If card hasn't been selected, add it to the array
            if (!(_selectedCards.contains(card))){
                unSelectedCards.add(card);
            }
        }

        return unSelectedCards;
    }

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View layout;

        //public TextView gameNum;
        public TextView destinationFrom;
        public TextView destinationTo;
        public ImageView check;
        public RelativeLayout relativeLayout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            destinationFrom = v.findViewById(R.id.destination_list_item_from);
            destinationTo = v.findViewById(R.id.destination_list_item_to);
            check = v.findViewById(R.id.destcard_check);
            relativeLayout = v.findViewById(R.id.destcard_relativelayout);
        }
    }


    public DestinationCardSelectionAdapter(Player currentPlayer, ArrayList<DestinationCard> newCards, CardSelectionListener listener, Context context) {
        _currentPlayer = currentPlayer;
        _context = context;
        _cards = newCards;
        _selectedCards = new ArrayList<>();
        _listener = listener;
    }

    //Create new views
    @Override
    public DestinationCardSelectionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.destination_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    private CardSelectionListener _listener;
    public interface CardSelectionListener{
        void onCardSelected();
        void onCardDeselected();
    }

    //Replace contents of a view
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final DestinationCard card = _cards.get(position);

        final ViewHolder finalHolder = holder;

        holder.destinationTo.setText(card.getEndPoint());
        holder.destinationFrom.setText(card.getStartPoint());
        holder.check.setVisibility(View.INVISIBLE);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Selected: " + finalHolder.destinationFrom + " " + finalHolder.destinationTo);
                //Was it previously selected?
                if (finalHolder.check.getVisibility() == View.VISIBLE){
                    _selectedCards.remove(card);
                    _listener.onCardDeselected();
                    finalHolder.check.setVisibility(View.INVISIBLE);
                } else {
                    _listener.onCardSelected();
                    _selectedCards.add(card);
                    finalHolder.check.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    //Return size of dataset
    @Override
    public int getItemCount() {
        return _cards.size();
    }


}
