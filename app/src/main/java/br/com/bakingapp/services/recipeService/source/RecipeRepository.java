package br.com.bakingapp.services.recipeService.source;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.bakingapp.data.model.Resource;
import br.com.bakingapp.services.recipeService.response.Recipe;

public class RecipeRepository implements RecipeDataSource {

    private volatile static RecipeRepository INSTANCE = null;

    private final RecipeDataSource mRecipeRemoteDataSource;


    private RecipeRepository(@NonNull RecipeDataSource mRecipeRemoteDataSource) {
        this.mRecipeRemoteDataSource = mRecipeRemoteDataSource;
    }

    public static RecipeRepository getInstance(RecipeDataSource mRecipeRemoteDataSource) {
        if (INSTANCE == null) {
            synchronized (RecipeRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RecipeRepository(mRecipeRemoteDataSource);
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public LiveData<Resource<List<Recipe>>> getRecipes() {
        return mRecipeRemoteDataSource.getRecipes();
    }
}
