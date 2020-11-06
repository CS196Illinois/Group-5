package com.example.mentallysound;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class Question3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("Mood Tracker", String.valueOf(QuestionsStart.MoodTracker));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);
    }
}