package com.example.asus.yaratube.ui.player;

import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.asus.yaratube.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class PlayerActivity extends AppCompatActivity {

    private String mediaSource;
    private SimpleExoPlayer player;
    private PlayerView playerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        mediaSource = getIntent().getExtras().getString("file");

        fullScreen();
        exoPlayerSetup();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.release();
    }

    private void exoPlayerSetup() {

        // create player
        // 1. Create a default TrackSelector
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        DefaultTrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        // 2. Create the player
        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
        player.addListener(new PlayerEventListener());

        // Bind the player to the view
        playerView = findViewById(R.id.player);
        playerView.setPlayer(player);

        // prepare player
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "YaraTube Player"), bandwidthMeter);

        // This is the MediaSource representing the media to be played.
        HlsMediaSource videoSource = new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(mediaSource));
        // Prepare the player with the source.
        player.prepare(videoSource);
        player.setPlayWhenReady(true);
    }

    private void fullScreen() {

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        getSupportActionBar().hide();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fullScreen();
        player.setPlayWhenReady(true);
        player.getPlaybackState();
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.setPlayWhenReady(false);
        player.getPlaybackState();

    }

    private class PlayerEventListener extends Player.DefaultEventListener {
        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            if (playbackState == Player.STATE_IDLE || playbackState == Player.STATE_ENDED ||
                    !playWhenReady) {
                playerView.setKeepScreenOn(false);
            } else { // STATE_IDLE, STATE_ENDED
                // This prevents the screen from getting dim/lock
                playerView.setKeepScreenOn(true);
            }
        }
    }
}
