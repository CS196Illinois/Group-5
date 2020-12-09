package com.example.mentallysound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;

import java.net.URI;
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
    public ImageView albumImage;

    private int progressStatus = 0;
    private Handler handler = new Handler();

    //ignore this, just putting genres with their uri's.
    //there is def a better way of doing this, but i couldnt find a better way lol
    public void fillMusicMap(Map<String, String> map) {
    map.put("blues", "spotify:playlist:37i9dQZF1DXd9rSDyQguIk");
    map.put("acoustic blues", "spotify:playlist:37i9dQZF1DX2iUghHXGIjj");
    map.put("african blues", "spotify:playlist:0cAGaeebKBsklVdo15q0mm");
    map.put("chicago blues", "spotify:playlist:37i9dQZF1DX4rGCw5bMNp1");
    map.put("acid house", "spotify:playlist:37i9dQZF1DXbwcS5W73XRr");
    map.put("acid jazz", "spotify:playlist:37i9dQZF1DWXHghfFFOaS6");
    map.put("acid rock", "spotify:playlist:68yp7nlOnkd4uUWfFHfH4U");
    map.put("acid techno", "spotify:playlist:50L3W8NaVU3d4bKlCPi6I9");
    map.put("acoustic rock", "spotify:playlist:37i9dQZF1DX0rCrO4CFRfM");
    map.put("afoxe", "spotify:playlist:2hDxDvoSKtMZ9ppI2As9Sw");
    map.put("afrobeat", "spotify:playlist:4HXMPRVKOAfzoUwws8fqHW");
    map.put("alternative country", "spotify:playlist:2RNMLu5C5v50RhdsGkJhwv");
    map.put("alternative dance", "spotify:playlist:1fDwuEZMWYaOBXmfW6PkdL");
    map.put("alternative folk", "spotify:playlist:0JqaKY09rGN6l1lpTE6eUI");
    map.put("alternative hip hop", "spotify:playlist:37i9dQZF1DWTggY0yqBxES");
    map.put("alternative metal", "spotify:playlist:3hMuAfaMcg9dWAwNTTuvXR");
    map.put("alternative pop", "spotify:playlist:1jaAEXF5ByxlouYzuAWL93");
    map.put("alternative punk", "spotify:playlist:4MilmjGiBljkbv0jxvObZ9");
    map.put("alternative rock", "spotify:playlist:0TCbzzrT932hpTAKdKj8wM");
    map.put("ambient", "spotify:playlist:1kqBP6eE24L0agNpnTIKtc");
    map.put("ambient dub", "spotify:playlist:2oLMPruXKXuoXpzI3NNq0k");
    map.put("ambient house", "spotify:playlist:4Z4CM5ZqeIVTHvIiDY7RFm");
    map.put("ambient techno", "spotify:playlist:49RyM6nIP0XFlwy69nUoz9");
    map.put("americana", "spotify:playlist:37i9dQZF1DX3gvEGcwdifU");
    map.put("anarcho-punk", "spotify:playlist:0Lx0gTeBFr7KQc321fB7Jr");
    map.put("aor", "spotify:playlist:05QRiiz6ujxGN2oZ0P51bI");
    map.put("arena rock", "spotify:playlist:1XAXDKMq2CB0XIIOEtQQEe");
    map.put("art pop", "spotify:playlist:5oqAgQxfCjjD5ejU76a8V3");
    map.put("art punk", "spotify:playlist:180M35eDbTmmTCZWuJf6QO");
    map.put("art rock", "spotify:playlist:42yCIEXNj7s1ZXWsKOSGEV");
    map.put("atmospheric black metal", "spotify:playlist:2F8BVYx9ECaVgogOk34MkV");
    map.put("audiobook", "spotify:playlist:3tFovso2iVD16RdqiLmRc4");
    map.put("avant-garde", "spotify:playlist:4FARCY3LRqvqR0Tm5wXQXi");
    map.put("avant-garde jazz", "spotify:playlist:72YNCrIywbtPaFgFSQNI6X");
    map.put("avant-garde metal", "spotify:playlist:5O93oZwfqsC6MauxEcJnw8");
    map.put("avant-garde pop", "spotify:playlist:7lyx5fUAVMkrONR9SjVzog");
    map.put("avant-prog", "spotify:playlist:1jREw9to20Chqt77bO1xAR");
    map.put("bachata", "spotify:playlist:5SqR3iQ1rvzjjB8vEPlF8d");
    map.put("ballad", "spotify:playlist:0950zENyu9Vtp15jGV4DbX");
    map.put("barbershop", "spotify:playlist:1xpEE49bS52ee6rxtl08va");
    map.put("baroque", "spotify:playlist:4DvteColbVCrs7iIgc4r6x");
    map.put("bass house", "spotify:playlist:6ViSThD6IjBMKBnmtzDipB");
    map.put("beat music", "spotify:playlist:1SiuqoeA1MCaq8YjfPqnN2");
    map.put("bebop", "spotify:playlist:41OUnh6TuhRS7FZlbXXgMw");
    map.put("bhangra", "spotify:playlist:37i9dQZF1DX3VNFqEPdDGw");
    map.put("big band", "spotify:playlist:2VMD3Kun96CVnYdog9MooY");
    map.put("big beat", "spotify:playlist:7gekAwUMfugfTC4hu1XA1v");
    map.put("black metal", "spotify:playlist:0nEi9oGlDjrSPzfk8SXhnt");
    map.put("blackened death metal", "spotify:playlist:4pB6QWOYa20lrH8jSnFU0x");
    map.put("blackgaze", "spotify:playlist:0tb4mctI0W8DgwlQzPx2Hd");
    map.put("blue-eyed soul", "spotify:playlist:744e4Vudqyau0nNMU2nev7");
    map.put("bluegrass", "spotify:playlist:7fAm3STfjNSuUpvDxzb9eJ");
    map.put("blues rock", "spotify:playlist:56dbowk1V5ycS5jW7DSvi5");
    map.put("bolero", "spotify:playlist:37i9dQZF1DX7YVXCfBlE4D");
    map.put("bolero son", "spotify:playlist:6AhFkHpGWmiAcLn55wPAMi");
    map.put("bongo flava", "spotify:playlist:37i9dQZF1DX3EbcelyrZPd");
    map.put("boogie rock", "spotify:playlist:60cfm5BxTEPrvjHl8ISaRk");
    map.put("boogie-woogie", "spotify:playlist:5wulGZDljjUBtrTyukjKjG");
    map.put("boom bap", "spotify:playlist:4BUq6stVvp7RxXeQ5gmTuC");
    map.put("bossa nova", "spotify:playlist:37i9dQZF1DX4AyFl3yqHeK");
    map.put("bounce", "spotify:playlist:0NB63NRtbvrEw0yfGAuAGI");
    map.put("breakbeat", "spotify:playlist:2Y49JuVf9VWLWfIijSFZmz");
    map.put("breakbeat hardcore", "spotify:playlist:1OJpU7wxI4P7QFHhzNUSwg");
    map.put("breakcore", "spotify:playlist:15PWiNpwy0vFmJIWiStE7M");
    map.put("breaks", "spotify:playlist:0S8VUQb3yrM7VUd2JLBO7p");
    map.put("britpop", "spotify:playlist:1rlBSxR4WryLssBr4kiZ77");
    map.put("brokenbeat", "spotify:playlist:2b8IUBxy00wteVzPCpiLbn");
    map.put("brostep", "spotify:playlist:4e1TfVuPeumCSZoA8KRq48");
    map.put("brutal death metal", "spotify:playlist:5iZh1symrVbgqWNwXACTZ2");
    map.put("bubblegum pop", "spotify:playlist:1pzWKmDCTcDGBQUyYdi8c3");
    map.put("cajun", "spotify:playlist:2KmefvmYGgMH8fvkHLWHse");
    map.put("calypso", "spotify:playlist:1UmzDFJhtZvo9yGjxcfnz0");
    map.put("candombe", "spotify:playlist:6zT60dPcpA3v83eoOVcqZG");
    map.put("canterbury scene", "spotify:playlist:0cKGEchDZyIGPp7XulCZO4");
    map.put("cantopop", "spotify:playlist:37i9dQZF1DWW6TogMpx8Q2");
    map.put("carnatic classical", "spotify:album:1HlSWm3iBAwX0i2I0JXFCS");
    map.put("celtic", "spotify:playlist:5OJdvyCQpZKdNQNUnOYYzk");
    map.put("celtic punk", "spotify:playlist:37i9dQZF1DXb1IUaS6F7Z8");
    map.put("chachacha", "spotify:playlist:5sGKJFi92t7IImZxQvFjPQ");
    map.put("chamber pop", "spotify:playlist:6jFw8tgtJFwKW7rzh2mEZD");
    map.put("champeta", "spotify:playlist:3XUcnPXjvejUdKk6mDD41a");
    map.put("changui", "spotify:playlist:3O0Un5vDr3f6YxZelz7prs");
    map.put("chanson", "spotify:playlist:3gEVAjHZLfejDhLrxVfGNj");
    map.put("chillout", "spotify:playlist:7ozIozDp260fjNOZy1yzRG");
    map.put("chiptune", "spotify:playlist:137E6KBEACEjDWve3W6cgq");
    map.put("chopped and screwed", "spotify:playlist:785iwTcs9z8QLmakRVFTGA");
    map.put("christian rock", "spotify:playlist:37i9dQZF1DX0N57moxx9BL");
    map.put("christmas music", "spotify:playlist:37i9dQZF1DX0Yxoavh5qJV");
    map.put("chutney", "spotify:playlist:3ovpLfmghnPprJfziTlqM7");
    map.put("city pop", "spotify:playlist:5ZEY1gtao9eGzvAD3KEh2x");
    map.put("classic blues", "spotify:playlist:37i9dQZF1DXd9rSDyQguIk");
    map.put("classic country", "spotify:playlist:6U8AxQBEDDW4GlzbqkoRMV");
    map.put("classic jazz", "spotify:playlist:3ZZBSpcUJ4Gi2uzHjDDpC3");
    map.put("classic rock", "spotify:playlist:6TeyryiZ2UEf3CbLXyztFA");
    map.put("classical", "spotify:playlist:37i9dQZF1DWWEJlAGA9gs0");
    map.put("classical crossover", "spotify:playlist:4G5JSCSOc9zHB6VbuqxiUq");
    map.put("club", "spotify:playlist:2qbOF1dJ0wBmnnLMIusa9p");
    map.put("comedy", "spotify:playlist:4rYEENJRLjWDFfgChdL4kl");
    map.put("comedy rock", "spotify:playlist:6LsciadnOQJFiQRSBI5LE2");
    map.put("compas", "spotify:playlist:51ZF6pPpYyeSMt090OzF39");
    map.put("complextro", "spotify:playlist:1W9U7NkU9YVOdYVMaIi9Ty");
    map.put("conscious hip hop", "spotify:playlist:13lAvSxOw1uCX9wqeVFykE");
    map.put("contemporary christian", "spotify:playlist:1h5ojikWVCSpYGoEbhDCgG");
    map.put("contemporary classical", "spotify:playlist:1rrTvJzJE8dBFdnzMfPwlS");
    map.put("contemporary folk", "spotify:playlist:37i9dQZF1DX1nqy6pUAoUv");
    map.put("contemporary gospel", "spotify:playlist:3Jfim4bWxkbjXHhgApBskF");
    map.put("contemporary jazz", "spotify:playlist:4uhchdjV19aiEFrEYmdHQS");
    map.put("contemporary r&b", "spotify:playlist:4BUmWtByLRpdEYomlLTiXi");
    //double check contra
    map.put("contra", "spotify:playlist:32I2E1yJ3syR8VKn4hgfi7");
    map.put("cool jazz", "spotify:playlist:37i9dQZF1DWTG7FCdM1HiA");
    map.put("country", "spotify:playlist:6nU0t33tQA2i0qTI5HiyRV");
    map.put("country blues", "spotify:playlist:37i9dQZF1DWWEJlAGA9gs0");
    map.put("country folk", "spotify:playlist:5XgcdeBzILhrcQPLF2Loja");
    map.put("country pop", "spotify:playlist:40Ktnv37eFQOHN2RLolHEw");
    map.put("country rock", "spotify:playlist:0v4DvG8cBpHGOBOxVuVvgV");
    map.put("coupe-decale", "spotify:playlist:37i9dQZF1DX0z1epx5KTtS");
    map.put("cowpunk", "spotify:playlist:6VbtVCyMUz0eoiWGl2r6g7");
    map.put("crossover prog", "spotify:playlist:2hxu7BD7LZ3TSdEf8VN9xS");
    map.put("Crust Punk", "spotify:playlist:1M4UmyNnokZRdeAmBEaftG");
    map.put("cumbia", "spotify:playlist:3pwEllWmQJajyllqFg6jTF");
    map.put("cumbia villera", "spotify:playlist:7ukAyJrKU3ZiLZBGz4vueH");
    map.put("cyberpunk", "spotify:playlist:3To7rpikLDGb0uUnbFCBfQ");
    map.put("d-beat", "spotify:playlist:5EoC9ERI2yecvjGax0AXwj");
    map.put("dance", "spotify:playlist:37i9dQZF1DX0BcQWzuB7ZO");
    map.put("dance-pop", "spotify:playlist:37i9dQZF1DWZQaaqNMbbXa");
    map.put("dance-punk", "spotify:playlist:6c6EDGbcWdOSUvTaaUENyg");
    map.put("dancehall", "spotify:playlist:6RMoIh0N6NPokXLKV6AyUX");
    map.put("dansband", "spotify:playlist:37i9dQZF1DX5aniZvGQsXc");
    map.put("dark ambient", "spotify:playlist:4X7woiZ7EjZaLi9ZkDDOXk");
    map.put("dark electro", "spotify:playlist:5arKLuU39VKym4leePpdpA");
    map.put("dark folk", "spotify:playlist:4oort7dQ9nx9uCglEuLIRy");
    map.put("dark wave", "spotify:playlist:2OKRPDYytFbe5FmPlwqPmP");
    map.put("death metal", "spotify:playlist:2vivknVOeJD7BUYnnuztrE");
    map.put("death-doom metal", "spotify:playlist:3EvU1abnwG5p8RQkv6aPnG");
    map.put("deathcore", "spotify:playlist:37i9dQZF1DX1cJWWyylDuw");
    map.put("deathgrind", "spotify:playlist:7eFqFSYDm17u1nUaSu7fg2");
    map.put("deathrock", "spotify:playlist:7DJy1Li1329qvqe1ex7wUB");
    map.put("deep House", "spotify:playlist:6vDGVr652ztNWKZuHvsFvx");
    map.put("delta Blues", "spotify:playlist:37i9dQZF1DWSTHVqvNCwNq");
    map.put("descarga", "spotify:playlist:32z94KC6rQj4D2O9VP8kxo");
    map.put("desert rock", "spotify:playlist:1DbwgQvTu3dehBIXzT45YN");
    map.put("detroit techno", "spotify:playlist:37i9dQZF1DX1GT5IIzDqMe");
    map.put("digital hardcore", "spotify:playlist:2fqbzU1sBBZ3gW61Q7qYhp");
    map.put("disco", "spotify:playlist:2PzF3urLEgnQs4cmokAVHX");
    map.put("doo-wop", "spotify:playlist:5QjMzXNIWDAj3vgUsgl7zH");
    map.put("doom metal", "spotify:playlist:5Lzif2bIMW8RiRLtbYJHU0");
    map.put("downtempo", "spotify:playlist:03mUE11LSpb7eePR2ck5P4");
    map.put("dream pop", "spotify:playlist:6tdYRrSvLhfkETS8cJKMTM");
    map.put("drill", "spotify:playlist:6AF3nsX31HBnZNkyj2sFXy");
    map.put("drill and bass", "spotify:playlist:2xv3fS41Q5E7i0Fp3fBXxP");
    map.put("drone", "spotify:playlist:6s9ixhTisn55r8AnCSZR0t");
    map.put("drum and bass", "spotify:playlist:068WHS0zOWsqvn2uIBYb5D");
    map.put("dub", "spotify:playlist:3ObJ6Qra3CkV0gNCRTtK0c");
    map.put("dub techno", "spotify:playlist:51rpsmosMio12mduQcdGab");
    map.put("dubstep", "spotify:playlist:6xo6Pr528QIucumzKcMXOu");
    map.put("dungeon synth", "spotify:playlist:4aRzldlGqM9aKHcyrzei7H");
    map.put("east coast hip hop", "spotify:playlist:2uoCPhbyzjpEegs1oFAGSC");
    map.put("ebm", "spotify:playlist:2FjnktEvmPXr1mHUEyHvcI");
    map.put("edm", "spotify:album:37bu5iGP8qyG50MbFUKbGi");
    map.put("electric blues", "spotify:playlist:2w4sSEvyPcJIEVYOoDjh2A");
    map.put("electro", "spotify:playlist:37i9dQZF1DX3bH0P2uDnWA");
    map.put("electro house", "spotify:playlist:317O0e8iWJLClLGDKtieRe");
    map.put("electro swing", "spotify:playlist:37i9dQZF1DX3bH0P2uDnWA");
    map.put("electro-funk", "spotify:playlist:6JfdrVJ75I9DxD7weU2riy");
    map.put("electro-industrial", "spotify:playlist:5cDlsZEzgKbAZLDsulgsg1");
    map.put("electroclash", "spotify:playlist:2lvWEEJ981sRN5GQzBvayl");
    map.put("electronic", "spotify:playlist:4sjsye4xyuVeYFQh0MJlMG");
    map.put("electronic rock", "spotify:playlist:1LfbMdDxqt8yJKwvTORcj8");
    map.put("electronica", "spotify:playlist:09tdi9JkYgC7DP0XYBl4Az");
    map.put("electronicore", "spotify:playlist:2v3eRn9vxfL0cSszKU8bK3");
    map.put("electropop", "spotify:playlist:2a5i2ZtEXGKwVGL16J8N0p");
    map.put("electropunk", "spotify:playlist:1KZDrVv1mAvRsXCnrEOlbK");
    map.put("emo", "spotify:playlist:46Wwg8rve0z5kwLD9zdSqW");
    map.put("emo pop", "spotify:playlist:52DEJKt18LNbFy46ldKkfY");
    map.put("emocore", "spotify:playlist:6uUYxFxIT4KtdAPdrPXz62");
    map.put("enka", "spotify:playlist:37i9dQZF1DXbSv6m8hMdDm");
    map.put("ethereal", "spotify:playlist:7pfwSM1dbcr9x1BZ3cZFc3");
    map.put("euro house", "spotify:playlist:2818tC1Ba59cftJJqjWKZi");
    map.put("eurobeat", "spotify:playlist:3ko904imRQAT3617CmthUo");
    map.put("eurodance", "spotify:playlist:1SnFk0AhmCPZWW3QCAJXVD");
    map.put("europop", "spotify:playlist:0gr8wuZtmPIiPLm1v7X0Py");
    map.put("exotica", "spotify:playlist:4yLAdzYy1IbjvzX2lAauT0");
    map.put("experimental", "spotify:playlist:5QZXKOWZUEbLkMmrSd9ucf");
    map.put("experimental rock", "spotify:playlist:3ckmiiqYb8UpAKlt9U8Q1V");
    map.put("fado", "spotify:playlist:37i9dQZF1DXckxK48poYJh");
    map.put("filk", "spotify:playlist:6TNFDNHyRauIZwwf3uGouY");
    map.put("flamenco", "spotify:playlist:37i9dQZF1DWTfrrcwZAlVH");
    map.put("folk", "spotify:playlist:2dJTclveMvEtslLgQb9LV3");
    map.put("folk metal", "spotify:playlist:4q91IJAnnO2IqpsAMXg6hM");
    map.put("folk pop", "spotify:playlist:37i9dQZF1DWXJyjYpHunCf");
    map.put("folk punk", "spotify:playlist:0pIaMAHnhG7RwQcotqtBif");
    map.put("folk rock", "spotify:playlist:37i9dQZF1DXat5j4Lk8UEj");
    map.put("folktronica", "spotify:playlist:07Rr6y6VUYYYUGBkhDbVfi");
    map.put("freak folk", "spotify:playlist:4KgZ9ZN10RfjRxBYkj6tTK");
    map.put("free improvisation", "spotify:playlist:79QWJdCtq6ZLnbtOE0zQqz");
    map.put("free jazz", "spotify:playlist:3nxdNdIA45HbkTwzlqZjQ0");
    map.put("funk", "spotify:playlist:37i9dQZF1DWWvhKV4FBciw");
    map.put("funk carioca", "spotify:playlist:7JPNYMANa0ajVhRoEL8S4e");
    map.put("funk metal", "spotify:playlist:6JqQli6WY1G043Mihy3MBp");
    map.put("funk rock", "spotify:playlist:37i9dQZF1DX23YPJntYMnh");
    map.put("funk soul", "spotify:playlist:6ftISEMV7Gy3couydcyBVa");
    map.put("funky house", "spotify:playlist:111k2mfSlw3amm9JLiXtdZ");
    map.put("fusion", "spotify:playlist:0BMBjPoQf4ij4B9rdqHUqd");
    map.put("future bass", "spotify:playlist:3Ivv8tT1eEt89ALFXDy59O");
    map.put("future garage", "spotify:playlist:4shq1hYsqbQvtxO7RCvvUZ");
    map.put("future jazz", "spotify:playlist:5ucvbFzIhehHuKYXH90jgW");
    map.put("futurepop", "spotify:playlist:5mEQLf08cWbwGl5PIuVJKH");
    map.put("g-funk", "spotify:playlist:5ioNili4SwzihmHwhExMt1");
    map.put("gabber", "spotify:playlist:704SwtOBaXYcKWGyuQXezY");
    map.put("gangsta rap", "spotify:playlist:0jV3eMjHBzN21rIhKXWh2h");
    map.put("garage", "spotify:playlist:2NxQcrfdRsBiSbLlSjInZ4");
    map.put("garage house", "spotify:playlist:6qVxgWN5i6WAZBCaPggUXx");
    map.put("garage punk", "spotify:playlist:1TxpjojATLhG8JHRPVrD1T");
    map.put("garage rock", "spotify:playlist:37i9dQZF1DXbMYUPb05hjJ");
    map.put("glam", "spotify:playlist:30sHrMoMyXuvkRM199y67h");
    map.put("glam metal", "spotify:playlist:6XrK1egRFz1hgXSO0X08QV");
    map.put("glam rock", "spotify:playlist:3LDsuqXWE1fuMHLUcog9A7");
    map.put("glitch", "spotify:playlist:37i9dQZF1DWUraJYejk11q");
    map.put("goa trance", "spotify:playlist:79jQu5ar7xcBDsaLMhlVqE");
    map.put("goregrind", "spotify:playlist:6kOigqG46UsxBFwMsX0ITH");
    map.put("gospel", "spotify:playlist:43EKblqJqJtoGXvMbJgFK9");
    map.put("gothic", "spotify:playlist:3mMx5HWEZWvagGcmDEhhzC");
    map.put("gothic metal", "spotify:playlist:76PSrknbBdEiQxvoinpYAm");
    map.put("gothic rock", "spotify:playlist:6wO6r6gDHiIbkiys13WC4r");
    map.put("grebo", "spotify:playlist:1TRV5SyiYD4vPYMhq8kCao");
    map.put("grime", "spotify:playlist:0GoqIgqiNL5Jk4FJIR0Bha");
    map.put("grindcore", "spotify:playlist:0QWDu8Dk7DbloN8TLFWKgA");
    map.put("groove metal", "spotify:playlist:39P00J1dNOniJKCT8w0axJ");
    map.put("group sounds", "spotify:playlist:7wQoQ39naLDkAthQ93g5E2");
    map.put("grunge", "spotify:playlist:32I2E1yJ3syR8VKn4hgfi7");
    map.put("guanguanco", "spotify:playlist:7kRE1DOV053LyR1PqKPjKF");
    map.put("guajira", "spotify:playlist:00GrCsSIvbMf0A9kD4ou5Z");
    map.put("guaracha", "spotify:playlist:7LDpUAjCFTJVKPikrKVSJy");
    map.put("happy hardcore", "spotify:playlist:5awU7dImY2OenjRFfN8WKD");
    map.put("hard bop", "spotify:playlist:37i9dQZF1DWZZfLKhEkflI");
    map.put("hard house", "spotify:playlist:75Go9Prcu58yw7qRNa1BG0");
    map.put("hard rock", "spotify:playlist:1GXRoQWlxTNQiMNkOe7RqA");
    map.put("hard trance", "spotify:playlist:2BZWmtIttwXVyHZJBYTiLc");
    map.put("hardcore hip hop", "spotify:playlist:2GePkjnfbs1cDsr9LTNX4Y");
    map.put("hardcore punk", "spotify:playlist:7mCzIltN0jFQ54GH02HMsY");
    map.put("hardcore techno", "spotify:playlist:3RD8Fn6LNZUjkoQyC5nsR5");
    map.put("hardstyle", "spotify:playlist:3bGSAHGYFEDxyEj7uXe0qq");
    map.put("harsh noise", "spotify:playlist:0l2w2LFR2iQ7BvlpI7dJeW");
    map.put("harsh noise wall", "spotify:playlist:6FzxRA3fwleEDHhds50mwp");
    map.put("hauntology", "spotify:playlist:37i9dQZF1DX9TOdl0GpvQm");
    map.put("heavy metal", "spotify:playlist:37i9dQZF1DX9qNs32fujYe");
    map.put("heavy psych", "spotify:playlist:0XBSDQ30QGXZDT8v1j53kR");
    map.put("hi-nrg", "spotify:playlist:6Q6SnZPhJ7C0b7IeGyTjCe");
    map.put("hindustani classical", "spotify:playlist:37i9dQZF1DX6EUcyVKIE73");
    map.put("hip hop", "spotify:playlist:0FAb3s3yJArWnikZbEOO9p");
    map.put("hip house", "spotify:playlist:7HMyxocigD89XyY2LlnBv1");
    map.put("honky tonk", "spotify:playlist:75vKOnfctBdBeVy1vru9FO");
    map.put("hopepunk", "spotify:playlist:6ANd4HAHZrG2sWutmdz9k6");
    map.put("horror punk", "spotify:playlist:14BCCMC9mRAbcO7ArekxtD");
    map.put("horrorcore", "spotify:playlist:365qMF6KqzjEPi3HnDz4Ji");
    map.put("house", "spotify:playlist:2otQLmbi8QWHjDfq3eL0DC");
    map.put("idm", "spotify:playlist:6t93D5kmpPfeGwAm7VMej8");
    map.put("illbient", "spotify:playlist:1UanuB6h9qWbhgBiPJGLz7");
    map.put("indie", "spotify:playlist:3nHylKOqdjGHrgUewxIYPQ");
    map.put("indie folk", "spotify:playlist:5tOffZXVBFTMS7mkKQ3tpX");
    map.put("indie pop", "spotify:playlist:37i9dQZF1DWWEcRhUVtL8n");
    map.put("indie rock", "spotify:playlist:7MqwKicbWkSqg8NqTlRPoe");
    map.put("indietronica", "spotify:playlist:2NAGzZNbDQHaOlXsb8Qsfr");
    map.put("indorock", "spotify:playlist:0B0BVXm6UitjyX0xgQWm0J");
    map.put("industrial", "spotify:playlist:6jOJQfjV34kbtcvuW04AmW");
    map.put("industrial metal", "spotify:playlist:37i9dQZF1DX29LQDcJ6Xy7");
    map.put("industrial musical", "spotify:playlist:75LZ7FJuB6dQzucBTrGMve");
    map.put("industrial rock", "spotify:playlist:4sKEgfrotpSzWv1TmDZdHn");
    map.put("instrumental", "spotify:playlist:37i9dQZF1DX8QHI5qVTd7o");
    map.put("instrumental jazz", "spotify:playlist:1YJe9dmtfOWC2lKbIdJUWm");
    map.put("instrumental rock", "spotify:playlist:2uhsnHgI4F2eFyvoMHY0GR");
    map.put("irish folk", "spotify:playlist:3uQywS57rCK3AJTsaNLR0T");
    map.put("italo-disco", "spotify:playlist:0wnvXVgoJbXdeKogyHeYYE");
    map.put("j-pop", "spotify:playlist:1NztXqTuhQY391AFxbqxGb");
    map.put("j-rock", "spotify:playlist:37i9dQZF1DX6ntWKaOqGAp");
    map.put("jazz", "spotify:playlist:37i9dQZF1DXbITWG1ZJKYt");
    map.put("jazz blues", "spotify:playlist:37i9dQZF1DX9GSZDbrndTa");
    map.put("jazz fusion", "spotify:playlist:3AGYHZ3tqmPv3Nek1dRv1g");
    map.put("jazz rap", "spotify:playlist:37i9dQZF1DX8Kgdykz6OKj");
    map.put("jazz-funk", "spotify:playlist:37i9dQZF1DWUb0uBnlJuTi");
    map.put("joik", "spotify:playlist:0cZtEkCwL44fZKeTyyKYul");
    map.put("jungle", "spotify:playlist:0ib9ehQcBIGYqOCU9NLG3r");
    map.put("k-Pop", "spotify:playlist:3ZdSe8w9NiQD7HEfopLm6s");
    map.put("kawaii metal", "spotify:playlist:658k8GMyBVGppLuC1Uaf18");
    map.put("kayokyoku", "spotify:playlist:2tuj056xQClFI0YZpN7gnO");
    map.put("kizomba", "spotify:playlist:6ofDyMnVdUqRcFUPYMxC8q");
    map.put("klezmer", "spotify:playlist:3QyVmfHUatn9SHTo8HZvE4");
    map.put("krautrock", "spotify:playlist:3aJUBDcbxvKqxWYsNSRt1M");
    map.put("latin", "spotify:playlist:7vI0tN3yUn07dkK9T6p2pg");
    map.put("latin jazz", "spotify:playlist:37i9dQZF1DX661EjJOj3Tu");
    map.put("latin pop", "spotify:playlist:2kKzN3kRYDzBctlaWs7CP2");
    map.put("latin rock", "spotify:playlist:56xmLQEhImVw6ArBfiV6M1");
    map.put("leftfield", "spotify:playlist:7rM48eZjlwXNAulxn0CijQ");
    map.put("line dance", "spotify:playlist:6FTdGkHkgbsVD6rremhqA4");
    map.put("lo-fi", "spotify:playlist:37i9dQZF1DWWQRwui0ExPn");
    map.put("lo-fi hip hop", "spotify:playlist:0vvXsWCC9xrXsKd4FyS8kM");
    map.put("lounge", "spotify:playlist:6P98cP6nMIlk4AWB4wPMJf");
    map.put("lovers rock", "spotify:playlist:65OBENZlHc6iz4JZCjjVbS");
    map.put("luk krung", "spotify:playlist:5PvkWa5FPuzjj8S0Wz4RZP");
    map.put("luk thung", "spotify:playlist:7qKLlcKPF6Agveaz547yr8");
    map.put("madchester", "spotify:playlist:3NzyOUejNH37GnM15DjDh3");
    map.put("mainstream rock", "spotify:playlist:7qHqwTyF3ZRxekKs60hw3f");
    map.put("maloya", "spotify:playlist:25bnmWrZPdYu7gvcHsQoKs");
    map.put("mambo", "spotify:playlist:7kI7rA9v5pyi1FEPJQMzfD");
    map.put("mandopop", "spotify:playlist:37i9dQZF1DXahjk49z40fT");
    map.put("martial industrial", "spotify:playlist:04CF8vJ7Ujcn8ovD4UMGhY");
    map.put("maskanda", "spotify:playlist:2GS6kUl6w29jmchBOmTyn9");
    map.put("math rock", "spotify:playlist:4TI52y8ILsaz1ae9DjS9ns");
    map.put("mathcore", "spotify:playlist:5IDUlMJWipaLZ8NZmaPB8E");
    map.put("medieval", "spotify:playlist:2YIdQoOgxs0V7uHUdtwCLL");
    map.put("melodic black metal", "spotify:playlist:61yilIg0cPykhuhkkuUyMo");
    map.put("melodic death metal", "spotify:playlist:1tHYwCxSH8z2lTVkppX8n2");
    map.put("melodic metalcore", "spotify:playlist:54lNJU6qTUtMLKKoQ5rw6o");
    map.put("melodic rock", "spotify:playlist:2SODrzN47xcQXajLpfwZnQ");
    map.put("melodic trance", "spotify:playlist:03JQeIhJa8jiLYLmwL7mBT");
    map.put("mento", "spotify:playlist:1IiCwlPlj1g4ICD4bHsAvG");
    map.put("merengue", "spotify:playlist:64xuAv7kLBZOrY0eP0n5mP");
    map.put("metal", "spotify:playlist:37i9dQZF1DXbj9Ksq4BAdj");
    map.put("metalcore", "spotify:playlist:79QHayucQm6M4wUlUbhQNQ");
    map.put("miami bass", "spotify:playlist:4dxEgJnPkOynGeQOqX7ar6");
    map.put("microhouse", "spotify:playlist:6FbDQuTcGT0IoZCtsbCISv");
    map.put("milonga", "spotify:playlist:37i9dQZF1DXcCT9tm6fRIV");
    //might need to change
    map.put("min'yo", "spotify:playlist:60xGxalMOlEdAvGsy8kdyN");
    map.put("mincecore", "spotify:playlist:1icy0kY7RV6Dd3BiO0jmsa");
    map.put("minimal", "spotify:playlist:4lTQ7NuDBOlerutHcPl0OG");
    map.put("minimal techno", "spotify:playlist:7Anb1HtKdhvK3Pb1d36f22");
    map.put("minimal wave", "spotify:playlist:1rWMBMuhp3H963mUqjFABI");
    map.put("modern blues", "spotify:playlist:37i9dQZF1DX7Y7BqFok9IQ");
    map.put("modern classical", "spotify:playlist:6RpbpgcOct72SmEKrUaEHd");
    map.put("modern country", "spotify:playlist:2dFTKmitgfqUXACL78Rb1s");
    map.put("motown", "spotify:playlist:70M2GdJysbCKToAuZkWw7a");
    map.put("mpb", "spotify:playlist:7Lw7HvOElzXwf043GXYPlK");
    map.put("musical", "spotify:playlist:37i9dQZF1DX3SzuIjjsy97");
    map.put("neo soul", "spotify:playlist:0r5ojPhjqGVq21oQR13UJy");
    map.put("neo-progressive rock", "spotify:playlist:0wVJ7N4glrdkdnIYkXqMuG");
    map.put("neo-rockabilly", "spotify:playlist:6gzNCSnOItLOUqiOtNptvd");
    map.put("neofolk", "spotify:playlist:4slkhHWFmRdmNpzRBswWI7");
    map.put("nerdcore", "spotify:playlist:1i6TCylOQ10oOSEqno5YMg");
    map.put("neurofunk", "spotify:playlist:7Dx1mjAUeV8ElB5FERajkl");
    map.put("new age", "spotify:playlist:4Jm8LcX9B9AcwSn9d1ZpOE");
    map.put("new jack swing", "spotify:playlist:0dmInkymNnOTWvEFamSNzb");
    map.put("new romantic", "spotify:playlist:46NBAikQVSevpdAJIr923P");
    map.put("new wave", "spotify:playlist:6G1YyVYzXjAbZ32bOLx7Em");
    map.put("nightcore", "spotify:playlist:6tUox0LhGWQVgvAp3BGT2A");
    map.put("no Wave", "spotify:playlist:5ip48U8kJFixBQYp6NJje8");
    map.put("noise", "spotify:playlist:1V7RAjYrXWlsKiACmXqxLl");
    map.put("noise pop", "spotify:playlist:79vSZtZGJqNVs3KYInh42V");
    map.put("noise rock", "spotify:playlist:1V7RAjYrXWlsKiACmXqxLl");
    map.put("noisecore", "spotify:playlist:4xF67EdvjTbNBrELLpXidp");
    map.put("non-music", "spotify:playlist:6Ye9GVCgHFc7vMDFiR6QH2");
    map.put("norteno", "spotify:playlist:37i9dQZF1DWZmwe0RTeFj4");
    map.put("northern soul", "spotify:playlist:37i9dQZF1DWUB4Jn6piqKS");
    map.put("nu disco", "spotify:playlist:0KUF6SR96VHkAK1DaKiSU0");
    map.put("nu jazz", "spotify:playlist:4biDlMZ2OCCEV73QTkZGqa");
    map.put("nu metal", "spotify:playlist:37i9dQZF1DXcfZ6moR6J0G");
    map.put("nueva Cancion", "spotify:playlist:275lWKA1hb4trLqnjQWYx9");
    map.put("occult rock", "spotify:playlist:17sZgprqbWIU6m5NZUAB15");
    //might need changing
    map.put("oi", "spotify:playlist:6nm5h4HsvQyB85b1Se6w8s");
    map.put("old school death metal", "spotify:playlist:1X2Z3JRtq3Vwd94ZQ7k6Sf");
    map.put("old-time", "spotify:playlist:3jDeMOgQjynSOb5JXcE6ar");
    map.put("opera", "spotify:playlist:2PjVPkj4a9kBvQIXaZ6UUt");
    map.put("orchestral", "spotify:playlist:4HTbud8g5tLMxAaP1gmtWv");
    map.put("outlaw country", "spotify:playlist:6G7ZJuBDKwjCrk9ftdu82D");
    map.put("p-funk", "spotify:playlist:3ZBYo9jFqTTgevdak4vfEG");
    map.put("pachanga", "spotify:playlist:4bbKrmeHyCk5PBoZ1MWmWL");
    map.put("pagode", "spotify:playlist:37i9dQZF1DXchBFvKSUooB");
    map.put("phonk", "spotify:playlist:6nx4VZCuStkId2YiISi7pZ");
    map.put("polka", "spotify:playlist:1UVQytf2CAXtpDGh91jYTp");
    map.put("pop", "spotify:playlist:6mtYuOxzl58vSGnEDtZ9uB");
    map.put("pop metal", "spotify:playlist:7BIlPLUxduiHzqbf6FBwvy");
    map.put("pop punk", "spotify:playlist:7By5485k7QSRLyDT15bdGB");
    map.put("pop rap", "spotify:playlist:4GbJRuMWmbr1to7F6EbcjZ");
    map.put("pop rock", "spotify:playlist:4l7qxiQpxDOaby1aKlteQr");
    map.put("pop soul", "spotify:playlist:37i9dQZF1DX7eEfahN5cwF");
    map.put("pornogrind", "spotify:playlist:6kOigqG46UsxBFwMsX0ITH");
    map.put("post-bop", "spotify:playlist:38b3oUWLMqcEaJ6puqlnhZ");
    map.put("post-classical", "spotify:playlist:7azXYUCJVQ2R1lyY0sgq84");
    map.put("post-grunge", "spotify:playlist:37i9dQZF1DWVwr24yj95lH");
    map.put("post-hardcore", "spotify:playlist:25xipv7zYC6ovwBJlTCBrI");
    map.put("post-metal", "spotify:playlist:3GHBPKU7UdRzgYicknfIXc");
    map.put("post-punk", "spotify:playlist:01tvqPxen74Nz7TRz7kpSd");
    map.put("post-rock", "spotify:playlist:6MLuasIWQmRkM6qyGIb1uj");
    map.put("power electronics", "spotify:playlist:6cUMJj84Rvc07gSmqWMmKd");
    map.put("power metal", "spotify:playlist:1gxUTHGqYwYka43Mvn2hGZ");
    map.put("power pop", "spotify:playlist:37i9dQZF1DX5W4wuxak2hE");
    map.put("powerviolence", "spotify:playlist:4SJlsd4gR9A8DTzRoMrFPl");
    map.put("production music", "spotify:playlist:2zBQDCqS3yaIsaxBUwpnid");
    map.put("progressive", "spotify:playlist:4L1MWp0aSM8YEIOOBizPVu");
    map.put("progressive folk", "spotify:playlist:1fBm6Yo1qNQ533FEYBPjtZ");
    map.put("progressive house", "spotify:playlist:5CMvAWTlDPdZnkleiTHyyo");
    map.put("Progressive metal", "spotify:playlist:37i9dQZF1DX5wgKYQVRARv");
    map.put("Progressive rock", "spotify:playlist:5wDPvicN1zmNIqcbK3psT0");
    map.put("Progressive trance", "spotify:playlist:0HAkmXptQCY655WDljapXa");
    map.put("psy-trance", "spotify:playlist:6bTaXLHYTrjxqtwfNp2q5r");
    map.put("psychedelic", "spotify:playlist:5OcDUk5nAum1m6PH8fHslE");
    map.put("psychedelic folk", "spotify:playlist:6tOn5WueO2BlgajE1feBi9");
    map.put("psychedelic pop", "spotify:playlist:1mXIDhdIz15xRBHwU5uDCJ");
    map.put("psychedelic rock", "spotify:playlist:37i9dQZF1DWSDoVybeQisg");
    map.put("psychobilly", "spotify:playlist:0H10cZ1mLE6sKy6EeYtbwx");
    map.put("psytrance", "spotify:playlist:4dC6qS3rrFcseBiaec70v1");
    map.put("punk", "spotify:playlist:37i9dQZF1DXd6tJtr4qeot");
    map.put("punk rock", "spotify:playlist:39sVxPTg7BKwrf2MfgrtcD");
    map.put("qawwali", "spotify:playlist:4EtNeMUHkAHV6A0p5lN7tw");
    map.put("queercore", "spotify:playlist:37i9dQZF1DXa3ll4rE48Mv");
    map.put("r&b", "spotify:playlist:4WdOOas9UoL3XjHEs57BTX");
    map.put("ragga", "spotify:playlist:6pn0caqhVq5c3NRpvr8hHG");
    map.put("ragga hip-hop", "spotify:playlist:1P4f6pPcQSeuEfw83dU7th");
    map.put("ragga jungle", "spotify:playlist:0V742srw56x8PCuMxK3N4b");
    map.put("ragtime", "spotify:playlist:4Dfe6YgDQtGYFSXGQPVy8a");
    map.put("raï", "spotify:playlist:37i9dQZF1DXb7WmotStdsj");
    map.put("ranchera", "spotify:playlist:523boqZle6pyYJYuAVQtAz");
    map.put("rap rock", "spotify:playlist:6VYEbaLnk6oKFjwHVgsV5R");
    map.put("rapcore", "spotify:playlist:1ZfpWJ6Wv8vMemZ87qVFGY");
    map.put("rave", "spotify:playlist:3ybZkcoA09pYekp0DIo1OP");
    map.put("red song", "spotify:playlist:1tRiGuJGjvHx1SliU3qOeH");
    map.put("reggae", "spotify:playlist:37i9dQZF1DXbSbnqxMTGx9");
    map.put("reggaeton", "spotify:playlist:03sDEv7FN58Mb9CJOs1Tgn");
    map.put("rhythmic noise", "spotify:playlist:5hyOzYh3w5wcliJTKhEPiC");
    map.put("ritual ambient", "spotify:playlist:1sJKFhnCYglVaDXtA2y7Ao");
    map.put("rock", "spotify:playlist:37i9dQZF1DWXRqgorJj26U");
    map.put("rock and roll", "spotify:playlist:5qNHuEyfs4QHR8NI4oNaxE");
    map.put("rockabilly", "spotify:playlist:37i9dQZF1DX0xLQsW8b5Zx");
    map.put("rocksteady", "spotify:playlist:37i9dQZF1DXcYHsRTcIw5a");
    map.put("romantic classical", "spotify:playlist:786hH2BXw7n4SNg0MXuAio");
    map.put("roots reggae", "spotify:playlist:53iDeoyHodextygU8wpras");
    map.put("rumba", "spotify:playlist:4QeTqdJtrfLLGT9vzqPCSX");
    map.put("ryūkōka", "spotify:playlist:5qDufC8I9t3EVumsy0ZRAR");
    map.put("salsa", "spotify:playlist:37i9dQZF1DX7SeoIaFyTmA");
    map.put("samba", "spotify:playlist:2iiljY2f8tADeNzfQLUDWi");
    map.put("schlager", "spotify:playlist:1J2vYkrZaRh9mPoRAiVVmU");
    map.put("screamo", "spotify:playlist:37i9dQZF1DX8sGALGjOrTu");
    map.put("shibuya-kei", "spotify:playlist:5wuLaZucmCLqdXF7CvsCo2");
    map.put("shoegaze", "spotify:playlist:37i9dQZF1DX6ujZpAN0v9r");
    map.put("singer-songwriter", "spotify:playlist:37i9dQZF1DWVHi578arQLF");
    map.put("ska", "spotify:playlist:6mRRGF4klfgUzbD2ZKOCq0");
    map.put("ska punk", "spotify:playlist:5NmTCJa0Wx4urcsYYEhaaj");
    map.put("skacore", "spotify:playlist:5U5GURR1JQnlJKwmnHkWJO");
    map.put("slow waltz", "spotify:playlist:5fI9FCkOfVpJs19hGUwsNv");
    map.put("sludge metal", "spotify:playlist:3kbzqWhOrhZFXME9PpL8dg");
    map.put("smooth jazz", "spotify:playlist:37i9dQZF1DXdwTUxmGKrdN");
    map.put("smooth soul", "spotify:playlist:4RwKm6R88xEx1ScjWhtByA");
    map.put("soca", "spotify:playlist:6ADEIhrQYjvSrvL8Wo9rHK");
    map.put("soft rock", "spotify:playlist:37i9dQZF1DX6xOPeSOGone");
    map.put("son cubano", "spotify:playlist:3DZYs9PjtavyPR5CBj4gB0");
    map.put("son montuno", "spotify:playlist:2myAwVZH0I2Q3Tzvzj0ml7");
    map.put("soul", "spotify:playlist:37i9dQZF1DWULEW2RfoSCi");
    map.put("soul jazz", "spotify:playlist:5jBndFJwNAKyLHVjwhDiql");
    map.put("southern rock", "spotify:playlist:1QKiEzhqqKwjsFQWuLPhmw");
    map.put("southern soul", "spotify:playlist:2lFMAUSybNcIKKpf7akny8");
    map.put("space age pop", "spotify:playlist:3u0DDEtIpt78wsMjei5vEo");
    map.put("space rock", "spotify:playlist:3FnGA7QZoIaEkTPensobDS");
    map.put("speed garage", "spotify:playlist:3gatfQHhLcZJIRIDwyEqB7");
    map.put("speed metal", "spotify:playlist:1REn1DbDB5SwaHTY6Dj53V");
    map.put("spoken word", "spotify:playlist:0dukhbpXBPXzYuFwz30DIl");
    map.put("steampunk", "spotify:playlist:37i9dQZF1DWULEW2RfoSCi");
    map.put("stoner metal", "spotify:playlist:2OOarnLNBNgkHMbT3rgUCJ");
    map.put("stoner rock", "spotify:playlist:37i9dQZF1DXdpVGstUksUC");
    map.put("street punk", "spotify:playlist:2II9QeiylOTKP7zVNEa4f3");
    map.put("surf rock", "spotify:playlist:37i9dQZF1DWYzpSJHStHHx");
    map.put("swamp pop", "spotify:playlist:4d2eI8uEgAIqKCmb8Hxwij");
    map.put("swamp rock", "spotify:playlist:0O1NlowBgmMbKMcCcHxZCq");
    map.put("swing", "spotify:playlist:2VMD3Kun96CVnYdog9MooY");
  }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_music_browser);
      songTitle = (TextView) findViewById(R.id.songTitle);
      artistName = (TextView) findViewById(R.id.artist);
      songDuration = (ProgressBar) findViewById(R.id.songDuration);
      genrePlaying = (TextView) findViewById(R.id.genrePlaying);
      albumImage = (ImageView) findViewById(R.id.imageView);
      fillMusicMap(AllSet.music);
      Log.d("MusicBrowser", String.valueOf(AllSet.music));
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
      if (playlistURI == null) {
          playlistURI = "spotify:playlist:0vvXsWCC9xrXsKd4FyS8kM";
      }
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

            if (QuestionsStart.MoodTracker > 10.00) {
                albumImage.setImageResource(R.drawable.music10);
            } else if (QuestionsStart.MoodTracker > -5.00) {
                albumImage.setImageResource(R.drawable.musicnorm);
            } else {
                albumImage.setImageResource(R.drawable.musicn10);
            }

            int trackDuration = (int) (track.duration/1000);
            songDuration.setMax(trackDuration);
            songDuration.setProgress(0);

            //progress bar updates based on trackduration
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