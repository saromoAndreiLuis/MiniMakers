package com.example.minimakers;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ViewStatisticsActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private TextView textViewStatistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_statistics);

        textViewStatistics = findViewById(R.id.textViewStatistics);
        dbHelper = new DatabaseHelper(this);

        // Display statistics
        String statistics = generateStatistics();
        textViewStatistics.setText(statistics);
    }

    private String generateStatistics() {
        List<DatabaseHelper.ScoreEntry> scores = dbHelper.getAllScores();
        if (scores.isEmpty()) {
            return "No data available.";
        }

        StringBuilder stats = new StringBuilder();
        for (DatabaseHelper.ScoreEntry score : scores) {
            stats.append("User: ").append(score.getUsername())
                    .append(", Score: ").append(score.getScore())
                    .append("\n");
        }
        return stats.toString();
    }
}
