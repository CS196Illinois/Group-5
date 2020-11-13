package com.example.mentallysound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Question4 extends AppCompatActivity {

    public Button motivated;
    public Button concentrated;
    public Button uplifted;
    public Button hyped;
    public Button relaxed;
    public Button nostalgic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        motivated = (Button) findViewById(R.id.motivatedButton);
        concentrated = (Button) findViewById(R.id.concentratedButton);
        uplifted = (Button) findViewById(R.id.upliftedButton);
        hyped = (Button) findViewById(R.id.hypedButton);
        relaxed = (Button) findViewById(R.id.relaxedButton);
        nostalgic = (Button) findViewById(R.id.nostalgicButton);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question4);

        motivated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Question4.this, Question5.class);
                startActivity(intent);
            }
        });

        concentrated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Question4.this, Question5.class);
                startActivity(intent);
            }
        });

        uplifted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Question4.this, Question5.class);
                startActivity(intent);
            }
        });

        hyped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Question4.this, Question5.class);
                startActivity(intent);
            }
        });

        relaxed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Question4.this, Question5.class);
                startActivity(intent);
            }
        });

        nostalgic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Question4.this, Question5.class);
                startActivity(intent);
            }
        });
    }
}