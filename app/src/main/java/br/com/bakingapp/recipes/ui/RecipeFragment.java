package br.com.bakingapp.recipes.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import br.com.bakingapp.R;
import br.com.bakingapp.data.model.Resource;
import br.com.bakingapp.recipes.viewmodel.RecipeViewModel;
import br.com.bakingapp.recipes.viewmodel.factories.RecipeFactory;
import br.com.bakingapp.services.recipeService.response.Recipe;
import br.com.bakingapp.services.recipeService.source.RecipeRepository;
import br.com.bakingapp.services.recipeService.source.remote.RecipeRemoteDataSource;

public class RecipeFragment extends Fragment {

    private RecipeViewModel mViewModel;
    private Observer<Resource<List<Recipe>>> recipesObserver;

    public static RecipeFragment newInstance() {
        return new RecipeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
        setupObservers();
    }

    private void setupObservers() {
        recipesObserver = new Observer<Resource<List<Recipe>>>() {
            @Override
            public void onChanged(Resource<List<Recipe>> resource) {
                if (resource != null) {

                }
            }
        };
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_fragment, container, false);
        RecipeRepository mRecipeRepository = RecipeRepository.getInstance(RecipeRemoteDataSource.getInstance());
        mViewModel = ViewModelProviders.of(this,
                new RecipeFactory(mRecipeRepository)).get(RecipeViewModel.class);
        mViewModel.getmRecipes().observe(getViewLifecycleOwner(), recipesObserver);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        // TODO: Use the ViewModel
    }

}
