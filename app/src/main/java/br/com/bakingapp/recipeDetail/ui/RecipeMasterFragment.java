package br.com.bakingapp.recipeDetail.ui;

import androidx.lifecycle.ViewModelProviders;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.bakingapp.R;
import br.com.bakingapp.recipeDetail.viewModel.RecipeMasterViewModel;

public class RecipeMasterFragment extends Fragment {

    private RecipeMasterViewModel mViewModel;

    public static RecipeMasterFragment newInstance() {
        return new RecipeMasterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        boolean isTablet = requireContext().getResources().getBoolean(R.bool.isTablet);
        View view;
        if (isTablet) {
            view = inflater.inflate(R.layout.recipe_master_fragment_land, container, false);
            requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            view = inflater.inflate(R.layout.recipe_master_fragment, container, false);
        }
        setupFields(view);
        return view;
    }

    private void setupFields(View view) {
        TextView ingredients = view.findViewById(R.id.tv_recipe_name);
        String ingredientsText = requireContext().getResources().getString(R.string.ingredients);
        ingredients.setText(ingredientsText);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RecipeMasterViewModel.class);
        if (getArguments() != null) {
            RecipeMasterFragmentArgs.fromBundle(getArguments());
        }
    }

}
