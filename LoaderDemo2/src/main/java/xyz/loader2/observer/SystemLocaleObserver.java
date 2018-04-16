package xyz.loader2.observer;

/**
 * My File Created by xyz on 2018/4/16.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import xyz.loader2.loader.AppListLoader;

/**
 * Created by Administrator on 2016/5/25.
 */
public class SystemLocaleObserver extends BroadcastReceiver{
    private static final String TAG = "ADP_SystemLocaleObserver";
    private static final boolean DEBUG = true;

    private AppListLoader mLoader;

    public SystemLocaleObserver(AppListLoader loader) {
        mLoader = loader;
        IntentFilter filter = new IntentFilter(Intent.ACTION_LOCALE_CHANGED);//The current device's locale has changed.
        mLoader.getContext().registerReceiver(this, filter);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        if (DEBUG) Log.i("TAG", "+++ The observer has detected a locale change!" +
//                " Notifying Loader... +++");
        Log.i("TAG","SystemLocaleObserver---onReceive()");
        // Tell the loader about the change.
        mLoader.onContentChanged();
    }
}
