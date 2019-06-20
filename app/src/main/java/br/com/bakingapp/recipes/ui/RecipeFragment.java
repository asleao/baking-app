package br.com.bakingapp.recipes.ui;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.bakingapp.R;
import br.com.bakingapp.data.model.Resource;
import br.com.bakingapp.recipes.adapters.RecipeAdapter;
import br.com.bakingapp.recipes.adapters.RecipeClickListener;
import br.com.bakingapp.recipes.viewmodel.RecipeViewModel;
import br.com.bakingapp.recipes.viewmodel.factories.RecipeFactory;
import br.com.bakingapp.services.recipeService.response.Recipe;
import br.com.bakingapp.services.recipeService.source.RecipeRepository;
import br.com.bakingapp.services.recipeService.source.remote.RecipeRemoteDataSource;

import static androidx.navigation.fragment.NavHostFragment.findNavController;
import static br.com.bakingapp.utils.ViewUtils.calculateNoOfColumns;

public class RecipeFragment extends Fragment implements RecipeClickListener {

    private Observer<Resource<List<Recipe>>> recipesObserver;
    private RecyclerView mRecipesRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
        setupObservers();
    }

    private void setupObservers() {
        recipesObserver = new Observer<Resource<List<Recipe>>>() {
            @Override
            public void onChanged(Resource<List<Recipe>> resource) {
                if (resource != null) {
                    switch (resource.status) {
                        case LOADING:
                            break;
                        case SUCCESS:
                            if (resource.data != null) {
                                RecipeAdapter mRecipeAdapter = new RecipeAdapter(resource.data, RecipeFragment.this);
                                mRecipesRecyclerView.setAdapter(mRecipeAdapter);
                            }
                            break;
                        case ERROR:
                            break;
                    }
                }
            }
        };
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_fragment, container, false);
        boolean isTablet = requireContext().getResources().getBoolean(R.bool.isTablet);

        RecipeRepository mRecipeRepository = RecipeRepository.getInstance(RecipeRemoteDataSource.getInstance());
        RecipeViewModel mViewModel = ViewModelProviders.of(this,
                new RecipeFactory(mRecipeRepository)).get(RecipeViewModel.class);
        mViewModel.getmRecipes().observe(getViewLifecycleOwner(), recipesObserver);
        if (isTablet) {
            setupRecipes(view, new GridLayoutManager(getContext(), calculateNoOfColumns(requireContext()), RecyclerView.VERTICAL, false));
            requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setupRecipes(view, new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        }
        return view;
    }

    private void setupRecipes(View view, RecyclerView.LayoutManager layoutManager) {
        mRecipesRecyclerView = view.findViewById(R.id.rv_recipes);
        mRecipesRecyclerView.setLayoutManager(layoutManager);
    }


    @Override
    public void onClick(Recipe recipe) {
        NavDirections action = RecipeFragmentDirections.actionRecipeFragmentToRecipeMasterFragment(recipe);
        findNavController(this).navigate(action);
    }
}
