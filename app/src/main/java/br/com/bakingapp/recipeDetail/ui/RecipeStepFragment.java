package br.com.bakingapp.recipeDetail.ui;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.bakingapp.R;
import br.com.bakingapp.recipeDetail.viewModel.RecipeStepViewModel;

public class RecipeStepFragment extends Fragment {

    private RecipeStepViewModel mViewModel;

    public static RecipeStepFragment newInstance() {
        return new RecipeStepFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_step_fragment, container, false);
        setupData(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RecipeStepViewModel.class);
        // TODO: Use the ViewModel
    }

    private void setupData(View view) {
        if (getArguments() != null) {
            RecipeStepFragmentArgs args = RecipeStepFragmentArgs.fromBundle(getArguments());
            args.getStep();
        }
    }
}
