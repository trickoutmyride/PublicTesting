package cs340.ui.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import cs340.ui.R;

/**
 * Created by sam on 2/8/18.
 */

public class CreateGameDialogFragment extends DialogFragment {

    private String newGameColor;
    private int newGameCapacity;
    private String newGameName;

    public String getNewGameColor() {
        return newGameColor;
    }

    public String getNewGameName() {
        return newGameName;
    }

    public int getNewGameCapacity() {
        return newGameCapacity;
    }

    public void setNewGameColor(String color){
        newGameColor = color;
    }

    public void setNewGameCapacity(int capacity) {
        newGameCapacity = capacity;
    }

    public void setNewGameName(String name) {
        newGameName = name;
    }

    public interface CreateGameDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
    }
    CreateGameDialogListener listener;

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
                        //Send positive button click back to the activity
                        if (newGameName == null || newGameName.length() == 0) {
                            Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Name field required.", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else {
                            listener.onDialogPositiveClick(CreateGameDialogFragment.this);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Cancel up in here
                        CreateGameDialogFragment.this.getDialog().cancel();
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
            listener = (CreateGameDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement CreateGameDialogListener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        //Setup editText for game name
        EditText et = (EditText) getDialog().findViewById(R.id.new_game_name);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //stub
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //stub
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Set game name
                if (editable.toString().length() != 0) {
                    setNewGameName(editable.toString());
                }
            }
        });

        //Setup capacity picker
        NumberPicker np = (NumberPicker) getDialog().findViewById(R.id.capacity_picker);
        np.setMinValue(2);
        np.setMaxValue(5);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                setNewGameCapacity(newVal);
            }
        });

        //Setup color spinner
        Spinner spin = (Spinner) getDialog().findViewById(R.id.player_color_spinner);
        ArrayAdapter<CharSequence> spinAdapter = ArrayAdapter.createFromResource(getDialog().getContext(),
                R.array.colors_array, android.R.layout.simple_spinner_item);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(spinAdapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String color = (String) adapterView.getItemAtPosition(i);
                setNewGameColor(color);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //stub
            }
        });
    }
}







