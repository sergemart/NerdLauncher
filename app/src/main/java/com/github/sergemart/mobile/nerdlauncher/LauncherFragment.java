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

import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class LauncherFragment extends Fragment {

    private static final String TAG = "LauncherFragment";

    private RecyclerView mAppListRecyclerView;
    private List<ResolveInfo> mResolveInfos;


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
        this.setupAdapter();
        mAppListRecyclerView.setAdapter(new LauncherItemAdapter(this.getActivity(), mResolveInfos));
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
        mResolveInfos = packageManager.queryIntentActivities(intent, 0);

        // Sort the list using a lambda implementing the Comparator and its compare()
        Collections.sort(mResolveInfos, (ResolveInfo a, ResolveInfo b) -> String.CASE_INSENSITIVE_ORDER.compare(
                a.loadLabel(packageManager).toString(),
                b.loadLabel(packageManager).toString()
        ));

        Log.i(TAG, "Found " + mResolveInfos.size() + " activities.");
    }


    // --------------------------- Static encapsulation-leveraging methods

    /**
     * Create LauncherFragment instance
     */
    public static LauncherFragment newInstance() {
        return new LauncherFragment();
    }


}
