package com.example.bookstore.view.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.bookstore.R;
import com.example.bookstore.view.main.MainActivity;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToMain();
            }
        },3000);
    }

    private void goToMain(){
        startActivity(new Intent(splashActivity.this, MainActivity.class));
        finish();
    }
}
