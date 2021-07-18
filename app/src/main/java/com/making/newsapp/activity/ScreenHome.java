package com.making.newsapp.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;


import com.making.newsapp.R;
import com.making.newsapp.customclass.CustomAppCompatActivity;
import com.making.newsapp.supportfiles.PreferenceData;
import com.making.newsapp.utils.ValueUtils;


public class ScreenHome extends CustomAppCompatActivity {

    ProgressBar splashProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_home);

        splashProgress = findViewById(R.id.splashProgress);
        playProgress();

        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do any action here. Now we are moving to next page
                Intent mySuperIntent = new Intent(ScreenHome.this, MainActivity.class);
                startActivity(mySuperIntent);

                //This 'finish()' is for exiting the app when back button pressed from Home page which is ActivityHome
                finish();

            }
        }, ValueUtils.SPLASH_TIME_OUT);
    }

    private void playProgress() {
        ObjectAnimator.ofInt(splashProgress, "progress", 100)
                .setDuration(5000)
                .start();
    }
}



