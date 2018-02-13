package cs340.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import cs340.shared.model.Player;
import cs340.ui.R;
import cs340.ui.presenters.ILoginPresenter;
import cs340.ui.presenters.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements ILoginActivity {

    //View fields
    private RadioButton loginRadio;
    private RadioGroup login_registerRadioGroup;
    private EditText usernameField, passwordField, confirmField;
    private Button submitButton;

    //User information, options
    private Boolean registerMode;
    private Boolean loginMode;
    private String username, password, confirm;

    //Presenter
    private ILoginPresenter loginPresenter;

    /**
     * onCreate
     *
     * Purpose:
     * Initializes the Activity. Sets state of radio buttons, adds radio/text listeners
     *
     * Functionality:
     * Adds listeners for username, confirm, password fields and login, register radio buttons
     * Adds listener for the submit button
     * Verifies user input
     *      username, password fields are not null
     *      confirm field matches password field (for register)
     * When user input is valid, calls login or register on the presenter
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);





        /**
         *
         *
         *
         *  Temporary override of network UI thread locking for testing:
         *
         *
         */
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);




        




        MultiDex.install(this);

        //MOCK LOGIN PRESENTER
        //loginPresenter = new MockLoginPresenter(this);

        //Setup Login Presenter
        loginPresenter = new LoginPresenter(this);


        //Grab radio group, buttons, editTexts, etc.
        loginRadio = findViewById(R.id.login_radio);
        login_registerRadioGroup = findViewById(R.id.login_registerGroup);
        usernameField = findViewById(R.id.username_field);
        passwordField = findViewById(R.id.password_field);
        confirmField = findViewById(R.id.confirm_field);
        submitButton = findViewById(R.id.submit_button);

        //Initialize radio state
        confirmField.setEnabled(false);
        confirmField.setAlpha(0.5f);
        loginRadio.setChecked(true);
        loginMode = true;
        registerMode = false;

        //Update username, password, confirm fields after user input
        usernameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() != 0) {
                    username = s.toString();
                }
                else {
                    username = null;
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

        });

        confirmField.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() != 0) {
                    confirm = s.toString();
                }
                else {
                    confirm = null;
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        passwordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() != 0) {
                    password = s.toString();
                }
                else {
                    password = null;
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });


        //Enable/disable login/confirm mode
        login_registerRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.login_radio) {
                    //Set login variable, disable confirm field
                    loginMode = true;
                    registerMode = false;
                    confirmField.setEnabled(false);
                    confirmField.setAlpha(0.5f);
                }
                if (checkedId == R.id.register_radio) {
                    //Set register variable, enable confirm field
                    registerMode = true;
                    loginMode = false;
                    confirmField.setEnabled(true);
                    confirmField.setAlpha(1f);
                }
            }
        });

        submitButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //User is attempting to register
                if (registerMode) {
                    if (username == null) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Username field required.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else if (password == null) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Password field required.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else if (confirm == null) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Confirm field required.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else if (!password.equals(confirm)) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Passwords do not match.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else {
                        //Pass username and password to the Presenter for Register
                        loginPresenter.register(username, password);

                        /*
                        new AsyncTask<Void, Void, Void>() {
                            protected void onPreExecute() {
                                // Pre Code
                            }
                            protected Void doInBackground(Void... unused) {
                                loginPresenter.register(username, password);
                                return null;
                            }
                            protected void onPostExecute(Void unused) {
                                // Post Code
                            }
                        }.execute();
                        */
                    }
                }
                //User is attempting to login
                else if (loginMode) {
                    if (username == null) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Username field required.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else if (password == null) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Password field required.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else {
                        //Pass username and password to the Presenter for Login
                        loginPresenter.login(username,password);

                        /*
                        new AsyncTask<Void, Void, Void>() {
                            protected void onPreExecute() {
                                // Pre Code
                            }
                            protected Void doInBackground(Void... unused) {
                                loginPresenter.login(username, password);
                                return null;
                            }
                            protected void onPostExecute(Void unused) {
                                // Post Code
                            }
                        }.execute();
                        */
                    }
                }
            }
        });
    }

    /* Presenter Calls The Following Methods */

    /**
     * onLogin
     *
     * Purpose:
     * Called by LoginPresenter to let the Activity know that the player login was successful
     *
     * Functionality:
     * Passes the logged in player to the PreGameActivity and starts the activity
     *
     * @param currentPlayer currently logged in player
     */
    public void onLogin(Player currentPlayer) {
        Intent intent = new Intent(this, PreGameActivity.class);
        Gson gson = new Gson();
        intent.putExtra("currentPlayer", gson.toJson(currentPlayer));
        startActivity(intent);
    }

    /**
     * onError
     *
     * Purpose:
     * Called by LoginPresenter to let the Activity know that the player login or register was unsuccessful
     *
     * Functionality:
     * Displays an Android Toast to the user containing the error message from the server
     *
     * @param msg error message from the server
     */
    public void onError(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }
}
