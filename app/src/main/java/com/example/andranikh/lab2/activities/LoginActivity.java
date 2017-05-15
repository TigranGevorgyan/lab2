package com.example.andranikh.lab2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andranikh.lab2.R;
import com.example.andranikh.lab2.pojos.User;
import com.example.andranikh.lab2.utils.PreferancesHelper;
import com.example.andranikh.lab2.utils.user.FileUserStorage;
import com.example.andranikh.lab2.utils.user.UserStorage;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText usernameEt;
    private EditText passwordEt;

    private UserStorage userStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEt = (EditText) findViewById(R.id.activity_login_username_et);
        passwordEt = (EditText) findViewById(R.id.activity_login_password_et);

        findViewById(R.id.activity_login_login_btn).setOnClickListener(this);
        findViewById(R.id.activity_login_signup_btn).setOnClickListener(this);

        userStorage = new FileUserStorage();
    }

    private void signUp(){
        startActivity(new Intent(this, RegistrationActivity.class));
    }

    private void login(){
        if(!checkInputValid()){
            return;
        }

        userStorage.checkAndGetUser(usernameEt.getText().toString(), passwordEt.getText().toString(), new UserStorage.UserFoundListener() {
            @Override
            public void onUserFound(User user) {
                handelUserFound(user);
            }
        });
    }

    private void handelUserFound(User user){
        if(user == null){
            Toast.makeText(this, "Wrong username or password", Toast.LENGTH_SHORT).show();
            return;
        }

        PreferancesHelper preferancesHelper = PreferancesHelper.getInstance(this);
        preferancesHelper.setLoggedIn(true);
        preferancesHelper.setUserId(user.getId());

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private boolean checkInputValid(){
        if(usernameEt.getText().length() == 0 || passwordEt.getText().length() == 0){
            Toast.makeText(this, "Username and password fields are required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(usernameEt.getText().length() < 6){
            Toast.makeText(this, "Username must be minimum 6 symbol", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(passwordEt.getText().length() < 6){
            Toast.makeText(this, "Password must be minimum 6 symbol", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_login_login_btn:
                login();
                break;

            case R.id.activity_login_signup_btn:
                signUp();
                break;
        }
    }
}
