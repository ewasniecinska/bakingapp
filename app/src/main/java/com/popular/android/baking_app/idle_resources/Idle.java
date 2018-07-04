package com.popular.android.baking_app.idle_resources;

import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;



public class Idle implements IdlingResource {

        @Nullable
        private volatile ResourceCallback mCallback;

        private AtomicBoolean mIsIdleNow = new AtomicBoolean(true);

        @Override
        public String getName() {
            return this.getClass().getName();
        }

         @Override
         public void registerIdleTransitionCallback(ResourceCallback callback) {
             mCallback = callback;
        }

        @Override
        public boolean isIdleNow() {
            return mIsIdleNow.get();
        }


        public void setIdleState(boolean isIdleNow) {
            mIsIdleNow.set(isIdleNow);

            if (isIdleNow && mCallback != null) {
                mCallback.onTransitionToIdle();
            }

        }
}