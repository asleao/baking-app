package br.com.bakingapp.recipes.ui;


import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import br.com.bakingapp.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class RecipeFragmentTest {
    @Rule
    public ActivityTestRule<RecipeActivity> mainActivityActivityTestRule = new ActivityTestRule<>(RecipeActivity.class);
    private int RECIPE_POSITION = 0;

    @Before
    public void setup() {
        mainActivityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void test_recipesAreShowned() {
        onView(withId(R.id.rv_recipes)).check(matches(isDisplayed()));
    }

    @Test
    public void test_recipeClick() {
        onView(withId(R.id.rv_recipes))
                .perform(
                        RecyclerViewActions.actionOnItemAtPosition(
                                RECIPE_POSITION,
                                click()
                        )
                );

    }

}