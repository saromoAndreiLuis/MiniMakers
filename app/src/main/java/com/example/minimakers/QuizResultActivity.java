package com.example.minimakers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class QuizResultActivity extends AppCompatActivity {

    private TextView scoreTextView;
    private TextView performanceTextView;
    private TextView highScoresTextView;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        scoreTextView = findViewById(R.id.scoreTextView);
        performanceTextView = findViewById(R.id.performanceTextView);
        highScoresTextView = findViewById(R.id.highScoresTextView);

        dbHelper = new DatabaseHelper(this);


        // Retrieve quiz data from Intent
        int correctAnswers = getIntent().getIntExtra("correctAnswers", 0);
        int incorrectAnswers = getIntent().getIntExtra("incorrectAnswers", 0);
        int totalQuestions = getIntent().getIntExtra("totalQuestions", 0);

        // Display user performance
        performanceTextView.setText("Total Questions: " + totalQuestions + "\n" + " Correct: " + correctAnswers + "\n" + "Incorrect: " + incorrectAnswers );

        // Display current score
        scoreTextView.setText("You Got: " + correctAnswers + " / " + totalQuestions);

        // Display high scores
        displayHighScores();
    }

    private void displayHighScores() {
        List<DatabaseHelper.ScoreEntry> highScores = dbHelper.getAllScores();
        StringBuilder sb = new StringBuilder();
        sb.append("Your Highest Scores:\n");
        for (int i = 0; i < Math.min(5, highScores.size()); i++) {
            DatabaseHelper.ScoreEntry entry = highScores.get(i);
            sb.append(entry.getUsername()).append(": ").append(entry.getScore()).append("\n");
        }
        highScoresTextView.setText(sb.toString());
    }
    private void backToModule() {
        Intent intent = new Intent(this, ModulesActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
        Toast.makeText(this, "Welcome back", Toast.LENGTH_SHORT).show();
    }

    private void exitApp() {
        finishAffinity(); // Closes all activities and exits the app
        Toast.makeText(this, "Exit successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit Confirmation")
                .setMessage("Do you really want to exit?")
                .setPositiveButton("Exit", (dialog, which) -> {
                    exitApp();
                })
                .setNeutralButton("Back to Module", (dialog, which) -> {
                    backToModule();
                })
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

}
