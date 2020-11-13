package com.example.mentallysound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AllSet extends AppCompatActivity {

    public Button startListening;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        startListening = (Button) findViewById(R.id.startListening);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_set);

        startListening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllSet.this, MusicBrowser.class);
                startActivity(intent);
            }
        });
    }
}