package cs340.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

/**
 * Created by sam on 2/8/18.
 */

public class CreateGameDialogFragment extends DialogFragment {

    private View _view;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Get layout inflater:
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //Inflate and set layout for dialog
        builder.setView(inflater.inflate(R.layout.creategame_dialog_layout, null))
                .setPositiveButton("Create Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Create Game
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CreateGameDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
