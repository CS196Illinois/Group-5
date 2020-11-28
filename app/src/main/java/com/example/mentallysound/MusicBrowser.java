package com.example.mentallysound;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class MusicBrowser extends AppCompatActivity {

    private static final String CLIENT_ID = "39914e12eddd4bdf83e4f0ba4104727c";
    private static final String REDIRECT_URI = "http://yoursite.com";
    private SpotifyAppRemote mSpotifyAppRemote;

    public TextView songTitle;
    public TextView artistName;
    public ProgressBar songDuration;
    public TextView genrePlaying;

    private int progressStatus = 0;
    private Handler handler = new Handler();

    //ignore this, just putting genres with their uri's.
    //there is def a better way of doing this, but i couldnt find a better way lol
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
    map.put("Breaks", "spotify:playlist:0S8VUQb3yrM7VUd2JLBO7p");
    map.put("Britpop", "spotify:playlist:1rlBSxR4WryLssBr4kiZ77");
    map.put("Brokenbeat", "spotify:playlist:2b8IUBxy00wteVzPCpiLbn");
    map.put("Brostep", "spotify:playlist:4e1TfVuPeumCSZoA8KRq48");
    map.put("Brutal Death Metal", "spotify:playlist:5iZh1symrVbgqWNwXACTZ2");
    map.put("Bubblegom Pop", "spotify:playlist:1pzWKmDCTcDGBQUyYdi8c3");
    map.put("Cajun", "spotify:playlist:2KmefvmYGgMH8fvkHLWHse");
    map.put("Calypso", "spotify:playlist:1UmzDFJhtZvo9yGjxcfnz0");
    map.put("Candombe", "spotify:playlist:6zT60dPcpA3v83eoOVcqZG");
    map.put("Canterbury Scene", "spotify:playlist:0cKGEchDZyIGPp7XulCZO4");
    map.put("Cantopop", "spotify:playlist:37i9dQZF1DWW6TogMpx8Q2");
    map.put("Carnatic Classical", "spotify:album:1HlSWm3iBAwX0i2I0JXFCS");
    map.put("Celtic", "spotify:playlist:5OJdvyCQpZKdNQNUnOYYzk");
    map.put("Celtic Punk", "spotify:playlist:37i9dQZF1DXb1IUaS6F7Z8");
    map.put("Chachacha", "spotify:playlist:5sGKJFi92t7IImZxQvFjPQ");
    map.put("Chamber Pop", "spotify:playlist:6jFw8tgtJFwKW7rzh2mEZD");
    map.put("Champeta", "spotify:playlist:3XUcnPXjvejUdKk6mDD41a");
    map.put("Changui", "spotify:playlist:3O0Un5vDr3f6YxZelz7prs");
    map.put("Chanson", "spotify:playlist:3gEVAjHZLfejDhLrxVfGNj");
    map.put("Chillout", "spotify:playlist:7ozIozDp260fjNOZy1yzRG");
    map.put("Chiptune", "spotify:playlist:137E6KBEACEjDWve3W6cgq");
    map.put("Chopped and Screwed", "spotify:playlist:785iwTcs9z8QLmakRVFTGA");
    map.put("Christian Rock", "spotify:playlist:37i9dQZF1DX0N57moxx9BL");
    map.put("Christmas Music", "spotify:playlist:37i9dQZF1DX0Yxoavh5qJV");
    map.put("Chutney", "spotify:playlist:3ovpLfmghnPprJfziTlqM7");
    map.put("City Pop", "spotify:playlist:5ZEY1gtao9eGzvAD3KEh2x");
    map.put("Classic Blues", "spotify:playlist:37i9dQZF1DXd9rSDyQguIk");
    map.put("Classic Country", "spotify:playlist:6U8AxQBEDDW4GlzbqkoRMV");
    map.put("Classic Jazz", "spotify:playlist:3ZZBSpcUJ4Gi2uzHjDDpC3");
    map.put("Classic Rock", "spotify:playlist:6TeyryiZ2UEf3CbLXyztFA");
    map.put("Classical", "spotify:playlist:37i9dQZF1DWWEJlAGA9gs0");
    map.put("Classical Crossover", "spotify:playlist:4G5JSCSOc9zHB6VbuqxiUq");
    map.put("Club", "spotify:playlist:2qbOF1dJ0wBmnnLMIusa9p");
    map.put("Comedy", "spotify:playlist:4rYEENJRLjWDFfgChdL4kl");
    map.put("Comedy Rock", "spotify:playlist:6LsciadnOQJFiQRSBI5LE2");
    map.put("Compas", "spotify:playlist:51ZF6pPpYyeSMt090OzF39");
    map.put("Complextro", "spotify:playlist:1W9U7NkU9YVOdYVMaIi9Ty");
    map.put("Conscious Hip Hop", "spotify:playlist:13lAvSxOw1uCX9wqeVFykE");
    map.put("Contemporary Christian", "spotify:playlist:1h5ojikWVCSpYGoEbhDCgG");
    map.put("Contemporary Classical", "spotify:playlist:1rrTvJzJE8dBFdnzMfPwlS");
    map.put("Contemporary Folk", "spotify:playlist:37i9dQZF1DX1nqy6pUAoUv");
    map.put("Contemporary Gospel", "spotify:playlist:3Jfim4bWxkbjXHhgApBskF");
    map.put("Contemporary Jazz", "spotify:playlist:4uhchdjV19aiEFrEYmdHQS");
    map.put("Contemporary R&B", "spotify:playlist:4BUmWtByLRpdEYomlLTiXi");
    //double check contra
    map.put("Contra", "spotify:playlist:32I2E1yJ3syR8VKn4hgfi7");
    map.put("Cool Jazz", "spotify:playlist:37i9dQZF1DWTG7FCdM1HiA");
    map.put("Country", "spotify:playlist:6nU0t33tQA2i0qTI5HiyRV");
    map.put("Country Blues", "spotify:playlist:37i9dQZF1DWWEJlAGA9gs0");
    map.put("Country Folk", "spotify:playlist:5XgcdeBzILhrcQPLF2Loja");
    map.put("Country Pop", "spotify:playlist:40Ktnv37eFQOHN2RLolHEw");
    map.put("Country Rock", "spotify:playlist:0v4DvG8cBpHGOBOxVuVvgV");
    map.put("Coupe-Decale", "spotify:playlist:37i9dQZF1DX0z1epx5KTtS");
    map.put("Cowpunk", "spotify:playlist:6VbtVCyMUz0eoiWGl2r6g7");
    map.put("Crossover Prog", "spotify:playlist:2hxu7BD7LZ3TSdEf8VN9xS");
    map.put("Crust Punk", "spotify:playlist:1M4UmyNnokZRdeAmBEaftG");
    map.put("Cumbia", "spotify:playlist:3pwEllWmQJajyllqFg6jTF");
    map.put("Cumbia Villera", "spotify:playlist:7ukAyJrKU3ZiLZBGz4vueH");
    map.put("Cyberpunk", "spotify:playlist:3To7rpikLDGb0uUnbFCBfQ");
    map.put("D-Beat", "spotify:playlist:5EoC9ERI2yecvjGax0AXwj");
    map.put("Dance", "spotify:playlist:37i9dQZF1DX0BcQWzuB7ZO");
    map.put("Dance-Pop", "spotify:playlist:37i9dQZF1DWZQaaqNMbbXa");
    map.put("Dance-Punk", "spotify:playlist:6c6EDGbcWdOSUvTaaUENyg");
    map.put("Dancehall", "spotify:playlist:6RMoIh0N6NPokXLKV6AyUX");
    map.put("Dansband", "spotify:playlist:37i9dQZF1DX5aniZvGQsXc");
    map.put("Dark Ambient", "spotify:playlist:4X7woiZ7EjZaLi9ZkDDOXk");
    map.put("Dark Electro", "spotify:playlist:5arKLuU39VKym4leePpdpA");
    map.put("Dark Folk", "spotify:playlist:4oort7dQ9nx9uCglEuLIRy");
    map.put("Dark Wave", "spotify:playlist:2OKRPDYytFbe5FmPlwqPmP");
    map.put("Death Metal", "spotify:playlist:2vivknVOeJD7BUYnnuztrE");
    map.put("Death-Doom Metal", "spotify:playlist:3EvU1abnwG5p8RQkv6aPnG");
    map.put("Deathcore", "spotify:playlist:37i9dQZF1DX1cJWWyylDuw");
    map.put("Deathgrind", "spotify:playlist:7eFqFSYDm17u1nUaSu7fg2");
    map.put("Deathrock", "spotify:playlist:7DJy1Li1329qvqe1ex7wUB");
    map.put("Deep House", "spotify:playlist:6vDGVr652ztNWKZuHvsFvx");
    map.put("Delta Blues", "spotify:playlist:37i9dQZF1DWSTHVqvNCwNq");
    map.put("Descarga", "spotify:playlist:32z94KC6rQj4D2O9VP8kxo");
    map.put("Desert Rock", "spotify:playlist:1DbwgQvTu3dehBIXzT45YN");
    map.put("Detroit Techno", "spotify:playlist:37i9dQZF1DX1GT5IIzDqMe");
    map.put("Digital Hardcore", "spotify:playlist:2fqbzU1sBBZ3gW61Q7qYhp");
    map.put("Disco", "spotify:playlist:2PzF3urLEgnQs4cmokAVHX");
    map.put("Doo-Wop", "spotify:playlist:5QjMzXNIWDAj3vgUsgl7zH");
    map.put("Doom Metal", "spotify:playlist:5Lzif2bIMW8RiRLtbYJHU0");
    map.put("Downtempo", "spotify:playlist:03mUE11LSpb7eePR2ck5P4");
    map.put("Dream Pop", "spotify:playlist:6tdYRrSvLhfkETS8cJKMTM");
    map.put("Drill", "spotify:playlist:6AF3nsX31HBnZNkyj2sFXy");
    map.put("Drill and Bass", "spotify:playlist:2xv3fS41Q5E7i0Fp3fBXxP");
    map.put("Drone", "spotify:playlist:6s9ixhTisn55r8AnCSZR0t");
    map.put("Drum and Bass", "spotify:playlist:068WHS0zOWsqvn2uIBYb5D");
    map.put("Dub", "spotify:playlist:3ObJ6Qra3CkV0gNCRTtK0c");
    map.put("Dub Techno", "spotify:playlist:51rpsmosMio12mduQcdGab");
    map.put("Dubstep", "spotify:playlist:6xo6Pr528QIucumzKcMXOu");
    map.put("Dungeon Synth", "spotify:playlist:4aRzldlGqM9aKHcyrzei7H");
    map.put("East Coast Hip Hop", "spotify:playlist:2uoCPhbyzjpEegs1oFAGSC");
    map.put("EBM", "spotify:playlist:2FjnktEvmPXr1mHUEyHvcI");
    map.put("EDM", "spotify:album:37bu5iGP8qyG50MbFUKbGi");
    map.put("Electric Blues", "spotify:playlist:2w4sSEvyPcJIEVYOoDjh2A");
    map.put("Electro", "spotify:playlist:37i9dQZF1DX3bH0P2uDnWA");
    map.put("Electro House", "spotify:playlist:317O0e8iWJLClLGDKtieRe");
    map.put("Electro Swing", "spotify:playlist:37i9dQZF1DX3bH0P2uDnWA");
    map.put("Electro-Funk", "spotify:playlist:6JfdrVJ75I9DxD7weU2riy");
    map.put("Electro-Industrial", "spotify:playlist:5cDlsZEzgKbAZLDsulgsg1");
    map.put("Electroclash", "spotify:playlist:2lvWEEJ981sRN5GQzBvayl");
    map.put("Electronic", "spotify:playlist:4sjsye4xyuVeYFQh0MJlMG");
    map.put("Electronic Rock", "spotify:playlist:1LfbMdDxqt8yJKwvTORcj8");
    map.put("Electronica", "spotify:playlist:09tdi9JkYgC7DP0XYBl4Az");
    map.put("Electronicore", "spotify:playlist:2v3eRn9vxfL0cSszKU8bK3");
    map.put("Electropop", "spotify:playlist:2a5i2ZtEXGKwVGL16J8N0p");
    map.put("Electropunk", "spotify:playlist:1KZDrVv1mAvRsXCnrEOlbK");
    map.put("Emo", "spotify:playlist:46Wwg8rve0z5kwLD9zdSqW");
    map.put("Emo Pop", "spotify:playlist:52DEJKt18LNbFy46ldKkfY");
    map.put("Emocore", "spotify:playlist:6uUYxFxIT4KtdAPdrPXz62");
    map.put("Enka", "spotify:playlist:37i9dQZF1DXbSv6m8hMdDm");
    map.put("Ethereal", "spotify:playlist:7pfwSM1dbcr9x1BZ3cZFc3");
    map.put("Euro House", "spotify:playlist:2818tC1Ba59cftJJqjWKZi");
    map.put("Eurobeat", "spotify:playlist:3ko904imRQAT3617CmthUo");
    map.put("Eurodance", "spotify:playlist:1SnFk0AhmCPZWW3QCAJXVD");
    map.put("Europop", "spotify:playlist:0gr8wuZtmPIiPLm1v7X0Py");
    map.put("Exotica", "spotify:playlist:4yLAdzYy1IbjvzX2lAauT0");
    map.put("Experimental", "spotify:playlist:5QZXKOWZUEbLkMmrSd9ucf");
    map.put("Experimental Rock", "spotify:playlist:3ckmiiqYb8UpAKlt9U8Q1V");
    map.put("Fado", "spotify:playlist:37i9dQZF1DXckxK48poYJh");
    map.put("Filk", "spotify:playlist:6TNFDNHyRauIZwwf3uGouY");
    map.put("Flamenco", "spotify:playlist:37i9dQZF1DWTfrrcwZAlVH");
    map.put("Folk", "spotify:playlist:2dJTclveMvEtslLgQb9LV3");
    map.put("Folk Metal", "spotify:playlist:4q91IJAnnO2IqpsAMXg6hM");
    map.put("Folk Pop", "spotify:playlist:37i9dQZF1DWXJyjYpHunCf");
    map.put("Folk Punk", "spotify:playlist:0pIaMAHnhG7RwQcotqtBif");
    map.put("Folk Rock", "spotify:playlist:37i9dQZF1DXat5j4Lk8UEj");
    map.put("Folktronica", "spotify:playlist:07Rr6y6VUYYYUGBkhDbVfi");
    map.put("Freak Folk", "spotify:playlist:4KgZ9ZN10RfjRxBYkj6tTK");
    map.put("Free Improvisation", "spotify:playlist:79QWJdCtq6ZLnbtOE0zQqz");
    map.put("Free Jazz", "spotify:playlist:3nxdNdIA45HbkTwzlqZjQ0");
    map.put("Funk", "spotify:playlist:37i9dQZF1DWWvhKV4FBciw");
    map.put("Funk Carioca", "spotify:playlist:7JPNYMANa0ajVhRoEL8S4e");
    map.put("Funk Metal", "spotify:playlist:6JqQli6WY1G043Mihy3MBp");
    map.put("Funk Rock", "spotify:playlist:37i9dQZF1DX23YPJntYMnh");
    map.put("Funk Soul", "spotify:playlist:6ftISEMV7Gy3couydcyBVa");
    map.put("Funky House", "spotify:playlist:111k2mfSlw3amm9JLiXtdZ");
    map.put("Fusion", "spotify:playlist:0BMBjPoQf4ij4B9rdqHUqd");
    map.put("Future Bass", "spotify:playlist:3Ivv8tT1eEt89ALFXDy59O");
    map.put("Future Garage", "spotify:playlist:4shq1hYsqbQvtxO7RCvvUZ");
    map.put("Future Jazz", "spotify:playlist:5ucvbFzIhehHuKYXH90jgW");
    map.put("Futurepop", "spotify:playlist:5mEQLf08cWbwGl5PIuVJKH");
    map.put("G-Funk", "spotify:playlist:5ioNili4SwzihmHwhExMt1");
    map.put("Gabber", "spotify:playlist:704SwtOBaXYcKWGyuQXezY");
    map.put("Gangsta Rap", "spotify:playlist:0jV3eMjHBzN21rIhKXWh2h");
    map.put("Garage", "spotify:playlist:2NxQcrfdRsBiSbLlSjInZ4");
    map.put("Garage House", "spotify:playlist:6qVxgWN5i6WAZBCaPggUXx");
    map.put("Garage Punk", "spotify:playlist:1TxpjojATLhG8JHRPVrD1T");
    map.put("Garage Rock", "spotify:playlist:37i9dQZF1DXbMYUPb05hjJ");
    map.put("Glam", "spotify:playlist:30sHrMoMyXuvkRM199y67h");
    map.put("Glam Metal", "spotify:playlist:6XrK1egRFz1hgXSO0X08QV");
    map.put("Glam Rock", "spotify:playlist:3LDsuqXWE1fuMHLUcog9A7");
    map.put("Glitch", "spotify:playlist:37i9dQZF1DWUraJYejk11q");
    map.put("Goa Trance", "spotify:playlist:79jQu5ar7xcBDsaLMhlVqE");
    map.put("Goregrind", "spotify:playlist:6kOigqG46UsxBFwMsX0ITH");
    map.put("Gospel", "spotify:playlist:43EKblqJqJtoGXvMbJgFK9");
    map.put("Gothic", "spotify:playlist:3mMx5HWEZWvagGcmDEhhzC");
    map.put("Gothic Metal", "spotify:playlist:76PSrknbBdEiQxvoinpYAm");
    map.put("Gothic Rock", "spotify:playlist:6wO6r6gDHiIbkiys13WC4r");
    map.put("Grebo", "spotify:playlist:1TRV5SyiYD4vPYMhq8kCao");
    map.put("Grime", "spotify:playlist:0GoqIgqiNL5Jk4FJIR0Bha");
    map.put("Grindcore", "spotify:playlist:0QWDu8Dk7DbloN8TLFWKgA");
    map.put("Groove Metal", "spotify:playlist:39P00J1dNOniJKCT8w0axJ");
    map.put("Group Sounds", "spotify:playlist:7wQoQ39naLDkAthQ93g5E2");
    map.put("Grunge", "spotify:playlist:32I2E1yJ3syR8VKn4hgfi7");
    map.put("Guanguanco", "spotify:playlist:7kRE1DOV053LyR1PqKPjKF");
    map.put("Guajira", "spotify:playlist:00GrCsSIvbMf0A9kD4ou5Z");
    map.put("Guaracha", "spotify:playlist:7LDpUAjCFTJVKPikrKVSJy");
    map.put("Happy Hardcore", "spotify:playlist:5awU7dImY2OenjRFfN8WKD");
    map.put("Hard Bop", "spotify:playlist:37i9dQZF1DWZZfLKhEkflI");
    map.put("Hard House", "spotify:playlist:75Go9Prcu58yw7qRNa1BG0");
    map.put("Hard Rock", "spotify:playlist:1GXRoQWlxTNQiMNkOe7RqA");
    map.put("Hard Trance", "spotify:playlist:2BZWmtIttwXVyHZJBYTiLc");
    map.put("Hardcore Hip Hop", "spotify:playlist:2GePkjnfbs1cDsr9LTNX4Y");
    map.put("Hardcore Punk", "spotify:playlist:7mCzIltN0jFQ54GH02HMsY");
    map.put("Hardcore Techno", "spotify:playlist:3RD8Fn6LNZUjkoQyC5nsR5");
    map.put("Hardstyle", "spotify:playlist:3bGSAHGYFEDxyEj7uXe0qq");
    map.put("Harsh Noise", "spotify:playlist:0l2w2LFR2iQ7BvlpI7dJeW");
    map.put("Harsh Noise Wall", "spotify:playlist:6FzxRA3fwleEDHhds50mwp");
    map.put("Hauntology", "spotify:playlist:37i9dQZF1DX9TOdl0GpvQm");
    map.put("Heavy Metal", "spotify:playlist:37i9dQZF1DX9qNs32fujYe");
    map.put("Heavy Psych", "spotify:playlist:0XBSDQ30QGXZDT8v1j53kR");
    map.put("Hi-NRG", "spotify:playlist:6Q6SnZPhJ7C0b7IeGyTjCe");
    map.put("Hindustani Classical", "spotify:playlist:37i9dQZF1DX6EUcyVKIE73");
    map.put("Hip Hop", "spotify:playlist:0FAb3s3yJArWnikZbEOO9p");
    map.put("Hip House", "spotify:playlist:7HMyxocigD89XyY2LlnBv1");
    map.put("Honky Tonk", "spotify:playlist:75vKOnfctBdBeVy1vru9FO");
    map.put("Hopepunk", "spotify:playlist:6ANd4HAHZrG2sWutmdz9k6");
    map.put("Horror Punk", "spotify:playlist:14BCCMC9mRAbcO7ArekxtD");
    map.put("Horrorcore", "spotify:playlist:365qMF6KqzjEPi3HnDz4Ji");
    map.put("House", "spotify:playlist:2otQLmbi8QWHjDfq3eL0DC");
    map.put("IDM", "spotify:playlist:6t93D5kmpPfeGwAm7VMej8");
    map.put("Illbient", "spotify:playlist:1UanuB6h9qWbhgBiPJGLz7");
    map.put("Indie", "spotify:playlist:3nHylKOqdjGHrgUewxIYPQ");
    map.put("Indie Folk", "spotify:playlist:5tOffZXVBFTMS7mkKQ3tpX");
    map.put("Indie Pop", "spotify:playlist:37i9dQZF1DWWEcRhUVtL8n");
    map.put("Indie Rock", "spotify:playlist:7MqwKicbWkSqg8NqTlRPoe");
    map.put("Indietronica", "spotify:playlist:2NAGzZNbDQHaOlXsb8Qsfr");
    map.put("Indorock", "spotify:playlist:0B0BVXm6UitjyX0xgQWm0J");
    map.put("Industrial", "spotify:playlist:6jOJQfjV34kbtcvuW04AmW");
    map.put("Industrial Metal", "spotify:playlist:37i9dQZF1DX29LQDcJ6Xy7");
    map.put("Industrial Musical", "spotify:playlist:75LZ7FJuB6dQzucBTrGMve");
    map.put("Industrial Rock", "spotify:playlist:4sKEgfrotpSzWv1TmDZdHn");
    map.put("Instrumental", "spotify:playlist:37i9dQZF1DX8QHI5qVTd7o");
    map.put("Instrumental Jazz", "spotify:playlist:1YJe9dmtfOWC2lKbIdJUWm");
    map.put("Instrumental Rock", "spotify:playlist:2uhsnHgI4F2eFyvoMHY0GR");
    map.put("Irish Folk", "spotify:playlist:3uQywS57rCK3AJTsaNLR0T");
    map.put("Italo-Disco", "spotify:playlist:0wnvXVgoJbXdeKogyHeYYE");
    map.put("J-Pop", "spotify:playlist:1NztXqTuhQY391AFxbqxGb");
    map.put("J-Rock", "spotify:playlist:37i9dQZF1DX6ntWKaOqGAp");
    map.put("Jazz", "spotify:playlist:37i9dQZF1DXbITWG1ZJKYt");
    map.put("Jazz Blues", "spotify:playlist:37i9dQZF1DX9GSZDbrndTa");
    map.put("Jazz Fusion", "spotify:playlist:3AGYHZ3tqmPv3Nek1dRv1g");
    map.put("Jazz Rap", "spotify:playlist:37i9dQZF1DX8Kgdykz6OKj");
    map.put("Jazz-Funk", "spotify:playlist:37i9dQZF1DWUb0uBnlJuTi");
    map.put("Joik", "spotify:playlist:0cZtEkCwL44fZKeTyyKYul");
    map.put("Jungle", "spotify:playlist:0ib9ehQcBIGYqOCU9NLG3r");
    map.put("K-Pop", "spotify:playlist:3ZdSe8w9NiQD7HEfopLm6s");
    map.put("Kawaii Metal", "spotify:playlist:658k8GMyBVGppLuC1Uaf18");
    map.put("Kayokyoku", "spotify:playlist:2tuj056xQClFI0YZpN7gnO");
    map.put("Kizomba", "spotify:playlist:6ofDyMnVdUqRcFUPYMxC8q");
    map.put("Klezmer", "spotify:playlist:3QyVmfHUatn9SHTo8HZvE4");
    map.put("Krautrock", "spotify:playlist:3aJUBDcbxvKqxWYsNSRt1M");
    map.put("Latin", "spotify:playlist:7vI0tN3yUn07dkK9T6p2pg");
    map.put("Latin Jazz", "spotify:playlist:37i9dQZF1DX661EjJOj3Tu");
    map.put("Latin Pop", "spotify:playlist:2kKzN3kRYDzBctlaWs7CP2");
    map.put("Latin Rock", "spotify:playlist:56xmLQEhImVw6ArBfiV6M1");
    map.put("Leftfield", "spotify:playlist:7rM48eZjlwXNAulxn0CijQ");
    map.put("Line dance", "spotify:playlist:6FTdGkHkgbsVD6rremhqA4");
    map.put("Lo-fi", "spotify:playlist:37i9dQZF1DWWQRwui0ExPn");
    map.put("Lo-fi Hip Hop", "spotify:playlist:0vvXsWCC9xrXsKd4FyS8kM");
    map.put("Lounge", "spotify:playlist:6P98cP6nMIlk4AWB4wPMJf");
    map.put("Lovers Rock", "spotify:playlist:65OBENZlHc6iz4JZCjjVbS");
    map.put("Luk Krung", "spotify:playlist:5PvkWa5FPuzjj8S0Wz4RZP");
    map.put("Luk Thung", "spotify:playlist:7qKLlcKPF6Agveaz547yr8");
    map.put("Madchester", "spotify:playlist:3NzyOUejNH37GnM15DjDh3");
    map.put("Mainstream Rock", "spotify:playlist:7qHqwTyF3ZRxekKs60hw3f");
    map.put("Maloya", "spotify:playlist:25bnmWrZPdYu7gvcHsQoKs");
    map.put("Mambo", "spotify:playlist:7kI7rA9v5pyi1FEPJQMzfD");
    map.put("Mandopop", "spotify:playlist:37i9dQZF1DXahjk49z40fT");
    map.put("Martial Industrial", "spotify:playlist:04CF8vJ7Ujcn8ovD4UMGhY");
    map.put("Maskanda", "spotify:playlist:2GS6kUl6w29jmchBOmTyn9");
    map.put("Math Rock", "spotify:playlist:4TI52y8ILsaz1ae9DjS9ns");
    map.put("Mathcore", "spotify:playlist:5IDUlMJWipaLZ8NZmaPB8E");
    map.put("Medieval", "spotify:playlist:2YIdQoOgxs0V7uHUdtwCLL");
    map.put("Melodic Black Metal", "spotify:playlist:61yilIg0cPykhuhkkuUyMo");
    map.put("Melodic Death Metal", "spotify:playlist:1tHYwCxSH8z2lTVkppX8n2");
    map.put("Melodic Metalcore", "spotify:playlist:54lNJU6qTUtMLKKoQ5rw6o");
    map.put("Melodic Rock", "spotify:playlist:2SODrzN47xcQXajLpfwZnQ");
    map.put("Melodic Trance", "spotify:playlist:03JQeIhJa8jiLYLmwL7mBT");
    map.put("Mento", "spotify:playlist:1IiCwlPlj1g4ICD4bHsAvG");
    map.put("Merengue", "spotify:playlist:64xuAv7kLBZOrY0eP0n5mP");
    map.put("Metal", "spotify:playlist:37i9dQZF1DXbj9Ksq4BAdj");
    map.put("Metalcore", "spotify:playlist:79QHayucQm6M4wUlUbhQNQ");
    map.put("Miami Bass", "spotify:playlist:4dxEgJnPkOynGeQOqX7ar6");
    map.put("Microhouse", "spotify:playlist:6FbDQuTcGT0IoZCtsbCISv");
    map.put("Milonga", "spotify:playlist:37i9dQZF1DXcCT9tm6fRIV");
    //might need to change
    map.put("Min'yo", "spotify:playlist:60xGxalMOlEdAvGsy8kdyN");
    map.put("Mincecore", "spotify:playlist:1icy0kY7RV6Dd3BiO0jmsa");
    map.put("Minimal", "spotify:playlist:4lTQ7NuDBOlerutHcPl0OG");
    map.put("Minimal Techno", "spotify:playlist:7Anb1HtKdhvK3Pb1d36f22");
    map.put("Minimal Wave", "spotify:playlist:1rWMBMuhp3H963mUqjFABI");
    map.put("Modern Blues", "spotify:playlist:37i9dQZF1DX7Y7BqFok9IQ");
    map.put("Modern Classical", "spotify:playlist:6RpbpgcOct72SmEKrUaEHd");
    map.put("Modern Country", "spotify:playlist:2dFTKmitgfqUXACL78Rb1s");
    map.put("Motown", "spotify:playlist:70M2GdJysbCKToAuZkWw7a");
    map.put("MPB", "spotify:playlist:7Lw7HvOElzXwf043GXYPlK");
    map.put("Musical", "spotify:playlist:37i9dQZF1DX3SzuIjjsy97");
    map.put("Neo Soul", "spotify:playlist:0r5ojPhjqGVq21oQR13UJy");
    map.put("Neo-Progressive Rock", "spotify:playlist:0wVJ7N4glrdkdnIYkXqMuG");
    map.put("Neo-Rockabilly", "spotify:playlist:6gzNCSnOItLOUqiOtNptvd");
    map.put("Neofolk", "spotify:playlist:4slkhHWFmRdmNpzRBswWI7");
    map.put("Nerdcore", "spotify:playlist:1i6TCylOQ10oOSEqno5YMg");
    map.put("Neurofunk", "spotify:playlist:7Dx1mjAUeV8ElB5FERajkl");
    map.put("New Age", "spotify:playlist:4Jm8LcX9B9AcwSn9d1ZpOE");
    map.put("New Jack Swing", "spotify:playlist:0dmInkymNnOTWvEFamSNzb");
    map.put("New Romantic", "spotify:playlist:46NBAikQVSevpdAJIr923P");
    map.put("New Wave", "spotify:playlist:6G1YyVYzXjAbZ32bOLx7Em");
    map.put("Nightcore", "spotify:playlist:6tUox0LhGWQVgvAp3BGT2A");
    map.put("No Wave", "spotify:playlist:5ip48U8kJFixBQYp6NJje8");
    map.put("Noise", "spotify:playlist:1V7RAjYrXWlsKiACmXqxLl");
    map.put("Noise Pop", "spotify:playlist:79vSZtZGJqNVs3KYInh42V");
    map.put("Noise Rock", "spotify:playlist:1V7RAjYrXWlsKiACmXqxLl");
    map.put("Noisecore", "spotify:playlist:4xF67EdvjTbNBrELLpXidp");
    map.put("Non-music", "spotify:playlist:6Ye9GVCgHFc7vMDFiR6QH2");
    map.put("Norteno", "spotify:playlist:37i9dQZF1DWZmwe0RTeFj4");
    map.put("Northern Soul", "spotify:playlist:37i9dQZF1DWUB4Jn6piqKS");
    map.put("Nu Disco", "spotify:playlist:0KUF6SR96VHkAK1DaKiSU0");
    map.put("Nu Jazz", "spotify:playlist:4biDlMZ2OCCEV73QTkZGqa");
    map.put("Nu Metal", "spotify:playlist:37i9dQZF1DXcfZ6moR6J0G");
    map.put("Nueva Cancion", "spotify:playlist:275lWKA1hb4trLqnjQWYx9");
    map.put("Occult Rock", "spotify:playlist:17sZgprqbWIU6m5NZUAB15");
    //might need changing
    map.put("Oi", "spotify:playlist:6nm5h4HsvQyB85b1Se6w8s");
    map.put("Old School Death Metal", "spotify:playlist:1X2Z3JRtq3Vwd94ZQ7k6Sf");
    map.put("Old-time", "spotify:playlist:3jDeMOgQjynSOb5JXcE6ar");
    map.put("Opera", "spotify:playlist:2PjVPkj4a9kBvQIXaZ6UUt");
    map.put("Orchestral", "spotify:playlist:4HTbud8g5tLMxAaP1gmtWv");
    map.put("Outlaw Country", "spotify:playlist:6G7ZJuBDKwjCrk9ftdu82D");
    map.put("P-Funk", "spotify:playlist:3ZBYo9jFqTTgevdak4vfEG");
    map.put("Pachanga", "spotify:playlist:4bbKrmeHyCk5PBoZ1MWmWL");
    map.put("Pagode", "spotify:playlist:37i9dQZF1DXchBFvKSUooB");
    map.put("Phonk", "spotify:playlist:6nx4VZCuStkId2YiISi7pZ");
    map.put("Polka", "spotify:playlist:1UVQytf2CAXtpDGh91jYTp");
    map.put("Pop", "spotify:playlist:6mtYuOxzl58vSGnEDtZ9uB");
    map.put("Pop Metal", "spotify:playlist:7BIlPLUxduiHzqbf6FBwvy");
    map.put("Pop Punk", "spotify:playlist:7By5485k7QSRLyDT15bdGB");
    map.put("Pop Rap", "spotify:playlist:4GbJRuMWmbr1to7F6EbcjZ");
    map.put("Pop Rock", "spotify:playlist:4l7qxiQpxDOaby1aKlteQr");
    map.put("Pop Soul", "spotify:playlist:37i9dQZF1DX7eEfahN5cwF");
    map.put("Pornogrind", "spotify:playlist:6kOigqG46UsxBFwMsX0ITH");
    map.put("Post-Bop", "spotify:playlist:38b3oUWLMqcEaJ6puqlnhZ");
    map.put("Post-Classical", "spotify:playlist:7azXYUCJVQ2R1lyY0sgq84");
    map.put("Post-Grunge", "spotify:playlist:37i9dQZF1DWVwr24yj95lH");
    map.put("Post-Hardcore", "spotify:playlist:25xipv7zYC6ovwBJlTCBrI");
    map.put("Post-Metal", "spotify:playlist:3GHBPKU7UdRzgYicknfIXc");
    map.put("Post-Punk", "spotify:playlist:01tvqPxen74Nz7TRz7kpSd");
    map.put("Post-Rock", "spotify:playlist:6MLuasIWQmRkM6qyGIb1uj");
    map.put("Power Electronics", "spotify:playlist:6cUMJj84Rvc07gSmqWMmKd");
    map.put("Power Metal", "spotify:playlist:1gxUTHGqYwYka43Mvn2hGZ");
    map.put("Power Pop", "spotify:playlist:37i9dQZF1DX5W4wuxak2hE");
    map.put("Powerviolence", "spotify:playlist:4SJlsd4gR9A8DTzRoMrFPl");
    map.put("Production Music", "spotify:playlist:2zBQDCqS3yaIsaxBUwpnid");
    map.put("Progressive", "spotify:playlist:4L1MWp0aSM8YEIOOBizPVu");
    map.put("Progressive Folk", "spotify:playlist:1fBm6Yo1qNQ533FEYBPjtZ");
    map.put("Progressive House", "spotify:playlist:5CMvAWTlDPdZnkleiTHyyo");
    map.put("Progressive Metal", "spotify:playlist:37i9dQZF1DX5wgKYQVRARv");
    map.put("Progressive Rock", "spotify:playlist:5wDPvicN1zmNIqcbK3psT0");
    map.put("Progressive Trance", "spotify:playlist:0HAkmXptQCY655WDljapXa");
    map.put("Psy-trance", "spotify:playlist:6bTaXLHYTrjxqtwfNp2q5r");
    map.put("Psychedelic", "spotify:playlist:5OcDUk5nAum1m6PH8fHslE");
    map.put("Psychedelic Folk", "spotify:playlist:6tOn5WueO2BlgajE1feBi9");
    map.put("Psychedelic Pop", "spotify:playlist:1mXIDhdIz15xRBHwU5uDCJ");
    map.put("Psychedelic Rock", "spotify:playlist:37i9dQZF1DWSDoVybeQisg");
    map.put("Psychobilly", "spotify:playlist:0H10cZ1mLE6sKy6EeYtbwx");
    map.put("Psytrance", "spotify:playlist:4dC6qS3rrFcseBiaec70v1");
    map.put("Punk", "spotify:playlist:37i9dQZF1DXd6tJtr4qeot");
    map.put("Punk Rock", "spotify:playlist:39sVxPTg7BKwrf2MfgrtcD");
    map.put("Qawwali", "spotify:playlist:4EtNeMUHkAHV6A0p5lN7tw");
    map.put("Queercore", "spotify:playlist:37i9dQZF1DXa3ll4rE48Mv");
    map.put("R&B", "spotify:playlist:4WdOOas9UoL3XjHEs57BTX");
    map.put("Ragga", "spotify:playlist:6pn0caqhVq5c3NRpvr8hHG");
    map.put("Ragga Hip-hop", "spotify:playlist:1P4f6pPcQSeuEfw83dU7th");
    map.put("Ragga Jungle", "spotify:playlist:0V742srw56x8PCuMxK3N4b");
    map.put("Ragtime", "spotify:playlist:4Dfe6YgDQtGYFSXGQPVy8a");
    map.put("Raï", "spotify:playlist:37i9dQZF1DXb7WmotStdsj");
    map.put("Ranchera", "spotify:playlist:523boqZle6pyYJYuAVQtAz");
    map.put("Rap Rock", "spotify:playlist:6VYEbaLnk6oKFjwHVgsV5R");
    map.put("Rapcore", "spotify:playlist:1ZfpWJ6Wv8vMemZ87qVFGY");
    map.put("Rave", "spotify:playlist:3ybZkcoA09pYekp0DIo1OP");
    map.put("Red Song", "spotify:playlist:1tRiGuJGjvHx1SliU3qOeH");
    map.put("Reggae", "spotify:playlist:37i9dQZF1DXbSbnqxMTGx9");
    map.put("Reggaeton", "spotify:playlist:03sDEv7FN58Mb9CJOs1Tgn");
    map.put("Rhythmic Noise", "spotify:playlist:5hyOzYh3w5wcliJTKhEPiC");
    map.put("Ritual Ambient", "spotify:playlist:1sJKFhnCYglVaDXtA2y7Ao");
    map.put("Rock", "spotify:playlist:37i9dQZF1DWXRqgorJj26U");
    map.put("Rock and Roll", "spotify:playlist:5qNHuEyfs4QHR8NI4oNaxE");
    map.put("Rockabilly", "spotify:playlist:37i9dQZF1DX0xLQsW8b5Zx");
    map.put("Rocksteady", "spotify:playlist:37i9dQZF1DXcYHsRTcIw5a");
    map.put("Romantic Classical", "spotify:playlist:786hH2BXw7n4SNg0MXuAio");
    map.put("Roots Reggae", "spotify:playlist:53iDeoyHodextygU8wpras");
    map.put("Rumba", "spotify:playlist:4QeTqdJtrfLLGT9vzqPCSX");
    map.put("Ryūkōka", "spotify:playlist:5qDufC8I9t3EVumsy0ZRAR");
    map.put("Salsa", "spotify:playlist:37i9dQZF1DX7SeoIaFyTmA");
    map.put("Samba", "spotify:playlist:2iiljY2f8tADeNzfQLUDWi");
    map.put("Schlager", "spotify:playlist:1J2vYkrZaRh9mPoRAiVVmU");
    map.put("Screamo", "spotify:playlist:37i9dQZF1DX8sGALGjOrTu");
    map.put("Shibuya-kei", "spotify:playlist:5wuLaZucmCLqdXF7CvsCo2");
    map.put("Shoegaze", "spotify:playlist:37i9dQZF1DX6ujZpAN0v9r");
    map.put("Singer-songwriter", "spotify:playlist:37i9dQZF1DWVHi578arQLF");
    map.put("Ska", "spotify:playlist:6mRRGF4klfgUzbD2ZKOCq0");
    map.put("Ska Punk", "spotify:playlist:5NmTCJa0Wx4urcsYYEhaaj");
    map.put("Skacore", "spotify:playlist:5U5GURR1JQnlJKwmnHkWJO");
    map.put("Slow Waltz", "spotify:playlist:5fI9FCkOfVpJs19hGUwsNv");
    map.put("Sludge Metal", "spotify:playlist:3kbzqWhOrhZFXME9PpL8dg");
    map.put("Smooth jazz", "spotify:playlist:37i9dQZF1DXdwTUxmGKrdN");
    map.put("Smooth soul", "spotify:playlist:4RwKm6R88xEx1ScjWhtByA");
    map.put("Soca", "spotify:playlist:6ADEIhrQYjvSrvL8Wo9rHK");
    map.put("Soft Rock", "spotify:playlist:37i9dQZF1DX6xOPeSOGone");
    map.put("Son Cubano", "spotify:playlist:3DZYs9PjtavyPR5CBj4gB0");
    map.put("Son Montuno", "spotify:playlist:2myAwVZH0I2Q3Tzvzj0ml7");
    map.put("Soul", "spotify:playlist:37i9dQZF1DWULEW2RfoSCi");
    map.put("Soul Jazz", "spotify:playlist:5jBndFJwNAKyLHVjwhDiql");
    map.put("Southern Rock", "spotify:playlist:1QKiEzhqqKwjsFQWuLPhmw");
    map.put("Southern Soul", "spotify:playlist:2lFMAUSybNcIKKpf7akny8");
  }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_music_browser);
      songTitle = (TextView) findViewById(R.id.songTitle);
      artistName = (TextView) findViewById(R.id.artist);
      songDuration = (ProgressBar) findViewById(R.id.songDuration);
      genrePlaying = (TextView) findViewById(R.id.genrePlaying);
      fillMusicMap(AllSet.music);
      //capitalizing so it matches music map keys
      for (String word : AllSet.genre10) {
        if (word.contains(" ")) {
          String[] words = word.split(" ");
          for (int n = 0; n < words.length; n++) {
            words[n] = words[n].substring(0,1).toUpperCase() + word.substring(1);
          }
        } else {
          word = word.substring(0,1).toUpperCase() + word.substring(1);
        }
      }
      for (String word : AllSet.genre5) {
        if (word.contains(" ")) {
          String[] words = word.split(" ");
          for (int n = 0; n < words.length; n++) {
            words[n] = words[n].substring(0,1).toUpperCase() + word.substring(1);
          }
        } else {
          word = word.substring(0,1).toUpperCase() + word.substring(1);
        }
      }
      for (String word : AllSet.genreNorm) {
        if (word.contains(" ")) {
          String[] words = word.split(" ");
          for (int n = 0; n < words.length; n++) {
            words[n] = words[n].substring(0,1).toUpperCase() + word.substring(1);
          }
        } else {
          word = word.substring(0,1).toUpperCase() + word.substring(1);
        }
      }
      for (String word : AllSet.genreN5) {
        if (word.contains(" ")) {
          String[] words = word.split(" ");
          for (int n = 0; n < words.length; n++) {
            words[n] = words[n].substring(0,1).toUpperCase() + word.substring(1);
          }
        } else {
          word = word.substring(0,1).toUpperCase() + word.substring(1);
        }
      }
      for (String word : AllSet.genreN10) {
        if (word.contains(" ")) {
          String[] words = word.split(" ");
          for (int n = 0; n < words.length; n++) {
            words[n] = words[n].substring(0,1).toUpperCase() + word.substring(1);
          }
        } else {
          word = word.substring(0,1).toUpperCase() + word.substring(1);
        }
      }
    }

    @Override
    protected void onStart() {
      super.onStart();
      // Set the connection parameters
      ConnectionParams connectionParams =
        new ConnectionParams.Builder(CLIENT_ID)
          .setRedirectUri(REDIRECT_URI)
          .showAuthView(true)
          .build();

      SpotifyAppRemote.connect(this, connectionParams,
        new Connector.ConnectionListener() {
        @Override
        public void onConnected(SpotifyAppRemote spotifyAppRemote) {
          mSpotifyAppRemote = spotifyAppRemote;
          Log.d("MainActivity", "Connected! Yay!");
          connected();
        }
        @Override
        public void onFailure(Throwable throwable) {
          Log.e("MainActivity", throwable.getMessage(), throwable);
        }
      });
    }

    private void connected() {
      //step 1. pick a category to pull from
      //step 2. pick a genre from the category array
      //step 3. get the uri of the genre from the map
      String genre = AllSet.generateGenre(QuestionsStart.MoodTracker);
      Log.d("MusicBrowser", genre + ": " + AllSet.music.get(genre));
      String playlistURI = AllSet.music.get(genre);
      mSpotifyAppRemote.getPlayerApi().play(playlistURI);

      // Subscribe to PlayerState
      mSpotifyAppRemote.getPlayerApi()
        .subscribeToPlayerState()
        .setEventCallback(playerState -> {
          final Track track = playerState.track;
          if (track != null) {
            songTitle.setText(track.name);
            artistName.setText(track.artist.name);
            genrePlaying.setText("Playing: " + genre);
            int trackDuration = (int) (track.duration/1000);
            songDuration.setMax(trackDuration);
            songDuration.setProgress(0);
            new Thread(new Runnable() {
              @Override
              public void run() {
                while (progressStatus < trackDuration) {
                  progressStatus += 1;
                  handler.post(new Runnable() {
                    public void run() {
                      songDuration.setProgress(progressStatus);
                    }
                  });
                  try {
                    Thread.sleep(1000);
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                }
              }
            }).start();
          }
        });
    }

    @Override
    protected void onStop() {
      super.onStop();
      SpotifyAppRemote.disconnect(mSpotifyAppRemote);
    }
}