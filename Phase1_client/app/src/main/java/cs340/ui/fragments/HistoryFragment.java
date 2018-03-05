package cs340.ui.fragments;

import cs340.ui.R;
import cs340.ui.activities.CreateGameDialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.util.ArrayList;

public class HistoryFragment extends DialogFragment {

    private HistoryListAdapter historyListAdapter;
    private RecyclerView historyList;
    private ArrayList<String> currentHistory;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        currentHistory = this.getArguments().getStringArrayList("history");
        //Get layout inflater:
        LayoutInflater inflater = getActivity().getLayoutInflater();
        //Inflate and set layout for dialog
        builder.setView(inflater.inflate(R.layout.history_dialog_layout, null)).setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HistoryFragment.this.getDialog().dismiss();
            }
        });
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();

        AlertDialog d = (AlertDialog)getDialog();
        if (d != null) {
            d.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
            d.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        }

        historyList = d.findViewById(R.id.historyList);
        historyList.setLayoutManager(new LinearLayoutManager(getContext()));

        historyListAdapter = new HistoryListAdapter(currentHistory, getContext());
        historyList.setAdapter(historyListAdapter);

    }

}
