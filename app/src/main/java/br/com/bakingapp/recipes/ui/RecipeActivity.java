package br.com.bakingapp.recipes.ui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;

import br.com.bakingapp.R;

import static androidx.navigation.ui.NavigationUI.onNavDestinationSelected;
import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;


public class RecipeActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fg_recipes_navhost);
        NavController navController = navHostFragment.getNavController();

        appBarConfiguration = new AppBarConfiguration.
                Builder(navController.getGraph()).
                build();
        setupActionBar(navController, appBarConfiguration);
    }

    private void setupActionBar(NavController navController, AppBarConfiguration appBarConfiguration) {
        setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.fg_recipes_navhost).navigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }
}
