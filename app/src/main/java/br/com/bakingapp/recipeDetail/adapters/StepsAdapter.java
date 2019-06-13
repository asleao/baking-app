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
import br.com.bakingapp.services.recipeService.response.Step;
import br.com.bakingapp.services.recipeService.response.Steps;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepViewHolder> {
    private final Steps steps;
    private final StepClickListener mOnStepClickListener;

    public StepsAdapter(Steps steps, StepClickListener mOnStepClickListener) {
        this.steps = steps;
        this.mOnStepClickListener = mOnStepClickListener;
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_card, parent, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        Step step = steps.getSteps().get(position);
        holder.name.setText(step.getShortDescription());

    }

    @Override
    public int getItemCount() {
        return steps.getSteps().size();
    }

    public class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView name;

        public StepViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_recipe_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnStepClickListener.onClick(steps.getSteps().get(getAdapterPosition()));
        }
    }
}
