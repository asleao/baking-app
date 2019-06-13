package br.com.bakingapp.recipeDetail.ui;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import br.com.bakingapp.R;
import br.com.bakingapp.recipeDetail.viewModel.RecipeStepViewModel;
import br.com.bakingapp.services.recipeService.response.Step;

public class RecipeStepFragment extends Fragment {

    private RecipeStepViewModel mViewModel;
    private Step step;
    private PlayerView mPlayerView;
    private SimpleExoPlayer player;

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
}
