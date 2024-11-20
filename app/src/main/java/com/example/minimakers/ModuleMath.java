package com.example.minimakers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.minimakers.databinding.MmMathBinding;

public class ModuleMath extends AppCompatActivity {
    private MmMathBinding binding;
    private SharedPreferences sharedPreferences;
    private VideoView videoView;
    private TextView questionTextView;
    private Button buttonA, buttonB, buttonC, buttonD;
    private int currentQuestionIndex = 0;
    private int correctAnswersCount = 0;
    private int incorrectAnswersCount = 0;
    private String[] questions = {
            "What is 1 + 1?",
            "How many corners does a square have?",
            "Which number is bigger: 7 or 5?",
            "What shape is a pizza slice?",
            "How many legs does a spider have?",
            "What comes next: 2, 4, 6, ___?",
            "Which number comes before 10?",
            "How many fingers do we have on two hands?",
            "What is the shape of a ball?",
            "Which is the smallest number?"
    };
    private String[][] answers = {
            {"1", "2", "3", "4"}, // 1 + 1
            {"2", "3", "4", "5"}, // square
            {"5", "9", "7", "4"}, // 7 or 5
            {"Circle", "Triangle", "Square", "Rectangle"}, // pizza
            {"2", "4", "6", "8"}, // spider
            {"7", "8", "9", "10"}, // Next
            {"3", "6", "1", "9"}, // before
            {"5", "8", "10", "7"}, // hands
            {"Square", "Triangle", "Circle", "Oval"}, // ball
            {"5", "2", "3", "1"} // smallest
    };
    private int[] correctAnswers = {1, 2, 2, 1, 3, 1, 3, 2, 2, 3,}; // Index of correct answer for each question
    private DatabaseHelper dbHelper;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MmMathBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("Username", "");
        String email = sharedPreferences.getString("Email", "");

        dbHelper = new DatabaseHelper(this);

        // Initialize views
        videoView = binding.videoView;
        questionTextView = binding.textView7;
        buttonA = binding.A;
        buttonB = binding.B;
        buttonC = binding.C;
        buttonD = binding.D;

        // Set up the video
        String videoPath = "android.resource://" + getPackageName() + "/raw/timer";
        Uri videoUri = Uri.parse(videoPath);
        videoView.setVideoURI(videoUri);
        videoView.start();
        videoView.setOnCompletionListener(mp -> videoView.start());

        // Set up the first question
        setQuestion(currentQuestionIndex);

        // Set up button click listeners
        View.OnClickListener answerClickListener = v -> {
            int selectedAnswer = -1;
            if (v.getId() == R.id.A) {
                selectedAnswer = 0;
            } else if (v.getId() == R.id.B) {
                selectedAnswer = 1;
            } else if (v.getId() == R.id.C) {
                selectedAnswer = 2;
            } else if (v.getId() == R.id.D) {
                selectedAnswer = 3;
            }
            checkAnswer(selectedAnswer);
        };

        buttonA.setOnClickListener(answerClickListener);
        buttonB.setOnClickListener(answerClickListener);
        buttonC.setOnClickListener(answerClickListener);
        buttonD.setOnClickListener(answerClickListener);
    }

    private void setQuestion(int index) {
        questionTextView.setText(questions[index]);
        buttonA.setText(answers[index][0]);
        buttonB.setText(answers[index][1]);
        buttonC.setText(answers[index][2]);
        buttonD.setText(answers[index][3]);
    }

    private void checkAnswer(int selectedAnswer) {
        if (selectedAnswer == correctAnswers[currentQuestionIndex]) {
            // Correct answer
            correctAnswersCount++;
        } else {
            // Incorrect answer
            incorrectAnswersCount++;
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            setQuestion(currentQuestionIndex);
        } else {
            // Quiz finished
            int totalQuestions = questions.length;
            long scoreId = dbHelper.addScore(username, correctAnswersCount);
            Intent intent = new Intent(ModuleMath.this, QuizResultActivity.class);
            intent.putExtra("correctAnswers", correctAnswersCount);
            intent.putExtra("incorrectAnswers", incorrectAnswersCount);
            intent.putExtra("totalQuestions", totalQuestions);
            intent.putExtra("scoreId", scoreId);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (videoView != null && !videoView.isPlaying()) {
            videoView.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (videoView != null && videoView.isPlaying()) {
            videoView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            videoView.stopPlayback();
        }
    }
}