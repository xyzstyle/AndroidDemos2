package xyz.sqlite3;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by xyz on 2018/4/11.
 */

public class MyContentProvider extends ContentProvider {
    private DiaryDAO dao;

    private static final UriMatcher matcher = new UriMatcher(
            UriMatcher.NO_MATCH);
    private static final int DIARY = 1;
    static {
        matcher.addURI("com.xyz.content_provider", "diary", DIARY);
    }

    private ContentResolver resolver;

    @Override
    public boolean onCreate() {
        dao = new DiaryDAO(this.getContext());
        resolver = getContext().getContentResolver();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        return dao.getRecords();
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        int flag = matcher.match(uri);
        switch (flag) {
            case DIARY:
                dao.insertRecord();
                Uri rUri =ContentUris.withAppendedId(uri, 1);
                resolver.notifyChange(rUri, null);//通知Loader已更新

                return rUri;
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
