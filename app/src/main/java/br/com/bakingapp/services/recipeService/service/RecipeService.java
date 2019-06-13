package br.com.bakingapp.services.recipeService.service;

import java.util.List;

import br.com.bakingapp.services.recipeService.response.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeService {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> getRecipes();
}
