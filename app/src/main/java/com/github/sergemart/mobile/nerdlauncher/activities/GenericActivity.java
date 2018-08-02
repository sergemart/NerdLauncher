package com.github.sergemart.mobile.nerdlauncher.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.github.sergemart.mobile.nerdlauncher.R;


public abstract class GenericActivity extends AppCompatActivity {

    // --------------------------- Generic abstracts

    /**
     * Specifies an activity layout
     */
    protected abstract int getLayoutResId();


    /**
     *  Specifies a fragment to use in the parent activity
     */
    protected abstract Fragment createFragment();


    // --------------------------- Activity event handlers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( this.getLayoutResId() );                                                    // implemented in subclasses

        this.addActivityFragments();
    }


    // --------------------------- Widget controls

    /**
     * Add activity fragments
     */
    private void addActivityFragments() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        Fragment singleFragment = fragmentManager.findFragmentById(R.id.layout_fragment_container); // fragment identified by its container id
        if (singleFragment == null) {
            singleFragment = this.createFragment();                                                 // implemented in subclass
            fragmentManager
                    .beginTransaction()
                    .add(R.id.layout_fragment_container, singleFragment)
                    .commit();
        }
    }

}
