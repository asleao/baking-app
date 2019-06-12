package br.com.bakingapp.recipeDetail.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.bakingapp.R;
import br.com.bakingapp.services.recipeService.response.Ingredient;
import br.com.bakingapp.services.recipeService.response.Ingredients;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {
    private Ingredients ingredients;

    public IngredientAdapter(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_ingredient, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Ingredient ingredient = ingredients.getIngredients().get(position);
        holder.name.setText(ingredient.getIngredient());
        holder.quantity.setText(ingredient.getQuantity().toString());
        holder.mesure.setText(ingredient.getMeasure());
    }

    @Override
    public int getItemCount() {
        return ingredients.getIngredients().size();
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView name;
        private final TextView quantity;
        private final TextView mesure;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_ingredient_name);
            quantity = itemView.findViewById(R.id.tv_ingredient_quantity);
            mesure = itemView.findViewById(R.id.tv_ingredient_mesure);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
