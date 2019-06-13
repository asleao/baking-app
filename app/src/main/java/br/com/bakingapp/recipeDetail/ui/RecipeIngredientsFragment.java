package br.com.bakingapp.recipeDetail.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.bakingapp.R;
import br.com.bakingapp.recipeDetail.adapters.IngredientAdapter;
import br.com.bakingapp.recipeDetail.viewModel.RecipeIngredientsViewModel;
import br.com.bakingapp.services.recipeService.response.Ingredients;

public class RecipeIngredientsFragment extends Fragment {

    private RecipeIngredientsViewModel mViewModel;
    private RecyclerView mIngredientsRecyclerView;

    public static RecipeIngredientsFragment newInstance() {
        return new RecipeIngredientsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_ingredients_fragment, container, false);
        setupData(view);
        return view;
    }

    private void setupIngredientsAdapter(View view, Ingredients ingredients) {
        mIngredientsRecyclerView = view.findViewById(R.id.rv_ingredients);
        mIngredientsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        mIngredientsRecyclerView.setAdapter(new IngredientAdapter(ingredients));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RecipeIngredientsViewModel.class);

    }

    private void setupData(View view) {
        if (getArguments() != null) {
            RecipeIngredientsFragmentArgs args = RecipeIngredientsFragmentArgs.fromBundle(getArguments());
            setupIngredientsAdapter(view, args.getIngredients());
        }
    }

}
