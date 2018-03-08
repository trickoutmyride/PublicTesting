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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import cs340.ui.R;
import cs340.ui.activities.GameActivity;
import cs340.ui.fragments.adapters.ChatListAdapter;

public class ChatFragment extends DialogFragment {

    private ChatListAdapter chatListAdapter;
    private RecyclerView chatList;
    private ArrayList<String> currentChat;
    private Button chatSubmit;
    private EditText chatInput;
    private String chatMessage;
    private ChatFragmentListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        currentChat = this.getArguments().getStringArrayList("chat");
        //Get layout inflater:
        LayoutInflater inflater = getActivity().getLayoutInflater();
        //Inflate and set layout for dialog
        builder.setView(inflater.inflate(R.layout.chat_dialog_layout, null)).setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ChatFragment.this.getDialog().dismiss();
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
        }

        chatSubmit = d.findViewById(R.id.chat_submit);
        chatInput = d.findViewById(R.id.chat_input);

        chatSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Only do something if there is text input
                if (chatMessage != null){
                    //SEND MESSAGE
                    listener.sendMessage(chatMessage);
                    //Clear chat input
                    chatInput.setText("");
                    chatMessage = null;
                }
            }
        });

        //Watch for changes in the chat input
        chatInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() != 0) {
                    chatMessage = s.toString();
                }
                else {
                    chatMessage = null;
                }
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        });

        //Initialize the chat list
        chatList = d.findViewById(R.id.chat_list);
        chatList.setLayoutManager(new LinearLayoutManager(getContext()));

        if (currentChat != null){
            chatListAdapter = new ChatListAdapter(currentChat, getContext());
            chatList.setAdapter(chatListAdapter);
        }
        else {
            System.out.println("Null");
        }
    }

    //Update chat list
    public void onChatUpdated(String newMessage){
        this.currentChat.add(newMessage);
        chatListAdapter = new ChatListAdapter(currentChat, getContext());
        chatList.setAdapter(chatListAdapter);
    }


    public interface ChatFragmentListener {
        void sendMessage(String message);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        //Attach GamePresenter as a listener
        GameActivity activity = (GameActivity) getActivity();
        try {
            listener = (ChatFragment.ChatFragmentListener) activity.getGamePresenter();
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement ChatFragmentListener");
        }
    }

}
