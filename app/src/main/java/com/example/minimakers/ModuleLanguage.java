package com.example.minimakers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.minimakers.databinding.MmLandingpageBinding;

public class ModuleLanguage extends AppCompatActivity {
    MmLandingpageBinding binding;
    SharedPreferences sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MmLandingpageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        android.content.SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String registerUsername = sharedPreferences.getString("Username", "");
        String email = sharedPreferences.getString("Email", "");

        sharedPrefManager = new SharedPreferences(this);


       /* binding.settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModuleLanguage.this, SettingsActivity.class);
                intent.putExtra("Email", email);
                intent.putExtra("Username", registerUsername);
                startActivity(intent);
                finish();
            }
        });*/

    }
}