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

    public String[] genreN5 = new String[] {"Blues", "Acoustic Blues", "African Blues", "Chicago Blues", "Contemporary R&B", "R&B", "Country", "Louisana Blues", "Avant-Garde", "High Classical", "Classical", "Comtemporary Classical", "Modern Composition", "Modern Classical", "Opera", "Oratorio", "Orchestral", "Organum", "Renaissance", "Romantic", "Romantic", "Sonata", "Symphonic"};
    public String[] genre10 = new String[] {"Pop", "New Wave", "Novelty", "Hip Hop", "Rap", "Country Pop", "Club", "Rock", "Alternative Rock", "Deep House", "Dubstep", "Electroswing", "Garage", "Glitch Hop", "Happy Hardcore", "Rave", "Dance-Rock", "German Pop", "Alternative Rap", "New Jersey Hip Hop", "Hip Pop", "J-Pop"};
    public String[] genre5 = new String[] {"Pop", "Hip Hop", "Rap", "Indie", "Lo-fi", "Contemporary R&B", "R&B", "Expressionist", "Country", "Dance", "Club", "Club Dance", "Minimal", "Uplifting", "Lounge", "Ambient House", "Ambient Techno", "Ambient", "Trip Hop", "Chillwave", "Folk", "Indie Folk", "Hip Pop", "Lyrical Hip Hop", "Holiday", "K-Pop", "J-Pop", "Jazz", "Latin"};
    public String[] genreNorm = new String[] {"Indie", "Lo-fi", "New Wave", "Blues", "Blues", "Acoustic Blues", "African Blues", "Chicago Blues", "Contemporary R&B", "R&B", "West Coast", "Chamber Music", "Minimal", "Modern Classical", "Modern Composition", "Classic", "Country", "Hip Hop", "Progressive", "Breakbeat", "Big Beat", "Deep House", "Garage", "Glitch Pop"};
    public String[] genreN10 = new String[] {"Art Punk", "Alternative Rock", "Britpunk", "College Rock", "Crossover Thrash", "Crust Punk", "Emotional Hardcore", "Experimental Rock", "Folk Punk", "Goth / Gothic Rock", "Grunge", "Hardcore Punk", "Hard Rock", "Indie Rock"};
    public static Map<String, String> music = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        startListening = (Button) findViewById(R.id.startListening);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_set);

        generateGenre(QuestionsStart.MoodTracker);
        fillMusicMap(music);

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

    //ignore this, just putting genres with their uri's.
    //ik there is def a better way of doing this, but i couldnt find a better way lol
    public void fillMusicMap(Map<String, String> map) {
        map.put("Blues", "spotify:playlist:37i9dQZF1DXd9rSDyQguIk");
        map.put("Acoustic Blues", "spotify:playlist:37i9dQZF1DX2iUghHXGIjj");
        map.put("African Blues", "spotify:playlist:0cAGaeebKBsklVdo15q0mm");
        map.put("Chicago Blues", "spotify:playlist:37i9dQZF1DX4rGCw5bMNp1");
        map.put("Acid House", "spotify:playlist:37i9dQZF1DXbwcS5W73XRr");
        map.put("Acid Jazz", "spotify:playlist:37i9dQZF1DWXHghfFFOaS6");
        map.put("Acid Rock", "spotify:playlist:68yp7nlOnkd4uUWfFHfH4U");
        map.put("Acid Techno", "spotify:playlist:50L3W8NaVU3d4bKlCPi6I9");
        map.put("Acoustic Rock", "spotify:playlist:37i9dQZF1DX0rCrO4CFRfM");
        map.put("Afoxe", "spotify:playlist:2hDxDvoSKtMZ9ppI2As9Sw");
        map.put("Afrobeat", "spotify:playlist:4HXMPRVKOAfzoUwws8fqHW");
        map.put("Alternative Country", "spotify:playlist:2RNMLu5C5v50RhdsGkJhwv");
        map.put("Alternative Dance", "spotify:playlist:1fDwuEZMWYaOBXmfW6PkdL");
        map.put("Alternative Folk", "spotify:playlist:0JqaKY09rGN6l1lpTE6eUI");
        map.put("Alternative Hip Hop", "spotify:playlist:37i9dQZF1DWTggY0yqBxES");
        map.put("Alternative Metal", "spotify:playlist:3hMuAfaMcg9dWAwNTTuvXR");
        map.put("Alternative Pop", "spotify:playlist:1jaAEXF5ByxlouYzuAWL93");
        map.put("Alternative Punk", "spotify:playlist:4MilmjGiBljkbv0jxvObZ9");
        map.put("Alternative Rock", "spotify:playlist:0TCbzzrT932hpTAKdKj8wM");
        map.put("Ambient", "spotify:playlist:1kqBP6eE24L0agNpnTIKtc");
        map.put("Ambient Dub", "spotify:playlist:2oLMPruXKXuoXpzI3NNq0k");
        map.put("Ambient House", "spotify:playlist:4Z4CM5ZqeIVTHvIiDY7RFm");
        map.put("Ambient Techno", "spotify:playlist:49RyM6nIP0XFlwy69nUoz9");
        map.put("Americana", "spotify:playlist:37i9dQZF1DX3gvEGcwdifU");
        map.put("Anarcho-punk", "spotify:playlist:0Lx0gTeBFr7KQc321fB7Jr");
        map.put("AOR", "spotify:playlist:05QRiiz6ujxGN2oZ0P51bI");
        map.put("Arena Rock", "spotify:playlist:1XAXDKMq2CB0XIIOEtQQEe");
        map.put("Art Pop", "spotify:playlist:5oqAgQxfCjjD5ejU76a8V3");
        map.put("Art Punk", "spotify:playlist:180M35eDbTmmTCZWuJf6QO");
        map.put("Art Rock", "spotify:playlist:42yCIEXNj7s1ZXWsKOSGEV");
        map.put("Atmospheric Black Metal", "spotify:playlist:2F8BVYx9ECaVgogOk34MkV");
        map.put("Audiobook", "spotify:playlist:3tFovso2iVD16RdqiLmRc4");
        map.put("Avant-garde", "spotify:playlist:4FARCY3LRqvqR0Tm5wXQXi");
        map.put("Avant-garde Jazz", "spotify:playlist:72YNCrIywbtPaFgFSQNI6X");
        map.put("Avant-garde Metal", "spotify:playlist:5O93oZwfqsC6MauxEcJnw8");
        map.put("Avant-garde Pop", "spotify:playlist:7lyx5fUAVMkrONR9SjVzog");
        map.put("Avant-prog", "spotify:playlist:1jREw9to20Chqt77bO1xAR");
        map.put("Bachata", "spotify:playlist:5SqR3iQ1rvzjjB8vEPlF8d");
        map.put("Ballad", "spotify:playlist:0950zENyu9Vtp15jGV4DbX");
        map.put("Barbershop", "spotify:playlist:1xpEE49bS52ee6rxtl08va");
        map.put("Baroque", "spotify:playlist:4DvteColbVCrs7iIgc4r6x");
        map.put("Bass House", "spotify:playlist:6ViSThD6IjBMKBnmtzDipB");
        map.put("Beat Music", "spotify:playlist:1SiuqoeA1MCaq8YjfPqnN2");
        map.put("Bebop", "spotify:playlist:41OUnh6TuhRS7FZlbXXgMw");
        map.put("Bhangra", "spotify:playlist:37i9dQZF1DX3VNFqEPdDGw");
        map.put("Big Band", "spotify:playlist:2VMD3Kun96CVnYdog9MooY");
        map.put("Big Beat", "spotify:playlist:7gekAwUMfugfTC4hu1XA1v");
        map.put("Black Metal", "spotify:playlist:0nEi9oGlDjrSPzfk8SXhnt");
        map.put("Blackened Death Metal", "spotify:playlist:4pB6QWOYa20lrH8jSnFU0x");
        map.put("Blackgaze", "spotify:playlist:0tb4mctI0W8DgwlQzPx2Hd");
        map.put("Blue-eyed Soul", "spotify:playlist:744e4Vudqyau0nNMU2nev7");
        map.put("Bluegrass", "spotify:playlist:7fAm3STfjNSuUpvDxzb9eJ");
        map.put("Blues Rock", "spotify:playlist:56dbowk1V5ycS5jW7DSvi5");
        map.put("Bolero", "spotify:playlist:37i9dQZF1DX7YVXCfBlE4D");
        map.put("Bolero Son", "spotify:playlist:6AhFkHpGWmiAcLn55wPAMi");
        map.put("Bongo Flava", "spotify:playlist:37i9dQZF1DX3EbcelyrZPd");
        map.put("Boogie Rock", "spotify:playlist:60cfm5BxTEPrvjHl8ISaRk");
        map.put("Boogie-woogie", "spotify:playlist:5wulGZDljjUBtrTyukjKjG");
        map.put("Boom Bap", "spotify:playlist:4BUq6stVvp7RxXeQ5gmTuC");
        map.put("Bossa Nova", "spotify:playlist:37i9dQZF1DX4AyFl3yqHeK");
        map.put("Bounce", "spotify:playlist:0NB63NRtbvrEw0yfGAuAGI");
        map.put("Breakbeat", "spotify:playlist:2Y49JuVf9VWLWfIijSFZmz");
        map.put("Breakbeat Hardcore", "spotify:playlist:1OJpU7wxI4P7QFHhzNUSwg");
        map.put("Breakcore", "spotify:playlist:15PWiNpwy0vFmJIWiStE7M");
        map.put("Breaks", "spotify:album:1rVha1fzaxdLH81bzjqTph");








    }
}