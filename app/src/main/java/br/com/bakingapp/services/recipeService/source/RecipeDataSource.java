package br.com.bakingapp.services.recipeService.source;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.bakingapp.data.model.Resource;
import br.com.bakingapp.services.recipeService.response.Recipe;

public interface RecipeDataSource {
    LiveData<Resource<List<Recipe>>> getRecipes();
}
