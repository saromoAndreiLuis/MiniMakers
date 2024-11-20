package com.example.minimakers;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private static final long SPLASH_DURATION = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Delay for 3 seconds and then show the input field and submit button
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Removed SharedPreferences and LoggedIn check
                Intent intent = new Intent(MainActivity.this, ATLogin.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DURATION);
    }
}