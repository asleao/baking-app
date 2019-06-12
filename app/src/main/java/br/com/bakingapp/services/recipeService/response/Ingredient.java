package br.com.bakingapp.services.recipeService.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

import java.math.BigDecimal;

public class Ingredient implements Parcelable {

    @Json(name = "quantity")
    private BigDecimal quantity;

    @Json(name = "measure")
    private final String measure;

    @Json(name = "ingredient")
    private final String ingredient;

    public Ingredient(BigDecimal quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    protected Ingredient(Parcel in) {
        measure = in.readString();
        ingredient = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(measure);
        dest.writeString(ingredient);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };
}
