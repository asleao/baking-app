package br.com.bakingapp.recipeDetail.ui;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        return inflater.inflate(R.layout.recipe_master_fragment, container, false);
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
