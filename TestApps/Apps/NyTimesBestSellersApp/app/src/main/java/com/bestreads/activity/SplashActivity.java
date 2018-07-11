package com.bestreads.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.bestreads.R;

/**
 * Created by mansshah on 7/5/18.
 */
public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_TIME = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            public void run() {

                Intent intent = new Intent();
                intent.setClass(SplashActivity.this,
                        MainActivity.class);

                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();

            }
        }, SPLASH_DISPLAY_TIME);
    }
}