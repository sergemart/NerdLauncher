package com.github.sergemart.mobile.nerdlauncher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;


class LauncherItemHolder extends RecyclerView.ViewHolder {

    private View mItemView;
    private TextView mActivityNameTextView;

    private Context mContext;
    private PackageManager mPackageManager;
    private List<ResolveInfo> mResolveInfos;
    private ResolveInfo mResolveInfo;


    LauncherItemHolder(Context context, View itemView, List<ResolveInfo> resolveInfos, PackageManager packageManager) {
        super(itemView);

        mContext = context;
        mItemView = itemView;
        mResolveInfos = resolveInfos;
        mPackageManager = packageManager;

        this.getWidgetReferences(itemView);
        this.setWidgetListeners();
    }


    // --------------------------- Widget controls

    /**
     * Get references to widgets
     */
    private void getWidgetReferences(View itemView) {
        mActivityNameTextView = itemView.findViewById(R.id.textView_activity_name);
    }


    /**
     * Set listeners to widgets
     */
    private void setWidgetListeners() {
        mItemView.setOnClickListener((view) -> {
            ActivityInfo activityInfo = mResolveInfo.activityInfo;
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setClassName(activityInfo.applicationInfo.packageName, activityInfo.name);
            mContext.startActivity(intent);
        });
    }


    // --------------------------- Public methods

    /**
     * Should be lightweight to smooth scrolling. All possible preparations are made outside this method
     */
    public void bind(int position) {
        mResolveInfo = mResolveInfos.get(position);
        mActivityNameTextView.setText(mResolveInfo.loadLabel(mPackageManager).toString());
    }


    // --------------------------- Static encapsulation-leveraging methods

    public static int getLayoutR() {
        return R.layout.list_item_app;
        //return android.R.layout.simple_list_item_1;
    }

}