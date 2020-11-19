package com.example.mentallysound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Question5 extends AppCompatActivity {

    public Button match;
    public Button oppose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        match = (Button) findViewById(R.id.matchButton);
        oppose = (Button) findViewById(R.id.opposeButton);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question5);

        match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if they want music that matches their mood, keep value the same
                QuestionsStart.MoodTracker *= 1;
                Intent intent = new Intent(Question5.this, AllSet.class);
                startActivity(intent);
            }
        });

        oppose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if they want the opposite of their mood, than negate it
                QuestionsStart.MoodTracker *= -1;
                Intent intent = new Intent(Question5.this, AllSet.class);
                startActivity(intent);
            }
        });
    }
}