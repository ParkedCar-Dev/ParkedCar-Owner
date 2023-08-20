package com.example.spaceowner.view.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;

import com.example.spaceowner.R;
import com.example.spaceowner.view.dashboard.DashboardActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        make this activity full screen
        getSupportActionBar().hide();
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        new Handler(Looper.myLooper()).postDelayed(() -> {
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
            this.finish();
        }, 2000);
    }
}