package com.github.sergemart.mobile.nerdlauncher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


class LauncherItemHolder extends RecyclerView.ViewHolder {

    private View mItemView;
    private TextView mAppNameTextView;
    private ImageView mAppIconImageView;

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
        mAppNameTextView = itemView.findViewById(R.id.textView_app_name);
        mAppIconImageView = itemView.findViewById(R.id.imageView_app_icon);
    }


    /**
     * Set listeners to widgets
     */
    private void setWidgetListeners() {
        mItemView.setOnClickListener((view) -> {
            ActivityInfo activityInfo = mResolveInfo.activityInfo;
            Intent intent = new Intent(Intent.ACTION_MAIN)
                    .setClassName(activityInfo.applicationInfo.packageName, activityInfo.name)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);                                       // to start activities in their own tasks
            mContext.startActivity(intent);
        });
    }


    // --------------------------- Public methods

    /**
     * Should be lightweight to smooth scrolling. All possible preparations are made outside this method
     */
    public void bind(int position) {
        mResolveInfo = mResolveInfos.get(position);

        String appLabel = mResolveInfo.loadLabel(mPackageManager).toString();
        mAppNameTextView.setText(appLabel);

        Drawable appIcon = mResolveInfo.loadIcon(mPackageManager);
        mAppIconImageView.setImageDrawable(appIcon);
    }


    // --------------------------- Static encapsulation-leveraging methods

    public static int getLayoutR() {
        return R.layout.list_item_app;
        //return android.R.layout.simple_list_item_1;
    }

}