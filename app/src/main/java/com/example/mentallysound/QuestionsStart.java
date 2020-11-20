package com.example.mentallysound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

public class QuestionsStart extends AppCompatActivity {

    //simple algo to track mood. this is the simple way out of the two that we discussed
    //using the Circumplex Model ideally, but linear for now. based on questions, this will change.
    public static double MoodTracker;

    public Button continueStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_start);

        continueStart = (Button) findViewById(R.id.continueStart);

        continueStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionsStart.this, Question1.class);
                startActivity(intent);
            }
        });
    }
}