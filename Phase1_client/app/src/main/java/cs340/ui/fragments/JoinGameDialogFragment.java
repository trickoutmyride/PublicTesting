package cs340.ui.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import cs340.ui.R;
import cs340.ui.activities.PreGameActivity;

/**
 * Created by sam on 2/8/18.
 */

public class JoinGameDialogFragment extends DialogFragment {

    private String playerColor;
    public String getPlayerColor() {
        return playerColor;
    }
    public void setPlayerColor(String color){
        playerColor = color;
    }

    public interface JoinGameDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
    }
    JoinGameDialogListener listener;

    private View _view;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Get layout inflater:
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //Inflate and set layout for dialog
        builder.setView(inflater.inflate(R.layout.joingame_dialog_layout, null))
                .setPositiveButton("Join Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            listener.onDialogPositiveClick(JoinGameDialogFragment.this);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Cancel up in here
                        JoinGameDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //Attach activity as a listener (for dialog confirm button)
        Activity activity = getActivity();
        try {
            listener = (JoinGameDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement JoinGameDialogListener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        AlertDialog d = (AlertDialog)getDialog();
        if (d != null) {
            d.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
            d.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        }

        setSpinner(((PreGameActivity)getActivity()).getJoinGame().getRemainingColors());
    }

    public void setSpinner(ArrayList<String> colors) {

        System.out.println("Set Spinner!");

        if (colors == null){
            System.out.println("NULL");
        }

        //Setup color spinner
        Spinner spin = (Spinner) getDialog().findViewById(R.id.player_color_spinner_2);

        for (int i = 0; i < colors.size(); i++) {
            System.out.println("Color: " + colors.get(i));
        }

        ArrayAdapter<String> spinArrayAdapter = new ArrayAdapter<>(getDialog().getContext(), android.R.layout.simple_spinner_item, colors);
        spinArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(spinArrayAdapter);
        spin.setEnabled(true);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String color = (String) adapterView.getItemAtPosition(i);
                setPlayerColor(color);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //stub
            }
        });

    }
}







