package com.example.mentallysound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Question2 extends AppCompatActivity {

    public Button meh;
    public Button happy;
    public Button sad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);

        meh = (Button) findViewById(R.id.meh2);
        happy = (Button) findViewById(R.id.happy2);
        sad = (Button) findViewById(R.id.sad2);

        meh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionsStart.MoodTracker += 0;
                Intent intent = new Intent(Question2.this, Question3.class);
                startActivity(intent);
            }
        });

        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if they are less stressed they wouldn't mind something more energetic
                QuestionsStart.MoodTracker *= 1.25;
                Intent intent = new Intent(Question2.this, Question3.class);
                startActivity(intent);
            }
        });

        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if they are more stressed they would want something more calm and neutral
                QuestionsStart.MoodTracker *= 0.75;
                Intent intent = new Intent(Question2.this, Question3.class);
                startActivity(intent);
            }
        });
    }
}