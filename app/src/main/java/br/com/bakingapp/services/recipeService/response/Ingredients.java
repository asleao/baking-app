package br.com.bakingapp.services.recipeService.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

import java.math.BigDecimal;
import java.util.List;

public class Ingredients implements Parcelable {
    private List<Ingredient> ingredients;

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public Ingredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    protected Ingredients(Parcel in) {
        ingredients = in.createTypedArrayList(Ingredient.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(ingredients);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };
}
