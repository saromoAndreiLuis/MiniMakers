package com.example.minimakers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizScores.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_SCORES = "scores";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_SCORE = "score";
    private static final String COLUMN_TIMESTAMP = "timestamp";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SCORES_TABLE = "CREATE TABLE " + TABLE_SCORES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_SCORE + " INTEGER,"
                + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                + ")";
        db.execSQL(CREATE_SCORES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
        onCreate(db);
    }

    public long addScore(String username, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_SCORE, score);
        long id = db.insert(TABLE_SCORES, null, values);
        db.close();
        return id;
    }

    public List<ScoreEntry> getAllScores() {
        List<ScoreEntry> scoreList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_SCORES + " ORDER BY " + COLUMN_SCORE + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ScoreEntry score = new ScoreEntry();
                score.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                score.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
                score.setScore(cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE)));
                score.setTimestamp(cursor.getString(cursor.getColumnIndex(COLUMN_TIMESTAMP)));
                scoreList.add(score);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return scoreList;
    }

    public static class ScoreEntry {
        private int id;
        private String username;
        private int score;
        private String timestamp;

        // Getters and setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public int getScore() { return score; }
        public void setScore(int score) { this.score = score; }
        public void setTimestamp(String timestamp) { this.timestamp = timestamp;}
        public String getTimestamp() { return timestamp; }
    }
}