package br.com.bakingapp.services.recipeService.response;

import com.squareup.moshi.Json;

import java.math.BigDecimal;

public class Ingredient {

    @Json(name = "quantity")
    private final BigDecimal quantity;

    @Json(name = "measure")
    private final String measure;

    @Json(name = "ingredient")
    private final String ingredient;

    public Ingredient(BigDecimal quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }
}
