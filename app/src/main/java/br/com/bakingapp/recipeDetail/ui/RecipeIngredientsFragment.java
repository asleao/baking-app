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
import br.com.bakingapp.recipeDetail.viewModel.RecipeIngredientsViewModel;
import br.com.bakingapp.services.recipeService.response.Ingredients;

public class RecipeIngredientsFragment extends Fragment {

    private RecipeIngredientsViewModel mViewModel;

    public static RecipeIngredientsFragment newInstance() {
        return new RecipeIngredientsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setupData();
        return inflater.inflate(R.layout.recipe_ingredients_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RecipeIngredientsViewModel.class);

    }

    private void setupData() {
        if (getArguments() != null) {
            RecipeIngredientsFragmentArgs args = RecipeIngredientsFragmentArgs.fromBundle(getArguments());
            args.getIngredients();
        }
    }

}
