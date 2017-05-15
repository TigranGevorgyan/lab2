package com.example.andranikh.lab2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andranikh.lab2.R;
import com.example.andranikh.lab2.pojos.User;
import com.example.andranikh.lab2.utils.PreferancesHelper;
import com.example.andranikh.lab2.utils.user.FileUserStorage;
import com.example.andranikh.lab2.utils.user.UserStorage;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView fNameTv;
    private TextView lNameTv;
    private TextView emailTv;
    private TextView usernameTv;
    private TextView password1Tv;
    private TextView password2Tv;
    private RadioGroup genderRg;
    private TextView ageTv;

    private UserStorage userStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fNameTv = (TextView) findViewById(R.id.activity_reg_fname_et);
        lNameTv = (TextView) findViewById(R.id.activity_reg_lname_et);
        emailTv = (TextView) findViewById(R.id.activity_reg_email_et);
        usernameTv = (TextView) findViewById(R.id.activity_reg_username_et);
        password1Tv = (TextView) findViewById(R.id.activity_reg_password1_et);
        password2Tv = (TextView) findViewById(R.id.activity_reg_password2_et);
        genderRg = (RadioGroup) findViewById(R.id.activity_reg_gender_rg);
        ageTv = (TextView) findViewById(R.id.activity_reg_age_et);

        findViewById(R.id.activity_reg_btn).setOnClickListener(this);

        userStorage = new FileUserStorage();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_reg_btn:
                registerUser();
                break;
        }
    }

    private void registerUser(){
        if(!checkAllFiledsValid()){
            return;
        }

        String fName = fNameTv.getText().toString();
        String lName = lNameTv.getText().toString();
        String email = emailTv.getText().toString();
        String username = usernameTv.getText().toString();
        String password = password1Tv.getText().toString();
        User.Gender gender = genderRg.getCheckedRadioButtonId() == R.id.activity_reg_gender_rb_male ? User.Gender.MALE : User.Gender.FEMALE;
        int age = Integer.valueOf(ageTv.getText().toString());

        User user = new User(username, email, password, fName, lName, gender, age);

        userStorage.registerUser(user, new UserStorage.UserFoundListener() {
            @Override
            public void onUserFound(User user) {
                handelUserFound(user);
            }
        });

    }

    private void handelUserFound(User user){
        if(user == null){
            Toast.makeText(this, "Something went wrong during registration", Toast.LENGTH_SHORT).show();
            return;
        }

        PreferancesHelper preferancesHelper = PreferancesHelper.getInstance(this);
        preferancesHelper.setLoggedIn(true);
        preferancesHelper.setUserId(user.getId());

        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
    }

    private boolean checkAllFiledsValid(){
        if (fNameTv.getText().toString().length() == 0 || lNameTv.getText().toString().length() == 0){
            Toast.makeText(this,"Write first name and last name.",Toast.LENGTH_LONG).show();
            return false;
        }
        if (!(emailTv.getText().toString().contains("@"))) {
            Toast.makeText(this, "Email must be contain symbol '@' ", Toast.LENGTH_LONG).show();
            return false;
        }
        if (emailTv.getText().toString().length() < 8) {
            Toast.makeText(this, "Email must be contain minimum 8 symbol", Toast.LENGTH_LONG).show();
            return false;
        }
        if (usernameTv.getText().toString().length() < 3){
            Toast.makeText(this,"Username must be minimum 3 symbol",Toast.LENGTH_LONG).show();
            return false;
        }
        if (password1Tv.getText().toString().length() < 6){
            Toast.makeText(this,"Password must be minimum 6 symbol",Toast.LENGTH_LONG).show();
            return false;
        }
        if (!(password1Tv.getText().toString().equals(password2Tv.getText().toString()))){
            Toast.makeText(this,"Password and confirm password must be same",Toast.LENGTH_LONG).show();
            return false;
        }
        if (genderRg.getCheckedRadioButtonId() == -1){
            Toast.makeText(this,"Choose gender",Toast.LENGTH_LONG).show();
            return false;
        }
        if (ageTv.getText().toString().length() == 0){
            Toast.makeText(this,"Write age",Toast.LENGTH_LONG).show();
            return false;
        }

        if (Integer.parseInt(ageTv.getText().toString()) < 8 || Integer.parseInt(ageTv.getText().toString()) > 120){
            Toast.makeText(this,"Write correct age (8 - 120)",Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}
