package cs340.ui.fragments.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;

import cs340.shared.model.TrainCard;
import cs340.ui.R;
import cs340.ui.activities.GameActivity;
import cs340.ui.fragments.DeckFragment;

/**
 * Created by sam on 2/7/18.
 */

public class DeckCardAdapter extends RecyclerView.Adapter<DeckCardAdapter.ViewHolder> {

    private ArrayList<TrainCard> _cards;
    private Context _context;

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View layout;
        public ImageButton card;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            card = v.findViewById(R.id.face_up_card);
        }
    }

    public DeckCardAdapter(ArrayList<TrainCard> cards, Context context) {
        _context = context;
        _cards = cards;
    }

    //Create new views
    @Override
    public DeckCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.face_up_item_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //Replace contents of a view
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final int pos = position;

        //Get card, set imagebutton to card type
        final TrainCard card = _cards.get(position);
        holder.card.setImageResource(cardToDrawable(card));

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeckFragment df = (DeckFragment)((GameActivity)_context).getFragmentManager().findFragmentById(R.id.deckFragment);
                df.cardSelected(card);
                removeAt(pos);
            }
        });

    }

    public void removeAt(int position){
        _cards.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, _cards.size());
    }

    //Return size of dataset
    @Override
    public int getItemCount() {
        return _cards.size();
    }

    //Returns drawable ID based on card color
    public int cardToDrawable(TrainCard card){

        switch (card.getColor()){
            case "purple":
                return R.drawable.traincard_purple_landscape;
            case "white":
                return R.drawable.traincard_white_landscape;
            case "blue":
                return R.drawable.traincard_blue_landscape;
            case "yellow":
                return R.drawable.traincard_yellow_landscape;
            case "orange":
                return R.drawable.traincard_orange_landscape;
            case "black":
                return R.drawable.traincard_black_landscape;
            case "red":
                return R.drawable.traincard_red_landscape;
            case "green":
                return R.drawable.traincard_green_landscape;
            case "wild":
                return R.drawable.traincard_wild_landscape;
            default:
                return 0;
        }
    }

}
