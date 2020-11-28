package com.example.mentallysound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AllSet extends AppCompatActivity {

    public Button startListening;

    //the different genre categories filled with all 500+ genres.
    //+10 is the happiest, and -10 is the saddest/angriest.
    public static String[] genre10 = new String[] {"alternative dance", "alternative folk", "alternative hip hop", "alternative pop", "americana", "art pop", "avant-garde", "avant-garde jazz", "avant-garde pop", "avant-prog", "bhangra", "big band", "big beat", "bounce", "britpop", "bubblegum pop", "chamber pop", "christmas music", "city pop", "club", "comedy", "country pop", "crossover prog", "dance", "dance-pop", "dance-punk", "dancehall", "disco", "doo-wop", "dream pop"};
    public static String[] genre5 = new String[] {"acid house", "acid jazz", "acid rock", "acid techno", "acoustic rock", "alternative hip hop", "alternative rock", "ambient", "ambient dub", "ambient techno", "aor", "art pop", "audiobook", "avant-garde", "avant-garde jazz", "ballad", "barbershop", "boogie rock", "boogie-woogie", "bounce", "chachachá", "chamber pop", "christian rock", "christmas music", "club", "comedy rock", "contemporary folk", "country folk", "country rock", "dance", "dance-punk", "dream pop"};
    public static String[] genreNorm = new String[] {"afoxê", "afrobeat", "alternate country", "alternative punk", "alternative rock", "ambient", "ambient dub", "arena rock", "art punk", "art rock", "audiobook", "avant-garde", "avant-garde jazz", "avant-garde metal", "ballad", "bouce", "broken beat", "carnatic classical", "chillout", "christmas music", "classic country", "classic rock", "classical", "club", "contemporary christian", "contemporary classical", "crust punk", "desert rock", "detroit techno", "downtempo"};
    public static String[] genreN5 = new String[] {"acoustic blues", "alternative metal", "anarcho-punk", "art punk", "art rock", "avant-garde", "avant-garde jazz", "avant-garde metal", "ballad", "bass house", "blackgaze", "blues", "breakbeat", "chicago blues", "classic blues", "classic country", "classic jazz", "classical", "classical crossover", "conscious hip hop", "contemporary classical", "contemporary gospel", "contemporary jazz", "contemporary r&b", "cool jazz", "country blues", "dark ambient", "dark electro", "dark folk", "deep house", "delta blues", "digital hardcore", "dub", "dub techno", "dubstep"};
    public static String[] genreN10 = new String[] {"acoustic blues", "alternative metal", "anarcho-punk", "atmospheric black metal", "beat music", "black metal", "blackened death metal", "blackgaze", "blues", "blues rock", "breakbeat hardcore", "breakcore", "brutal death metal", "chicago blues", "classic blues", "classic jazz", "contemporary gospel", "contemporary jazz", "contemporary r&b", "dark ambient", "dark electro", "dark wave", "death metal", "death-doom metal", "deathcore", "deathgrind", "deathgrind", "deathrock", "doom metal", "dub", "dubstep"};

    public static Map<String, String> music = new HashMap<>();

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

    //generates a random genre depending on the category
    public static String generateArray(String[] arr) {
        assert arr != null;
        assert arr.length != 0;
        String returned;
        int random = new Random().nextInt(arr.length);
        returned = arr[random];
        return returned;
    }
    //generates array based on mood
    public static String generateGenre(double num) {
        String genreToPlay;
        if (num > 10.00) {
            //choose 5 genres of music in the >10 array
            genreToPlay = generateArray(genre10);
        } else if (num > 5) {
            genreToPlay = generateArray(genre5);
        } else if (num > -5) {
            genreToPlay = generateArray(genreNorm);
        } else if (num > -10) {
            genreToPlay = generateArray(genreN5);
        } else {
            genreToPlay = generateArray(genreN10);
        }
        return genreToPlay;
    }
}