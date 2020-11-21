package com.example.mentallysound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class AllSet extends AppCompatActivity {

    public Button startListening;

    public String[] genreN5 = new String[] {"Blues", "Acoustic Blues", "African Blues", "Chicago Blues", "Contemporary R&B", "R&B", "Country", "Louisana Blues", "Avant-Garde", "High Classical", "Classical", "Comtemporary Classical", "Modern Composition", "Modern Classical", "Opera", "Oratorio", "Orchestral", "Organum", "Renaissance", "Romantic", "Romantic", "Sonata", "Symphonic"};
    public String[] genre10 = new String[] {"Pop", "New Wave", "Novelty", "Hip Hop", "Rap", "Country Pop", "Club", "Rock", "Alternative Rock", "Deep House", "Dubstep", "Electroswing", "Garage", "Glitch Hop", "Happy Hardcore", "Rave", "Dance-Rock", "German Pop", "Alternative Rap", "New Jersey Hip Hop", "Hip Pop", "J-Pop"};
    public String[] genre5 = new String[] {"Pop", "Hip Hop", "Rap", "Indie", "Lo-fi", "Contemporary R&B", "R&B", "Expressionist", "Country", "Dance", "Club", "Club Dance", "Minimal", "Uplifting", "Lounge", "Ambient House", "Ambient Techno", "Ambient", "Trip Hop", "Chillwave", "Folk", "Indie Folk", "Hip Pop", "Lyrical Hip Hop", "Holiday", "K-Pop", "J-Pop", "Jazz", "Latin"};
    public String[] genreNorm = new String[] {"Indie", "Lo-fi", "New Wave", "Blues", "Blues", "Acoustic Blues", "African Blues", "Chicago Blues", "Contemporary R&B", "R&B", "West Coast", "Chamber Music", "Minimal", "Modern Classical", "Modern Composition", "Classic", "Country", "Hip Hop", "Progressive", "Breakbeat", "Big Beat", "Deep House", "Garage", "Glitch Pop"};
    public String[] genreN10 = new String[] {"Art Punk", "Alternative Rock", "Britpunk", "College Rock", "Crossover Thrash", "Crust Punk", "Emotional Hardcore", "Experimental Rock", "Folk Punk", "Goth / Gothic Rock", "Grunge", "Hardcore Punk", "Hard Rock", "Indie Rock"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        startListening = (Button) findViewById(R.id.startListening);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_set);

        generateGenre(QuestionsStart.MoodTracker);

        startListening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllSet.this, MusicBrowser.class);
                startActivity(intent);
            }
        });
    }

    //generates array of top 5 genres in category
    public String[] generateArray(String[] arr) {
        assert arr != null;
        assert arr.length != 0;
        String[] returned = new String[5];
        for (int n = 0; n < returned.length; n++) {
            int random = new Random().nextInt(arr.length);
            returned[n] = arr[random];
        }
        return returned;
    }

    //generates array based on mood
    public void generateGenre(double num) {
        String[] genres;
        if (num > 10.00) {
            //choose 5 genres of music in the >10 array
            genres = generateArray(genre10);
        } else if (num > 5) {
            genres = generateArray(genre5);
        } else if (num > -5) {
            genres = generateArray(genreNorm);
        } else if (num > -10) {
            genres = generateArray(genreN5);
        } else {
            genres = generateArray(genreN10);
        }
    }
}