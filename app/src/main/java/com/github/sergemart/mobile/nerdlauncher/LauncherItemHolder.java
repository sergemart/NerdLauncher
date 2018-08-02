package com.github.sergemart.mobile.nerdlauncher;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;


class LauncherItemHolder extends RecyclerView.ViewHolder {

    private Context mContext;


    LauncherItemHolder(Context context, View itemView) {
        super(itemView);

        mContext = context;
    }


    // --------------------------- Public methods

    public void bind() {
    }


    // --------------------------- Static encapsulation-leveraging methods

    public static int getLayoutR() {
        return R.layout.list_item_app;
    }

}