package com.example.mentallysound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

        meh = (Button) findViewById(R.id.meh);
        happy = (Button) findViewById(R.id.happy);
        sad = (Button) findViewById(R.id.sad);
        angry = (Button) findViewById(R.id.angry);

        meh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionsStart.MoodTracker += 0;
                Intent intent = new Intent(Question1.this, MainActivity.class);
                startActivity(intent);
            }
        });

        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionsStart.MoodTracker += 5;
                Intent intent = new Intent(Question1.this, MainActivity.class);
                startActivity(intent);
            }
        });

        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionsStart.MoodTracker += -3.33;
                Intent intent = new Intent(Question1.this, MainActivity.class);
                startActivity(intent);
            }
        });

        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionsStart.MoodTracker += -6.66;
                Intent intent = new Intent(Question1.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}