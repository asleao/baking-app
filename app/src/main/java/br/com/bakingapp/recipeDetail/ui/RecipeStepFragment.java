package br.com.bakingapp.recipeDetail.ui;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.IOException;
import java.util.EventListener;

import br.com.bakingapp.R;
import br.com.bakingapp.recipeDetail.viewModel.RecipeStepViewModel;
import br.com.bakingapp.services.recipeService.response.Step;

public class RecipeStepFragment extends Fragment implements ExtractorMediaSource.EventListener {

    private RecipeStepViewModel mViewModel;
    private Step step;
    private PlayerView mPlayerView;
    private SimpleExoPlayer mPlayer;


    public static RecipeStepFragment newInstance() {
        return new RecipeStepFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_step_fragment, container, false);
        setupData();
        setupFields(view);
        return view;
    }

    private void setupFields(View view) {
        mPlayerView = view.findViewById(R.id.pv_recipe_step);
        initializePlayer(Uri.parse(step.getVideoURL()));
    }

    private void initializePlayer(Uri mediaUrl) {
        if (mPlayer == null) {
            mPlayer = ExoPlayerFactory.newSimpleInstance(requireContext(), new DefaultTrackSelector(), new DefaultLoadControl());
            mPlayerView.setPlayer(mPlayer);
            String user = Util.getUserAgent(requireContext(), getResources().getString(R.string.app_name));
            DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(requireContext(), user);
            DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(mediaUrl, dataSourceFactory,
                    extractorsFactory,
                    new Handler(),
                    this);
            mPlayer.prepare(mediaSource);
            mPlayer.setPlayWhenReady(true);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RecipeStepViewModel.class);
    }

    private void setupData() {
        if (getArguments() != null) {
            RecipeStepFragmentArgs args = RecipeStepFragmentArgs.fromBundle(getArguments());
            step = args.getStep();
        }
    }

    @Override
    public void onLoadError(IOException error) {

    }
}
