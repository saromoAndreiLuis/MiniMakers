package com.example.minimakers;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TeacherLandingPageActivity extends AppCompatActivity {

    private TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mm_teacher_landingpage);

        welcomeText = findViewById(R.id.welcomeText);

        String username = getIntent().getStringExtra("Username");
        if (username != null && !username.isEmpty()) {
            welcomeText.setText("Welcome, Teacher " + username + "!");
        }

        // For example:
        // - View student progress
        // - Manage assignments
        // - Access teaching resources
    }
}
