package xyz.loader2.loader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import xyz.loader2.observer.InstalledAppsObserver;
import xyz.loader2.observer.SystemLocaleObserver;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2016/5/25.
 */
public class AppListLoader  extends AsyncTaskLoader<List<AppEntry>> {

    final PackageManager mPm;//包管理器
    private List<AppEntry> mApps;//装在应用程序实体的容器

    // An observer to notify the Loader when new apps are installed/updated.
    private InstalledAppsObserver mAppsObserver;//非系统应用程序安装或者卸载的广播接收器

    // The observer to notify the Loader when the system Locale has been changed.
    private SystemLocaleObserver mLocaleObserver;//系统应用程序安装或者卸载的广播接收器

    public AppListLoader(Context context) {
        super(context);
        mPm = getContext().getPackageManager();
        Log.i("TAG","AppListLoader(Context)");
    }

    @Override
    protected void onStartLoading() {
        Log.i("TAG","onStartLoading()");
        if(mApps!=null){
            deliverResult(mApps);
        }
        // Register the observers that will notify the Loader when changes are made.
        if (mAppsObserver == null) {
            mAppsObserver = new InstalledAppsObserver(this);//注册一个非系统应用程序的接收器
        }
        if (mLocaleObserver == null) {
            mLocaleObserver = new SystemLocaleObserver(this);//注册一个系统应用程序的接收器
        }

        if (takeContentChanged()) {
            forceLoad();
        } else if (mApps == null) {
            forceLoad();//强制加载数据
        }
    }

    @Override
    public void forceLoad() {
        Log.i("TAG","forceLoad()");
        super.forceLoad();
    }

    @Override
    public List<AppEntry> loadInBackground() {
        Log.i("TAG","loadInBackground()");
        List<ApplicationInfo> apps=mPm.getInstalledApplications(0);
//        public static final int FILTER_ALL_APP = 0; // 所有应用程序
//        public static final int FILTER_SYSTEM_APP = 1; // 系统程序
//        public static final int FILTER_THIRD_APP = 2; // 第三方应用程序
//        public static final int FILTER_SDCARD_APP = 3; // 安装在SDCard的应用程序
        if(apps==null){
            apps=new ArrayList<>();
        }
        List<AppEntry> entries=new ArrayList<>(apps.size());
        for(int i=0;i<apps.size();i++){
            AppEntry appEntry=new AppEntry(this,apps.get(i));
            appEntry.loadLabel(getContext());
            entries.add(appEntry);
        }
        //Sort the list
        Collections.sort(entries,ALPHA_COMPARATOR);//对应用程序进行排序
        return entries;
    }

    @Override
    public void deliverResult(List<AppEntry> datas) {
        Log.i("TAG","deliverResult()");
        if(isReset()){
            if(datas!=null){
                releaseResources(datas);
                return;
            }
        }
        List<AppEntry> oldApps=mApps;
        mApps=datas;

        if(isStarted()){
            super.deliverResult(datas);
        }

        if(oldApps!=null&&oldApps!=datas){
            releaseResources(oldApps);
        }
    }

    @Override
    protected void onStopLoading() {
        Log.i("TAG","onStopLoading()");
        cancelLoad();
    }

    @Override
    protected void onReset() {
        Log.i("TAG","onReset()");
        onStopLoading();
        // At this point we can release the resources associated with 'apps'.
        if (mApps != null) {
            releaseResources(mApps);
            mApps = null;
        }
        // The Loader is being reset, so we should stop monitoring for changes.
        if (mAppsObserver != null) {
            getContext().unregisterReceiver(mAppsObserver);
            mAppsObserver = null;
        }
        if (mLocaleObserver != null) {
            getContext().unregisterReceiver(mLocaleObserver);
            mLocaleObserver = null;
        }
    }

    @Override
    public void onCanceled(List<AppEntry> apps) {  // Attempt to cancel the current asynchronous load.
        super.onCanceled(apps);
        Log.i("TAG","onCanceled()");
        releaseResources(apps);
    }


    /**
     * Helper method to take care of releasing resources associated with an
     * actively loaded data set.
     */
    private void releaseResources(List<AppEntry> apps) {
        // For a simple List, there is nothing to do. For something like a Cursor,
        // we would close it in this method. All resources associated with the
        // Loader should be released here.
    }

    /**
     * Performs alphabetical comparison of {@link AppEntry} objects. This is
     * used to sort queried data in {@link }.
     */
    private static final Comparator<AppEntry> ALPHA_COMPARATOR = new Comparator<AppEntry>() {
        Collator sCollator = Collator.getInstance();

        @Override
        public int compare(AppEntry object1, AppEntry object2) {
            return sCollator.compare(object1.getLabel(), object2.getLabel());
        }
    };
}



/***
 * public static final int FILTER_ALL_APP = 0; // 所有应用程序       
 * public static final int FILTER_SYSTEM_APP = 1; // 系统程序       
 * public static final int FILTER_THIRD_APP = 2; // 第三方应用程序       
 * public static final int FILTER_SDCARD_APP = 3; // 安装在SDCard的应用程序
 * */


