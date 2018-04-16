package xyz.loader2.observer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import xyz.loader2.loader.AppListLoader;

/**
 * Created by Administrator on 2016/5/25.
 */
public class InstalledAppsObserver extends BroadcastReceiver {



    private AppListLoader mLoader;

    public InstalledAppsObserver(AppListLoader loader) {
        mLoader = loader;

        // Register for events related to application installs/removals/updates.
        IntentFilter filter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
        filter.addDataScheme("package");
        mLoader.getContext().registerReceiver(this, filter);

        // Register for events related to sdcard installation.
        IntentFilter sdFilter = new IntentFilter();
        sdFilter.addAction(Intent.ACTION_EXTERNAL_APPLICATIONS_AVAILABLE);
        sdFilter.addAction(Intent.ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE);
        mLoader.getContext().registerReceiver(this, sdFilter);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        if (DEBUG) Log.i("TAG", "+++ The observer has detected an application change!" +
//                " Notifying Loader...");
        Log.i("TAG","InstalledAppsObserver---onReceive()");
        //The observer has detected an application change!" + " Notifying Loader..
        // Tell the loader about the change.
        mLoader.onContentChanged();
    }
}
