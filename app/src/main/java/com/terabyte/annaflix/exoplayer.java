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

public class exoplayer extends AppCompatActivity {
    SimpleExoPlayerView exoPlayerView;
    SimpleExoPlayer exoPlayer;
    String videourl="https://storage.kanzaki.ru/ANIME___/3-gatsu_no_Lion/%5bHorribleSubs%5d%203-gatsu%20no%20Lion%20-%2004%20%5b720p%5d.mkv";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exoplayer);
        exoPlayerView=(SimpleExoPlayerView)findViewById(R.id.exo_player_view2);
        try {
            BandwidthMeter bandwidthMeter=new DefaultBandwidthMeter();
            TrackSelector trackSelector=new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer= ExoPlayerFactory.newSimpleInstance(this,trackSelector);
            Uri videouri=Uri.parse(videourl);
            DefaultHttpDataSourceFactory dataSourceFactory=new DefaultHttpDataSourceFactory("exoplayer_video");
            ExtractorsFactory extractorsFactory=new DefaultExtractorsFactory();
            MediaSource mediaSource=new ExtractorMediaSource(videouri,dataSourceFactory,extractorsFactory,null,null);
            exoPlayerView.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);

        }catch (Exception e)
        {
            Log.e("videoplay","exoplayer error"+e.toString());
        }
    }
}
