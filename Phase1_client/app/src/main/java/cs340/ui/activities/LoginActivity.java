package cs340.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import cs340.ui.presenters.MockLoginPresenter;

public class LoginActivity extends AppCompatActivity implements ILoginActivity {

    RadioButton loginRadio;
    RadioButton registerRadio;
    RadioGroup login_registerRadioGroup;
    EditText usernameField, passwordField, confirmField;
    Button submitButton;
    Boolean registerMode;
    Boolean loginMode;
    String username, password, confirm;
    ILoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Setup Login Presenter

        loginPresenter = new MockLoginPresenter(this);

        //Grab radio group, buttons, editTexts, etc.
        loginRadio = findViewById(R.id.login_radio);
        registerRadio = findViewById(R.id.register_radio);
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
                if (registerMode) {
                    if (username == null) {
                        //Toast - enter password
                        Toast toast = Toast.makeText(getApplicationContext(), "Username field required.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else if (password == null) {
                        //Toast - enter username
                        Toast toast = Toast.makeText(getApplicationContext(), "Password field required.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else if (confirm == null) {
                        //Toast - enter username
                        Toast toast = Toast.makeText(getApplicationContext(), "Confirm field required.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else if (!password.equals(confirm)) {
                        //Toast - passwords do not match
                        Toast toast = Toast.makeText(getApplicationContext(), "Passwords do not match.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else {
                        //Pass username and password to the Presenter for Register
                        loginPresenter.login(username, password);
                    }
                }
                else if (loginMode) {
                    if (username == null) {
                        //Toast - enter password
                        Toast toast = Toast.makeText(getApplicationContext(), "Username field required.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else if (password == null) {
                        //Toast - enter username
                        Toast toast = Toast.makeText(getApplicationContext(), "Password field required.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else {
                        //Pass username and password to the Presenter for Login
                        loginPresenter.login(username, password);
                    }
                }
                return;
            }
        });
    }

    //Presenter Calls These Methods
    public void onLogin(Player currentPlayer) {
        Intent intent = new Intent(this, PreGameActivity.class);
        Gson gson = new Gson();
        intent.putExtra("currentPlayer", gson.toJson(currentPlayer));
        startActivity(intent);
    }

    public void onError(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }

}
