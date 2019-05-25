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

        if (savedInstanceState != null) {
            FragmentUtils.addFragmentInActivity(getSupportFragmentManager(),
                    new RecipeFragment(),
                    R.id.fg_recipes_main,
                    getResources().getString(R.string.recipes_main_fragment),
                    true);
        }
    }
}
