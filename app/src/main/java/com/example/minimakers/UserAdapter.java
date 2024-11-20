package com.example.minimakers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ArrayAdapter;
import java.util.List;

public class UserAdapter extends ArrayAdapter<DatabaseHelper.ScoreEntry> {

    private final Context context;
    private final List<DatabaseHelper.ScoreEntry> users;

    public UserAdapter(Context context, List<DatabaseHelper.ScoreEntry> users) {
        super(context, R.layout.user_list_item, users);
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.user_list_item, parent, false);
        }

        DatabaseHelper.ScoreEntry user = users.get(position);

        TextView textUsername = convertView.findViewById(R.id.textUsername);
        TextView textScore = convertView.findViewById(R.id.textScore);

        textUsername.setText(user.getUsername());
        textScore.setText(String.valueOf(user.getScore()));

        return convertView;
    }
}
