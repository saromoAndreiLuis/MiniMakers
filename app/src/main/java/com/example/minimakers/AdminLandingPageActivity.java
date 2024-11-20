package com.example.minimakers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AdminLandingPageActivity extends AppCompatActivity {

    private TextView welcomeText;
    private Button btnManageAccounts;
    private Button btnViewStatistics;
    private Button btnConfigureSettings;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mm_admin_landingpage);

        // Initialize views
        welcomeText = findViewById(R.id.welcomeText);
        btnManageAccounts = findViewById(R.id.btnManageAccounts);
        btnViewStatistics = findViewById(R.id.btnViewStatistics);
        btnConfigureSettings = findViewById(R.id.btnConfigureSettings);
        btnLogout = findViewById(R.id.btnLogout);

        // Set welcome message
        String username = getIntent().getStringExtra("Username");
        if (username != null && !username.isEmpty()) {
            welcomeText.setText("Welcome Admin " + username);
        }

        // Set button click listeners
        btnManageAccounts.setOnClickListener(v -> openManageAccountsActivity());
        btnViewStatistics.setOnClickListener(v -> openViewStatisticsActivity());
        btnConfigureSettings.setOnClickListener(v -> openConfigureSettingsActivity());
        btnLogout.setOnClickListener(v -> logout());
    }

    private void openManageAccountsActivity() {
        Intent intent = new Intent(this, ManageAccountsActivity.class);
        startActivity(intent);
    }

    private void openViewStatisticsActivity() {
        Intent intent = new Intent(this, ViewStatisticsActivity.class);
        startActivity(intent);
    }

    private void openConfigureSettingsActivity() {
        Intent intent = new Intent(this, ConfigureSettingsActivity.class);
        startActivity(intent);
    }

    private void logout() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Logout Confirmation")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    logout();
                })
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }
}
