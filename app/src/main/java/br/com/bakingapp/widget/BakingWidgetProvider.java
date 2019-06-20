package br.com.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import java.util.List;

import br.com.bakingapp.R;
import br.com.bakingapp.services.recipeService.response.Ingredient;
import br.com.bakingapp.services.recipeService.response.Recipe;

import static br.com.bakingapp.widget.Constants.RECIPE_INTENT;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidgetProvider extends AppWidgetProvider {

    private static List<Ingredient> cachedIngredients;

    static void updateIngredientWidgets(Context context, AppWidgetManager appWidgetManager,
                                        int[] appWidgetIds, Recipe recipe) {
        for (int appWidgetId : appWidgetIds) {
            updateIngredientWidget(context, appWidgetManager, recipe, appWidgetId);
        }
    }

    private static void updateIngredientWidget(Context context, AppWidgetManager appWidgetManager, Recipe recipe, int appWidgetId) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.baking_widget);
        Intent intent = new Intent(context, IngredientWidgetService.class);
        remoteViews.setRemoteAdapter(R.id.lv_ingredients, intent);
        if (recipe != null) {
            cachedIngredients = recipe.getIngredients();
            remoteViews.setTextViewText(R.id.tv_widget_label, recipe.getName());
        }
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.lv_ingredients);
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    public static void sendBroadcast(Context context, Recipe recipe) {
        Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        Bundle bundle = new Bundle();
        bundle.putParcelable(RECIPE_INTENT, recipe);
        intent.putExtras(bundle);
        intent.setComponent(new ComponentName(context, BakingWidgetProvider.class));
        context.sendBroadcast(intent);
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        final String action = intent.getAction();
        if (action != null && action.equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            Recipe recipe = intent.getParcelableExtra(RECIPE_INTENT);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(intent.getComponent());
            BakingWidgetProvider.updateIngredientWidgets(context, appWidgetManager, appWidgetIds, recipe);
            super.onReceive(context, intent);
        }
    }

    public static List<Ingredient> getCachedIngredients() {
        return cachedIngredients;
    }
}

