package com.github.sergemart.mobile.nerdlauncher.activities;

import android.support.v4.app.Fragment;

import com.github.sergemart.mobile.nerdlauncher.LauncherFragment;
import com.github.sergemart.mobile.nerdlauncher.R;
import com.github.sergemart.mobile.nerdlauncher.activities.GenericActivity;


public class LauncherActivity extends GenericActivity {

    // --------------------------- Implement generic abstracts

    /**
     * Specifies an activity layout
     */
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_one_pane;
    }


    /**
     *  Specifies a fragment to use in the parent activity
     */
    @Override
    protected Fragment createFragment() {
        return LauncherFragment.newInstance();
    }
}
