package com.example.minimakers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.minimakers.databinding.MmModulesBinding;

public class ModulesActivity extends AppCompatActivity {
    MmModulesBinding binding;
    SharedPreferences sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MmModulesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        android.content.SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String registerUsername = sharedPreferences.getString("Username", "");
        String email = sharedPreferences.getString("Email", "");

        sharedPrefManager = new SharedPreferences(this);


        /*binding.settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesActivity.this, SettingsActivity.class);
                intent.putExtra("Email", email);
                intent.putExtra("Username", registerUsername);
                startActivity(intent);
                finish();
            }
        });*/
        binding.math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesActivity.this, ModuleMath.class);
                intent.putExtra("Email", email);
                intent.putExtra("Username", registerUsername);
                startActivity(intent);
                finish();
            }
        });
        binding.language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesActivity.this, ModuleLanguage.class);
                intent.putExtra("Email", email);
                intent.putExtra("Username", registerUsername);
                startActivity(intent);
                finish();
            }
        });
        binding.science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesActivity.this, ModuleScience.class);
                intent.putExtra("Email", email);
                intent.putExtra("Username", registerUsername);
                startActivity(intent);
                finish();
            }
        });
        binding.programing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesActivity.this, ModuleProgramming.class);
                intent.putExtra("Email", email);
                intent.putExtra("Username", registerUsername);
                startActivity(intent);
                finish();
            }
        });
        binding.ap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModulesActivity.this, ModuleProgramming.class);
                intent.putExtra("Email", email);
                intent.putExtra("Username", registerUsername);
                startActivity(intent);
                finish();
            }
        });

    }
}