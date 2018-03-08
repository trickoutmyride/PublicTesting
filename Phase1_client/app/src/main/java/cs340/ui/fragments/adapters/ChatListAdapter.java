package cs340.ui.fragments.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cs340.ui.R;

/**
 * Created by sam on 2/7/18.
 */

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {

    private ArrayList<String> _chatList;
    private Context _context;

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View layout;
        public TextView chatItem;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            chatItem = v.findViewById(R.id.chat_item);
        }
    }

    public ChatListAdapter(ArrayList<String> chatList, Context context) {
        _chatList = chatList;
        _context = context;
    }

    //Create new views
    @Override
    public ChatListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.chat_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //Replace contents of a view
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        System.out.println("OnBindViewHolder " + position);
        String item = _chatList.get(position);
        holder.chatItem.setText(item);
    }

    //Return size of dataset
    @Override
    public int getItemCount() {
        return _chatList.size();
    }


}
