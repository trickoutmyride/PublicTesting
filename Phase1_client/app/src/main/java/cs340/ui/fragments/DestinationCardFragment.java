package cs340.ui.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.google.gson.Gson;

import java.util.ArrayList;

import cs340.shared.model.Player;
import cs340.ui.R;

public class DestinationCardFragment extends DialogFragment {

    private DestinationCardDisplayAdapter destinationCardDisplayAdapter;
    private DestinationCardSelectionAdapter destinationCardSelectionAdapter;
    private RecyclerView destinationCardList;

    //If this is true, we are in selection mode. Else, we are in display mode.
    private boolean selection;

    private HistoryListAdapter historyListAdapter;
    private RecyclerView historyList;
    private ArrayList<String> currentHistory;
    private Player player;

    public DestinationCardSelectionAdapter getDestinationCardSelectionAdapter(){
        return destinationCardSelectionAdapter;
    }

    public interface DestinationCardDialogListener{
        public void onDialogPositiveClick(DialogFragment dialog);
    }
    DestinationCardDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Get current player, selection
        Gson gson = new Gson();
        player = gson.fromJson(this.getArguments().getString("player"), Player.class);
        selection = this.getArguments().getBoolean("selection");

        //Get layout inflater:
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //Selection Mode: Set up Submit and Cancel buttons
        //Display Mode: Set up Close button
        if (selection) {
            builder.setView(inflater.inflate(R.layout.destination_dialog_layout, null)).setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    listener.onDialogPositiveClick(DestinationCardFragment.this);
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    DestinationCardFragment.this.getDialog().dismiss();
                }
            });
        }
        else {
            builder.setView(inflater.inflate(R.layout.destination_dialog_layout, null)).setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    DestinationCardFragment.this.getDialog().dismiss();
                }
            });
        }

        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();

        AlertDialog d = (AlertDialog)getDialog();
        d.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        if (selection) {
            d.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        }

        destinationCardList = d.findViewById(R.id.destination_cards_list);
        destinationCardList.setLayoutManager(new LinearLayoutManager(getContext()));

        if (selection) {

        }else{
            destinationCardDisplayAdapter = new DestinationCardDisplayAdapter(player, getContext());
            destinationCardList.setAdapter(destinationCardDisplayAdapter);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //Attach activity as a listener (for dialog confirm button)
        Activity activity = getActivity();
        try {
            listener = (DestinationCardDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement DestinationCardDialogListener");
        }
    }
}
