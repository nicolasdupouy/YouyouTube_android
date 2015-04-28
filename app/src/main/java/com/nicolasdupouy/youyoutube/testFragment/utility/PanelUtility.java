package com.nicolasdupouy.youyoutube.testFragment.utility;

import android.content.Context;

import com.nicolasdupouy.youyoutube.R;

/**
 * Created by nicolasdupouy on 26/04/2015.
 */
public final class PanelUtility {

    private PanelUtility() {}

    public static boolean isDualPanel(Context context) {
        return context.getResources().getBoolean(R.bool.dual_panel);
    }

    public static boolean isMonoPanel(Context context) {
        return !isDualPanel(context);
    }
}
