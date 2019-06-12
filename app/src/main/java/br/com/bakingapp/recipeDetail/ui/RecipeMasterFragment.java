package br.com.bakingapp.recipeDetail.ui;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import br.com.bakingapp.R;
import br.com.bakingapp.recipeDetail.viewModel.RecipeMasterViewModel;
import br.com.bakingapp.services.recipeService.response.Ingredients;
import br.com.bakingapp.services.recipeService.response.Recipe;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

public class RecipeMasterFragment extends Fragment {

    private RecipeMasterViewModel mViewModel;
    private TextView ingredientsTextView;
    private Recipe recipe;
    private Ingredients ingredients;

    public static RecipeMasterFragment newInstance() {
        return new RecipeMasterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setupData();
        boolean isTablet = requireContext().getResources().getBoolean(R.bool.isTablet);
        View view;
        if (isTablet) {
            view = inflater.inflate(R.layout.recipe_master_fragment_land, container, false);
            setupFields(view);
            setupMasterDetailLayout();

        } else {
            view = inflater.inflate(R.layout.recipe_master_fragment, container, false);
            setupFields(view);
            setupSingleLayout();
        }
        mViewModel = ViewModelProviders.of(this).get(RecipeMasterViewModel.class);

        return view;
    }

    private void setupMasterDetailLayout() {
        final NavHostFragment navHostFragment =
                (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.fg_nav_container);
        requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        ingredientsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(requireContext().
                        getResources()
                        .getString(R.string.ingredients_arg), ingredients);
                navHostFragment.getNavController().navigate(R.id.recipeIngredientsFragment, bundle);
            }
        });
    }

    private void setupSingleLayout() {
        ingredientsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = RecipeMasterFragmentDirections.actionRecipeMasterFragmentToRecipeIngredientsFragment(ingredients);
                findNavController(RecipeMasterFragment.this).navigate(action);
            }
        });
    }

    private void setupFields(View view) {
        ingredientsTextView = view.findViewById(R.id.tv_recipe_name);
        String ingredientsText = requireContext().getResources().getString(R.string.ingredients);
        ingredientsTextView.setText(ingredientsText);
    }

    private void setupData() {
        if (getArguments() != null) {
            RecipeMasterFragmentArgs args = RecipeMasterFragmentArgs.fromBundle(getArguments());
            recipe = args.getRecipe();
            ingredients = new Ingredients(recipe.getIngredients());
        }
    }

}
