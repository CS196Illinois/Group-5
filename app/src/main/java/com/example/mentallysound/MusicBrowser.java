package com.example.mentallysound;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;


public class MusicBrowser extends AppCompatActivity {

    private static final String CLIENT_ID = "39914e12eddd4bdf83e4f0ba4104727c";
    private static final String REDIRECT_URI = "http://yoursite.com";
    private SpotifyAppRemote mSpotifyAppRemote;

    public TextView songTitle;
    public TextView artistName;

  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_browser);

        songTitle = (TextView) findViewById(R.id.songTitle);
        artistName = (TextView) findViewById(R.id.artist);
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

                  // Now you can start interacting with App Remote
                  connected();
              }

              @Override
              public void onFailure(Throwable throwable) {
                  Log.e("MainActivity", throwable.getMessage(), throwable);

                  // Something went wrong when attempting to connect! Handle errors here
              }
          });
    }

    private void connected() {
      //dummy uri for now.
      String playlistURI = "spotify:playlist:0FAb3s3yJArWnikZbEOO9p";
      mSpotifyAppRemote.getPlayerApi().play(playlistURI);
      // Subscribe to PlayerState
      mSpotifyAppRemote.getPlayerApi()
        .subscribeToPlayerState()
        .setEventCallback(playerState -> {
          final Track track = playerState.track;
          if (track != null) {
            Log.d("Music Browser", track.name + " by " + track.artist.name);
            songTitle.setText(track.name);
            artistName.setText(track.artist.name);
          }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Aaand we will finish off here.
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);
    }
}