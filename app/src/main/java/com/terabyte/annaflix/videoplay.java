package com.terabyte.annaflix;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;


import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;



public class videoplay extends AppCompatActivity {

    SimpleExoPlayerView exoPlayerView;
    SimpleExoPlayer exoPlayer;
    String videourl="https://storage.kanzaki.ru/ANIME___/Baton_The_Animation/%5bAniDub%5d_Baton_The_Animation_%5b1_of_3%5d_%5bCuba77%5d.mkv";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplay);
        exoPlayerView=(SimpleExoPlayerView)findViewById(R.id.exo_player_view);
        try {
            BandwidthMeter bandwidthMeter=new DefaultBandwidthMeter();
            TrackSelector trackSelector=new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer= ExoPlayerFactory.newSimpleInstance(this,trackSelector);
            Uri videouri=Uri.parse(videourl);
            DefaultHttpDataSourceFactory  dataSourceFactory=new DefaultHttpDataSourceFactory("exoplayer_video");
            ExtractorsFactory extractorsFactory=new DefaultExtractorsFactory();
            MediaSource mediaSource=new ExtractorMediaSource(videouri,dataSourceFactory,extractorsFactory,null,null);
            exoPlayerView.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);

        }catch (Exception e)
        {
            Log.e("videoplay","exoplayer error"+e.toString());
        }
       /* startActivity(PlayerActivity.getVideoPlayerIntent(getApplicationContext(),
                "https://storage.kanzaki.ru/ANIME___/Akiba%27s_Trip_The_Animation/%5bHorribleSubs%5d%20Akiba%27s%20Trip%20The%20Animation%20-%2002%20%5b720p%5d.mkv",
                "Video title"));
        //VidstaPlayer player = (VidstaPlayer) findViewById(R.id.player);
       // player.setVideoSource("http://www.quirksmode.org/html5/videos/big_buck_bunny.mp4");

        */
       /* AndExoPlayerView andExoPlayerView = findViewById(R.id.andExoPlayerView);
        andExoPlayerView.setSource("https://gegenees.feralhosting.com/prisoner627/Unsorted/Schitts.Creek.S05E11.1080p.WEBRip.x264-TBS%5Brarbg%5D/schitts.creek.s05e11.1080p.webrip.x264-tbs.mkv");

        */
    }
}
