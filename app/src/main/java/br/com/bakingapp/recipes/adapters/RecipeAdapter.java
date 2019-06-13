package br.com.bakingapp.recipes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.bakingapp.R;
import br.com.bakingapp.services.recipeService.response.Recipe;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private final List<Recipe> mRecipes;
    private final RecipeClickListener mOnRecipeClickListener;

    public RecipeAdapter(List<Recipe> recipes, RecipeClickListener mOnRecipeClickListener) {
        this.mRecipes = recipes;
        this.mOnRecipeClickListener = mOnRecipeClickListener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_card, viewGroup, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = mRecipes.get(position);
        holder.mRecipeName.setText(recipe.getName());
    }


    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView mRecipeName;

        RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            mRecipeName = itemView.findViewById(R.id.tv_recipe_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnRecipeClickListener.onClick(mRecipes.get(getAdapterPosition()));

        }
    }

}
