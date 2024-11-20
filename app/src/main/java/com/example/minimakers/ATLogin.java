package com.example.minimakers;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.minimakers.databinding.MmLandingpageBinding;

import java.util.Arrays;
import java.util.List;

public class ATLogin extends AppCompatActivity {

    private MmLandingpageBinding binding;
    private MediaPlayer backgroundMusic;
    private List<String> studentNames = Arrays.asList(
            "Emily Johnson", "Noah Smith", "Olivia Brown", "Liam Davis", "Ava Wilson",
            "Mason Taylor", "Sophia Anderson", "James Thomas", "Isabella Jackson", "Elijah Harris", "Nikki"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MmLandingpageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize and start background music
        backgroundMusic = MediaPlayer.create(this, R.raw.mm_music_bg);
        backgroundMusic.setLooping(true); // Loop the music
        backgroundMusic.start();

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.mm_music_bg); // Play click sound
                Intent intent = new Intent(ATLogin.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.mm_music_bg); // Play click sound
                String fullName = binding.fullName.getText().toString();
                processLogin(fullName);
            }
        });
    }

    private void processLogin(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            showToast("You need to enter your full name");
            return;
        }

        fullName = fullName.trim();

        if (studentNames.contains(fullName)) {
            navigateTo(ModulesActivity.class);
        } else {
            showToast("Name not recognized. Please try again.");
        }
    }

    private void navigateTo(Class<?> destinationClass) {
        Intent intent = new Intent(this, destinationClass);
        startActivity(intent);
        finish();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void exitApp() {
        // Exits the application
        finishAffinity(); // Closes all activities and exits the app
        Toast.makeText(this, "Exit successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {

        // Show exit confirmation dialog
        new AlertDialog.Builder(this)
                .setTitle("Exit Confirmation")
                .setMessage("Are you sure you want to exit the game?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    exitApp();
                })
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    // Helper method to play sound
    private void playSound(int soundResource) {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, soundResource);
        mediaPlayer.setOnCompletionListener(MediaPlayer::release); // Release resources after playing
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop and release background music resources when the activity is destroyed
        if (backgroundMusic != null) {
            backgroundMusic.stop();
            backgroundMusic.release();
            backgroundMusic = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Pause background music when the activity is paused
        if (backgroundMusic != null && backgroundMusic.isPlaying()) {
            backgroundMusic.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Resume background music when the activity is resumed
        if (backgroundMusic != null && !backgroundMusic.isPlaying()) {
            backgroundMusic.start();
        }
    }
}
