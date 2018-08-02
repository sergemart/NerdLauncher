package com.github.sergemart.mobile.nerdlauncher;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



class LauncherItemAdapter extends RecyclerView.Adapter<LauncherItemHolder> {

    private Activity mActivity;


    LauncherItemAdapter(Activity activity) {
        mActivity = activity;
    }


    // --------------------------- Overrides

    @NonNull
    @Override
    public LauncherItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View itemView = inflater.inflate(LauncherItemHolder.getLayoutR(), parent, false);
        return new LauncherItemHolder(mActivity, itemView);
    }


    // Should be lightweight to smooth scrolling
    @Override
    public void onBindViewHolder(@NonNull LauncherItemHolder holder, int position) {
        holder.bind();
    }


    @Override
    public int getItemCount() {
        return 0;
    }
}