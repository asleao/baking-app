package br.com.bakingapp.recipes.adapters;

import br.com.bakingapp.services.recipeService.response.Recipe;

public interface RecipeClickListener {
    void onClick(Recipe recipe);
}
