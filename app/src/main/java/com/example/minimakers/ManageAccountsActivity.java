package com.example.minimakers;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ManageAccountsActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private ListView listViewUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_accounts);

        listViewUsers = findViewById(R.id.listViewUsers);
        dbHelper = new DatabaseHelper(this);

        // Fetch user data and display
        List<DatabaseHelper.ScoreEntry> users = dbHelper.getAllScores();
        UserAdapter adapter = new UserAdapter(this, users);
        listViewUsers.setAdapter(adapter);
    }
}
