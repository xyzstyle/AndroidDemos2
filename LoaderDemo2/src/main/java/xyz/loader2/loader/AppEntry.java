package xyz.loader2.loader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;

import java.io.File;

/**
 * Created by Administrator on 2016/5/25.
 */
public class AppEntry {
    private String mLabel;//应用名字
    private Drawable mIcon;//应用图标

    private final AppListLoader mLoader;
    private final ApplicationInfo mInfo;//<application>节点信息，只有一个
    //PackageInfo、ApplicationInfo、ActivityInfo、ResolveInfo四种信息类的一种
    private final File mApkFile;

    private boolean mMounted;

    public AppEntry(AppListLoader mLoader,ApplicationInfo mInfo ) {
        this.mInfo = mInfo;
        this.mLoader = mLoader;
        mApkFile=new File(mInfo.sourceDir);//sourceDir=Full path to the location of this package
    }

    public ApplicationInfo getApplicationInfo() {
        return mInfo;
    }

    public String getLabel() {
        return mLabel;
    }

    public Drawable getIcon() {
        if (mIcon == null) {
            if (mApkFile.exists()) {
                mIcon = mInfo.loadIcon(mLoader.mPm);
                //public Drawable loadIcon (PackageManager pm){}获取应用图标
                return mIcon;
            } else {
                mMounted = false;
            }
        } else if (!mMounted) {
            // If the app wasn't mounted but is now mounted, reload its icon.
            if (mApkFile.exists()) {
                mMounted = true;
                mIcon = mInfo.loadIcon(mLoader.mPm);
                return mIcon;
            }
        } else {
            return mIcon;
        }

        return mLoader.getContext().getResources()
                .getDrawable(android.R.drawable.sym_def_app_icon);//否则返回默认的小机器人
    }

    @Override
    public String toString() {
        return mLabel;
    }

    void loadLabel(Context context) {
        if (mLabel == null || !mMounted) {
            if (!mApkFile.exists()) {
                mMounted = false;
                mLabel = mInfo.packageName;
            } else {
                mMounted = true;
                CharSequence label = mInfo.loadLabel(context.getPackageManager());
                mLabel = label != null ? label.toString() : mInfo.packageName;
            }
        }
    }
}
