package br.com.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.List;

import br.com.bakingapp.R;
import br.com.bakingapp.services.recipeService.response.Ingredient;

import static br.com.bakingapp.widget.BakingWidgetProvider.getCachedIngredients;


public class IngredientFactoryAdapter implements RemoteViewsService.RemoteViewsFactory {
    private Context mContext;
    private List<Ingredient> mIngredients;

    IngredientFactoryAdapter(Context mContext, Intent intent) {
        this.mContext = mContext;
        mIngredients = getCachedIngredients();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        mIngredients = getCachedIngredients();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (mIngredients == null)
            return 0;
        return mIngredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Ingredient ingredient = mIngredients.get(position);
        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.item_ingredient_widget);
        views.setTextViewText(R.id.tv_ingredient_name, ingredient.getIngredient());

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
