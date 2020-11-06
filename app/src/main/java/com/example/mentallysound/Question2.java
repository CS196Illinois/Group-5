package com.example.mentallysound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Question2 extends AppCompatActivity {
    public Button fast;
    public Button slow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);

        Log.d("Mood Tracker", "Current mood index: " + String.valueOf(QuestionsStart.MoodTracker));

        fast = (Button) findViewById(R.id.fastButton);
        slow = (Button) findViewById(R.id.slowButton);

        fast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if the user wants something more fast, their mood may be more heightened, be it happy or sad
                QuestionsStart.MoodTracker *= 1.25;
                Intent intent = new Intent(Question2.this, Question3.class);
                startActivity(intent);
            }
        });

        slow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if they user wants something more slow, their mood is more calm, bringing them closer to neutral
                QuestionsStart.MoodTracker *= 0.75;
                Intent intent = new Intent(Question2.this, Question3.class);
                startActivity(intent);
            }
        });
    }
}