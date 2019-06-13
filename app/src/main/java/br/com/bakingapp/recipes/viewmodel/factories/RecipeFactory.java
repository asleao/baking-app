package br.com.bakingapp.recipes.viewmodel.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import br.com.bakingapp.recipes.viewmodel.RecipeViewModel;
import br.com.bakingapp.services.recipeService.source.RecipeRepository;

public class RecipeFactory implements ViewModelProvider.Factory {
    private final RecipeRepository mRecipeRepository;

    public RecipeFactory(RecipeRepository mRecipeRepository) {
        this.mRecipeRepository = mRecipeRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RecipeViewModel(mRecipeRepository);
    }
}
