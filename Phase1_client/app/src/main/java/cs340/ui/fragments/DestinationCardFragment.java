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
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

import cs340.shared.model.DestinationCard;
import cs340.shared.model.Player;
import cs340.ui.R;
import cs340.ui.fragments.adapters.DestinationCardDisplayAdapter;
import cs340.ui.fragments.adapters.DestinationCardSelectionAdapter;
import cs340.ui.fragments.adapters.HistoryListAdapter;

public class DestinationCardFragment extends DialogFragment implements DestinationCardSelectionAdapter.CardSelectionListener {

    private DestinationCardDisplayAdapter destinationCardDisplayAdapter;
    private DestinationCardSelectionAdapter destinationCardSelectionAdapter;
    private RecyclerView destinationCardList;
    private int checked;
    private Button drawButton;
    private ArrayList<DestinationCard> newCards;
    private boolean gameStarted;

    //If this is true, we are in selection mode. Else, we are in display mode.
    private boolean selection;

    private HistoryListAdapter historyListAdapter;
    private RecyclerView historyList;
    private ArrayList<String> currentHistory;
    private Player player;

    public boolean getGameStarted(){ return gameStarted; }

    public DestinationCardSelectionAdapter getDestinationCardSelectionAdapter(){
        return destinationCardSelectionAdapter;
    }

    public interface DestinationCardDialogListener{
        public void onDialogPositiveClick(DialogFragment dialog);
        void onDrawNewDestinationCardsSelected();
    }
    DestinationCardDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        checked = 0;

        //Get current player, selection
        Gson gson = new Gson();
        player = gson.fromJson(this.getArguments().getString("player"), Player.class);
        selection = this.getArguments().getBoolean("selection");
        gameStarted = this.getArguments().getBoolean("gameStarted");

        if (selection) {
            final Type type = new TypeToken<ArrayList<DestinationCard>>(){}.getType();
            newCards = gson.fromJson(this.getArguments().getString("newCards"), type);
        }

        //Get layout inflater:
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //Selection Mode: Set up Submit and Cancel buttons
        //Display Mode: Set up Close button

        Dialog alertDialog;
        if (selection) {
            builder.setView(inflater.inflate(R.layout.destination_dialog_layout, null)).setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    listener.onDialogPositiveClick(DestinationCardFragment.this);
                    DestinationCardFragment.this.getDialog().dismiss();
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

        final AlertDialog d = (AlertDialog)getDialog();
        d.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        if (selection) {
            d.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
            ((AlertDialog)d).getButton(AlertDialog.BUTTON_NEGATIVE).setEnabled(false);
            ((AlertDialog)d).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        }else {
            ((AlertDialog)d).getButton(AlertDialog.BUTTON_NEGATIVE).setEnabled(false);
            ((AlertDialog)d).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
        }

        destinationCardList = d.findViewById(R.id.destination_cards_list);
        destinationCardList.setLayoutManager(new LinearLayoutManager(getContext()));

        if (selection) {
            destinationCardSelectionAdapter = new DestinationCardSelectionAdapter(player, newCards, this, getContext());
            destinationCardList.setAdapter(destinationCardSelectionAdapter);
        }else{
            destinationCardDisplayAdapter = new DestinationCardDisplayAdapter(player, getContext());
            destinationCardList.setAdapter(destinationCardDisplayAdapter);
        }

        //If display mode, display selection button
        if (!selection){
            drawButton = d.findViewById(R.id.draw_destination_button);
            drawButton.setVisibility(View.VISIBLE);
            drawButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onDrawNewDestinationCardsSelected();
                    d.dismiss();
                }
            });
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

    @Override
    public void onCardSelected(){
        if (selection) {
            checked++;
            //Enable confirm button
            if (!gameStarted) {
                if (checked >= 2) {
                    AlertDialog d = (AlertDialog) getDialog();
                    d.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                }
            }
            else {
                if (checked >= 1) {
                    AlertDialog d = (AlertDialog) getDialog();
                    d.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                }
            }
        }
    }

    @Override
    public void onCardDeselected(){
        if (selection) {
            checked--;
            //Disable confirm button
            if (!gameStarted) {
                if (checked < 2) {
                    AlertDialog d = (AlertDialog) getDialog();
                    d.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
            }
            else{
                if (checked < 1) {
                    AlertDialog d = (AlertDialog) getDialog();
                    d.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
            }
        }
    }
}
