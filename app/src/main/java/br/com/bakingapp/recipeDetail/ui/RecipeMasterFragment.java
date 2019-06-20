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
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.bakingapp.R;
import br.com.bakingapp.recipeDetail.adapters.StepClickListener;
import br.com.bakingapp.recipeDetail.adapters.StepsAdapter;
import br.com.bakingapp.services.recipeService.response.Ingredients;
import br.com.bakingapp.services.recipeService.response.Recipe;
import br.com.bakingapp.services.recipeService.response.Step;
import br.com.bakingapp.services.recipeService.response.Steps;
import br.com.bakingapp.widget.BakingWidgetProvider;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

public class RecipeMasterFragment extends Fragment {

    private TextView ingredientsTextView;
    private Ingredients ingredients;
    private Steps steps;
    private RecyclerView mStepsRecyclerView;


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
        mStepsRecyclerView.setAdapter(new StepsAdapter(steps, new StepClickListener() {
            @Override
            public void onClick(Step step) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(requireContext().
                        getResources()
                        .getString(R.string.step_arg), step);
                navHostFragment.getNavController().navigate(R.id.recipeStepFragment, bundle);
            }
        }));
    }

    private void setupSingleLayout() {
        ingredientsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = RecipeMasterFragmentDirections.actionRecipeMasterFragmentToRecipeIngredientsFragment(ingredients);
                findNavController(RecipeMasterFragment.this).navigate(action);
            }
        });
        mStepsRecyclerView.setAdapter(new StepsAdapter(steps, new StepClickListener() {
            @Override
            public void onClick(Step step) {
                NavDirections action = RecipeMasterFragmentDirections.actionRecipeMasterFragmentToRecipeStepFragment(step);
                findNavController(RecipeMasterFragment.this).navigate(action);
            }
        }));
    }

    private void setupFields(View view) {
        ingredientsTextView = view.findViewById(R.id.tv_recipe_name);
        String ingredientsText = requireContext().getResources().getString(R.string.ingredients);
        ingredientsTextView.setText(ingredientsText);
        mStepsRecyclerView = view.findViewById(R.id.rv_steps);
        mStepsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));

    }

    private void setupData() {
        if (getArguments() != null) {
            RecipeMasterFragmentArgs args = RecipeMasterFragmentArgs.fromBundle(getArguments());
            Recipe recipe = args.getRecipe();
            ingredients = new Ingredients(recipe.getIngredients());
            steps = new Steps(recipe.getSteps());
            BakingWidgetProvider.sendBroadcast(requireContext(), recipe);
        }
    }

}
