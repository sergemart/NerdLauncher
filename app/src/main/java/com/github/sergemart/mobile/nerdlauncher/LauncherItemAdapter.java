package com.github.sergemart.mobile.nerdlauncher;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


class LauncherItemAdapter extends RecyclerView.Adapter<LauncherItemHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private PackageManager mPackageManager;
    private List<ResolveInfo> mResolveInfos;


    LauncherItemAdapter(Context context, List<ResolveInfo> resolveInfos) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mPackageManager = context.getPackageManager();
        mResolveInfos = resolveInfos;
    }


    // --------------------------- Overrides

    @NonNull
    @Override
    public LauncherItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(LauncherItemHolder.getLayoutR(), parent, false);
        return new LauncherItemHolder(mContext, itemView, mResolveInfos, mPackageManager);
    }


    /**
     * Should be lightweight to smooth scrolling. All possible preparations are made outside this method
     */
    @Override
    public void onBindViewHolder(@NonNull LauncherItemHolder holder, int position) {
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return mResolveInfos.size();
    }
}