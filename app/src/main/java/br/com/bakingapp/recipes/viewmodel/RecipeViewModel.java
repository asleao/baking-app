package br.com.bakingapp.recipes.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.com.bakingapp.data.model.Resource;
import br.com.bakingapp.services.recipeService.response.Recipe;
import br.com.bakingapp.services.recipeService.source.RecipeRepository;

public class RecipeViewModel extends ViewModel {
    private final LiveData<Resource<List<Recipe>>> mRecipes;

    public RecipeViewModel(RecipeRepository recipeRepository) {
        mRecipes = recipeRepository.getRecipes();
    }

    public LiveData<Resource<List<Recipe>>> getmRecipes() {
        return mRecipes;
    }
}
