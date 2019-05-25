package br.com.bakingapp.services.recipeService.response;

import com.squareup.moshi.Json;

import java.util.List;

public class Recipe {
    @Json(name = "id")
    private final int id;

    @Json(name = "name")
    private final String name;

    @Json(name = "ingredients")
    private final List<Ingredient> ingredients;

    @Json(name = "steps")
    private final List<Step> steps;


    @Json(name = "servings")
    private final String servings;

    @Json(name = "image")
    private final String image;

    public Recipe(int id, String name, List<Ingredient> ingredients, List<Step> steps, String servings, String image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
