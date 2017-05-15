package com.example.andranikh.lab2.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.andranikh.lab2.R;
import com.example.andranikh.lab2.pojos.User;
import com.example.andranikh.lab2.utils.PreferancesHelper;

public class StartupActivity extends AppCompatActivity {

    private static final int DELAY = 1000;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                navigate();
            }
        }, DELAY);
    }

    private void navigate(){
        Class<? extends Activity> activityClass;

        if(PreferancesHelper.getInstance(this).isLoggedIn()){
            activityClass = MainActivity.class;
        } else {
            activityClass = LoginActivity.class;
        }

        startActivity(new Intent(this, activityClass));
        finish();
    }

    @Override
    public void onBackPressed() {

    }
}
