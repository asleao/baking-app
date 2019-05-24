package br.com.bakingapp.recipes.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import br.com.bakingapp.R;
import br.com.bakingapp.utils.FragmentUtils;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        RecipeFragment recipeFragment = (RecipeFragment) getSupportFragmentManager().findFragmentById(R.id.fg_recipes);
        if (recipeFragment != null) {
            FragmentUtils.addFragmentInActivity(getSupportFragmentManager(),
                    recipeFragment,
                    R.id.fg_recipes_main,
                    "RECIPES_FRAGMENT", true);
        }
    }
}
