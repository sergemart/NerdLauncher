package com.github.sergemart.mobile.nerdlauncher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Objects;


public class LauncherFragment extends Fragment {

    private static final String TAG = "LauncherFragment";

    private RecyclerView mAppListRecyclerView;


    // --------------------------- Override fragment event handlers

    /**
     * View-unrelated startup actions
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * View-related startup actions
     * @return Inflated content view of the fragment
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_launcher, container, false);

        this.getWidgetReferences(fragmentView);
        this.setWidgetAttributes();
        this.setWidgetListeners();

        setupAdapter();

        return fragmentView;
    }


    // --------------------------- Widget controls

    private void getWidgetReferences(View fragmentView) {
        mAppListRecyclerView = fragmentView.findViewById(R.id.recyclerView_app_list);
    }


    private void setWidgetAttributes() {
        mAppListRecyclerView.setLayoutManager(new LinearLayoutManager( this.getActivity() ));       // set up the RecyclerView
        mAppListRecyclerView.setAdapter(new LauncherItemAdapter( this.getActivity() ));
    }


    /**
     * Set listeners to widgets
     */
    private void setWidgetListeners() {
    }


    // --------------------------- Subroutines

    private void setupAdapter() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager = Objects.requireNonNull( this.getActivity() ).getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        Log.i(TAG, "Found " + activities.size() + " activities.");
    }


    // --------------------------- Static encapsulation-leveraging methods

    /**
     * Create LauncherFragment instance
     */
    public static LauncherFragment newInstance() {
        return new LauncherFragment();
    }


}
