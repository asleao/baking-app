package br.com.bakingapp.recipeDetail.viewModel;

import androidx.lifecycle.ViewModel;

public class RecipeStepViewModel extends ViewModel {
    private long playerPosition;

    public long getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(long playerPosition) {
        this.playerPosition = playerPosition;
    }
}
