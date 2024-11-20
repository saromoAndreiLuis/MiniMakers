package com.example.minimakers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText enter_email;
    EditText password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mm_login);

        enter_email = findViewById(R.id.textInputEmail);
        password = findViewById(R.id.textInputPassword);
        login = findViewById(R.id.btn_lg);

        login.setOnClickListener(v -> {
            String email = enter_email.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please input your Email or password", Toast.LENGTH_SHORT).show();
                return;
            }

            if (email.equals("Diether") && pass.equals("Urdas")) {
                loginSuccessful("Teacher", "TEACHER");
            } else if (email.equals("Urdas") && pass.equals("Diether")) {
                loginSuccessful("Admin", "ADMIN");
            } else {
                Toast.makeText(LoginActivity.this, "You entered an incorrect email or password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loginSuccessful(String username, String role) {
        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("LoggedIn", true);
        editor.putString("Username", username);
        editor.putString("Role", role);
        editor.apply();

        Intent intent;
        if (role.equals("ADMIN")) {
            intent = new Intent(LoginActivity.this, AdminLandingPageActivity.class);
        } else {
            intent = new Intent(LoginActivity.this, TeacherLandingPageActivity.class);
        }

        intent.putExtra("Username", username);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        // Navigate to the previous page (e.g., ATLogin activity)
        Intent intent = new Intent(LoginActivity.this, ATLogin.class);
        startActivity(intent);

        // Apply playful animations for kids
        overridePendingTransition(R.anim.bounce_in, R.anim.slide_out_to_right);

        finish();
    }



}
