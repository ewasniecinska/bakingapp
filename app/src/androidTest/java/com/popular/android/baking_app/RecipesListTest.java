package com.popular.android.baking_app;


import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.popular.android.baking_app.Delay.waitTime;


/**
 * Created by ewasniecinska on 03.07.2018.
 */

@RunWith(AndroidJUnit4.class)
public class RecipesListTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testRecycleViewIfDisplayed(){

        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()));
    }

    @Test
    public void testRecycleViewCheckClick(){

        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitTime(5000));
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(withId(R.id.recycyle_view_ingredients)).check(matches(isDisplayed()));
    }
}
