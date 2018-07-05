package com.popular.android.baking_app;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import static android.support.test.espresso.matcher.ViewMatchers.isRoot;

/**
 * Created by ewasniecinska on 05.07.2018.
 * Based
 */

public class Delay {
    public static ViewAction waitTime(final long miliseconds) {
        return new ViewAction() {
            @Override
            public org.hamcrest.Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public void perform(UiController uiController, final View view) {
                uiController.loopMainThreadForAtLeast(miliseconds);
            }

            @Override
            public String getDescription() {
                return "Network Action: Wait for " + miliseconds + " milliseconds.";
            }
        };
    }
}
