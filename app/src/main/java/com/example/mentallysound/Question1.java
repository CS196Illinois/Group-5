package com.example.mentallysound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Question1 extends AppCompatActivity {

    public Button meh;
    public Button happy;
    public Button sad;
    public Button angry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        Log.d("Mood Tracker", "Current mood index: " + String.valueOf(QuestionsStart.MoodTracker));

        meh = (Button) findViewById(R.id.meh);
        happy = (Button) findViewById(R.id.happy);
        sad = (Button) findViewById(R.id.sad);
        angry = (Button) findViewById(R.id.angry);

        meh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionsStart.MoodTracker += 0;
                Intent intent = new Intent(Question1.this, Question2.class);
                startActivity(intent);
            }
        });

        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionsStart.MoodTracker += 5;
                //QuestionsStart.moodMap.put("happy", QuestionsStart.moodMap.get("happy") + 5);
                Intent intent = new Intent(Question1.this, Question2.class);
                startActivity(intent);
            }
        });

        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionsStart.MoodTracker += -3.33;
                //QuestionsStart.moodMap.put("sad", QuestionsStart.moodMap.get("normal") + 5);
                Intent intent = new Intent(Question1.this, Question2.class);
                startActivity(intent);
            }
        });

        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionsStart.MoodTracker += -6.66;
                //QuestionsStart.moodMap.put("angry", QuestionsStart.moodMap.get("angry") + 5);
                Intent intent = new Intent(Question1.this, Question2.class);
                startActivity(intent);
            }
        });
    }
}