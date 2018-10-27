package xyz.provider3;

/**
 * My File Created by xyz on 2018/10/26.
 */


import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    private final static int CONTACT = 1;

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI("com.cj.mycontentprovider", "contact", CONTACT);
    }

    private MyDBHelp dbHelp;

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int id = 0;
        if (uriMatcher.match(uri) == CONTACT) {
            SQLiteDatabase database = dbHelp.getWritableDatabase();
            id = database.delete("contact", selection, selectionArgs);
            contentResolver.notifyChange(uri, null);
        }
        return id;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri u = null;
        if (uriMatcher.match(uri) == CONTACT) {
            SQLiteDatabase database = dbHelp.getWritableDatabase();

            long d = database.insert("contact", "_id", values);
            u = ContentUris.withAppendedId(uri, d);
            contentResolver.notifyChange(u, null);
        }
        return u;

    }

    private ContentResolver contentResolver;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        contentResolver = context.getContentResolver();
        dbHelp = new MyDBHelp(context, "contact.db", null, 1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        if (uriMatcher.match(uri) == CONTACT) {
            SQLiteDatabase database = dbHelp.getReadableDatabase();
            cursor = database.query("contact", projection, selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, uri);
        }
        return cursor;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     *
     */
    private static class MyDBHelp extends SQLiteOpenHelper {

        public MyDBHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = "create table contact(_id integer primary key autoincrement," +
                    "name text not null,number text not null);";
            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onCreate(db);

        }
    }
}

