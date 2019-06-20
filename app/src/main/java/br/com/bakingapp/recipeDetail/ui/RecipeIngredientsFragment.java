package br.com.bakingapp.recipeDetail.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.bakingapp.R;
import br.com.bakingapp.recipeDetail.adapters.IngredientAdapter;
import br.com.bakingapp.services.recipeService.response.Ingredients;

public class RecipeIngredientsFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_ingredients_fragment, container, false);
        setupData(view);
        return view;
    }

    private void setupIngredientsAdapter(View view, Ingredients ingredients) {
        RecyclerView mIngredientsRecyclerView = view.findViewById(R.id.rv_ingredients);
        mIngredientsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        mIngredientsRecyclerView.setAdapter(new IngredientAdapter(ingredients));
    }

    private void setupData(View view) {
        if (getArguments() != null) {
            RecipeIngredientsFragmentArgs args = RecipeIngredientsFragmentArgs.fromBundle(getArguments());
            setupIngredientsAdapter(view, args.getIngredients());
        }
    }

}
