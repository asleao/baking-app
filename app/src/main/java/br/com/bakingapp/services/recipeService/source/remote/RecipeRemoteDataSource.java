package br.com.bakingapp.services.recipeService.source.remote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import br.com.bakingapp.data.ServiceGenerator;
import br.com.bakingapp.data.model.Resource;
import br.com.bakingapp.services.recipeService.response.Recipe;
import br.com.bakingapp.services.recipeService.service.RecipeService;
import br.com.bakingapp.services.recipeService.source.ApiResponse;
import br.com.bakingapp.services.recipeService.source.RecipeDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRemoteDataSource implements RecipeDataSource {

    private volatile static RecipeRemoteDataSource INSTANCE = null;
    private final RecipeService mRecipeService;


    private RecipeRemoteDataSource() {
        mRecipeService = ServiceGenerator.createService(RecipeService.class);
    }

    public static RecipeRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (RecipeRemoteDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RecipeRemoteDataSource();
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
        final String GET_RECIPES_TAG = "getRecipes";
        Call<List<Recipe>> call = mRecipeService.getRecipes();
        final ApiResponse<List<Recipe>> apiResponse = new ApiResponse<>(GET_RECIPES_TAG);
        final MutableLiveData<Resource<List<Recipe>>> recipes = new MutableLiveData<>();
        recipes.setValue(Resource.<List<Recipe>>loading());
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(@NotNull Call<List<Recipe>> call, @NotNull Response<List<Recipe>> response) {
                recipes.setValue(apiResponse.getApiOnResponse(response));
            }

            @Override
            public void onFailure(@NotNull Call<List<Recipe>> call, @NotNull Throwable t) {
                recipes.setValue(apiResponse.getApiOnFailure(t));
            }
        });
        return recipes;
    }
}
