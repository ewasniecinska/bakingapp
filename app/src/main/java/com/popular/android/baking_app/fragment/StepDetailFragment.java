package com.popular.android.baking_app.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.popular.android.baking_app.R;
import com.popular.android.baking_app.models.Step;
import com.squareup.picasso.Picasso;


public class StepDetailFragment extends Fragment {
    Step step;
    SimpleExoPlayerView simpleExoPlayerView;
    private SimpleExoPlayer mExoPlayer;
    TextView textView;
    CardView cardView;
    private long mCurrentPosition = 0;
    private boolean mPlayWhenReady = true;
    ImageView thumbnailImage;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_detail, container, false);

        getActivity().setTitle(getString(R.string.step_detail_tittle));

        if (savedInstanceState != null && savedInstanceState.containsKey("POSITION_KEY")) {
            mCurrentPosition = savedInstanceState.getLong("POSITION_KEY");
            mPlayWhenReady = savedInstanceState.getBoolean("PLAY_WHEN_READY_KEY");
        }

        // get Step data from intent
        Intent intent = getActivity().getIntent();
        step = intent.getParcelableExtra(getString(R.string.STEP_BUNDLE));
            updateUI(view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        checkAndStartPlayer();

    }
    @Override
    public void onResume(){
        super.onResume();
        checkAndStartPlayer();
    }


    public void updateUI(View view) {
        textView = view.findViewById(R.id.text_description);
        cardView = view.findViewById(R.id.cardView2);
        thumbnailImage = view.findViewById(R.id.thumbnail_image);
        textView.setText(step.getDescription());
        simpleExoPlayerView = view.findViewById(R.id.exoplayer_view);

        if (!step.getThumbnailURL().isEmpty()) {
            Picasso.with(getActivity().getApplicationContext())
                    .load(step.getThumbnailURL())
                    .placeholder(R.drawable.food_icon)
                    .into(thumbnailImage);
            thumbnailImage.setVisibility(View.VISIBLE);
        }
        checkAndStartPlayer();


    }

    public void checkAndStartPlayer(){
        if(step.getVideoURL().isEmpty()){
            simpleExoPlayerView.setVisibility(View.INVISIBLE);
        } else {
            initializePlayer(Uri.parse(step.getVideoURL()));
            showVideoInFullScreen();

        }
    }

    public void showVideoInFullScreen(){
        int value = getActivity().getResources().getConfiguration().orientation;

        if (value == Configuration.ORIENTATION_LANDSCAPE) {
            cardView.setVisibility(View.GONE);
            ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    public void initializePlayer(Uri uri){
        if (mExoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity().getApplicationContext(), trackSelector, loadControl);
            simpleExoPlayerView.setPlayer(mExoPlayer);

            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent(getActivity().getApplicationContext(), "Awesome Baking");
            MediaSource mediaSource = new ExtractorMediaSource(uri, new DefaultDataSourceFactory(
                    getActivity().getApplicationContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);

            if (mCurrentPosition != 0){
                mExoPlayer.seekTo(mCurrentPosition);
            }
            mExoPlayer.setPlayWhenReady(mPlayWhenReady);

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
         releasePlayer();
    }

    @Override
    public void onPause(){
        super.onPause();
        releasePlayer();
    }

    private void releasePlayer() {
        if(mExoPlayer != null){
            mPlayWhenReady = mExoPlayer.getPlayWhenReady();
            mCurrentPosition = mExoPlayer.getCurrentPosition();

            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putLong("POSITION_KEY", mCurrentPosition);
        outState.putBoolean("PLAY_WHEN_READY_KEY", mPlayWhenReady);
    }



}
