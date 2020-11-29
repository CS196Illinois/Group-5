package com.example.mentallysound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Question4 extends AppCompatActivity {

    public Button meh;
    public Button happy;
    public Button sad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question4);

        meh = (Button) findViewById(R.id.meh4);
        happy = (Button) findViewById(R.id.happy4);
        sad = (Button) findViewById(R.id.sad4);

        meh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Question4.this, MusicBrowser.class);
                startActivity(intent);
            }
        });

        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionsStart.MoodTracker += 4;
                QuestionsStart.effectArray[0] -= 1.0;
                QuestionsStart.effectArray[1] -= 1.0;
                QuestionsStart.effectArray[2] -= 1.0;
                Intent intent = new Intent(Question4.this, MusicBrowser.class);
                startActivity(intent);
            }
        });

        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionsStart.MoodTracker -= 4;
                QuestionsStart.effectArray[0] += 1.0;
                QuestionsStart.effectArray[1] += 1.0;
                QuestionsStart.effectArray[2] += 1.0;
                Intent intent = new Intent(Question4.this, MusicBrowser.class);
                startActivity(intent);
            }
        });
    }
}