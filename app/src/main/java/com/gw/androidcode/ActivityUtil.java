package com.gw.androidcode;

import android.app.Activity;
import android.content.Intent;

import com.gw.androidcode.activity.AsyncTaskActivity;

/**
 * Created by GongWen on 17/2/28.
 */

public class ActivityUtil {
    public static void goAsyncTaskActivity(Activity mActivity) {
        mActivity.startActivity(new Intent(mActivity, AsyncTaskActivity.class));
    }
}
