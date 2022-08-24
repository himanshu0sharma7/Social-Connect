package com.example.twitter;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class sc_splash_screen extends AppCompatActivity {

    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sc_splash_screen);

        ActionBar action = getSupportActionBar();
        action.hide();

        handler.postDelayed(() -> {
            Intent i = new Intent(sc_splash_screen.this , twitpage.class);
            startActivity(i);
            finish();
        }, 1000);

    }
}