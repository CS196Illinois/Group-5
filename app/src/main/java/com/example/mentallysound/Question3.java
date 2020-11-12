package com.example.mentallysound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Question3 extends AppCompatActivity {

    public Button continueQ3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        continueQ3 = (Button) findViewById(R.id.continueQ3Button);

        Log.d("Mood Tracker", "Current mood index: " + String.valueOf(QuestionsStart.MoodTracker));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);

        continueQ3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Question3.this, Question4.class);
                startActivity(intent);
            }
        });
    }
}