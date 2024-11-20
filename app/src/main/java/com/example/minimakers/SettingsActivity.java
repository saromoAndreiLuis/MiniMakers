package com.example.minimakers;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.minimakers.databinding.MmSettingsBinding;

public class SettingsActivity extends AppCompatActivity {

    MmSettingsBinding binding;
    String email;
    String registerUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MmSettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        email = getIntent().getStringExtra("Email");
        registerUsername = getIntent().getStringExtra("Username");

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("Email", email);
        registerUsername = sharedPreferences.getString("Username", registerUsername);

        binding.editUsername.setText(registerUsername);
        binding.editEmailname.setText(email);

        binding.btnLgOut.setOnClickListener(v -> showLogoutDialog());

        binding.btnEditinfo.setOnClickListener(v -> showEditInfoDialog());

        binding.devInfo.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, DevinfoActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SettingsActivity.this, ModulesActivity.class);
        intent.putExtra("Email", email);
        intent.putExtra("Username", registerUsername);
        startActivity(intent);
        finish();
    }

    private void showLogoutDialog() {
        // Create an AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomAlertDialog);

        // Set the title
        builder.setTitle("Logout");

        builder.setMessage("Really want to logout ?");

        // Add action buttons
        builder.setPositiveButton("Yes", (dialog, which) -> {
            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("LoggedIn", false);
            editor.apply();

            Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(SettingsActivity.this, "Logged out successfully!", Toast.LENGTH_LONG).show();
        });

        builder.setNegativeButton("No", (dialog, which) -> {
            dialog.cancel();
        });

        // Create and show the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.white));
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.white));
    }


    private void showEditInfoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomAlertDialog);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.mm_update_info, null);
        builder.setView(dialogView);

        final EditText editTextNewUsername = dialogView.findViewById(R.id.NewUsername);
        final EditText editTextNewEmail = dialogView.findViewById(R.id.NewEmail);

        editTextNewUsername.setText(registerUsername);
        editTextNewEmail.setText(email);

        builder.setTitle("Edit Info")
                .setPositiveButton("Update", (dialog, id) -> {
                    String newUsername = editTextNewUsername.getText().toString().trim();
                    String newEmail = editTextNewEmail.getText().toString().trim();

                    if (!newUsername.isEmpty() && !newEmail.isEmpty()) {
                        registerUsername = newUsername;
                        email = newEmail;

                        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Username", newUsername);
                        editor.putString("Email", newEmail);
                        editor.apply();

                        binding.editUsername.setText(newUsername);
                        binding.editEmailname.setText(newEmail);

                        Toast.makeText(SettingsActivity.this, "Info updated successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SettingsActivity.this, "Both fields are required", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());

        AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.white));
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.white));

    }



}
