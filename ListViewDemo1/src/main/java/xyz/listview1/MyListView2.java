package xyz.listview1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

/**
 * Created by xyz on 2018/4/6.
 */

public class MyListView2 extends AppCompatActivity {
    private SimpleCursorAdapter adapter;
    private final int CURSOR_LOADER_ID = 1;
    private final int READ_CONTACTS_REQUEST_CODE=2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPower();

    }

    class MyLoaderCallbacks implements LoaderManager.LoaderCallbacks<Cursor> {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            Uri uri = ContactsContract.Contacts.CONTENT_URI;
            String[] projection = new String[]{
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.DISPLAY_NAME,
                    ContactsContract.Contacts.CONTACT_STATUS
            };

            String selection = "((" + ContactsContract.Contacts.DISPLAY_NAME + " NOTNULL) AND " +
                    "(" + ContactsContract.Contacts.HAS_PHONE_NUMBER + " =1) AND " +
                    "(" + ContactsContract.Contacts.DISPLAY_NAME + " != ''))";

            String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
            return new CursorLoader(MyListView2.this, uri, projection, selection, null, sortOrder);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            adapter.swapCursor(data);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            adapter.swapCursor(null);
        }


    }

    public void requestPower() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //判断是否已经赋予权限
            // if之前请求过此权限但用户拒绝了请求，方法将返回true,会重新请求
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

                //申请权限，字符串数组内是一个或多个要申请的权限，1是申请权限结果的返回参数，在onRequestPermissionsResult可以得知申请结果
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, READ_CONTACTS_REQUEST_CODE);

            } else {
                fillData();
                //Toast.makeText(this,"权限已申请",Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_CONTACTS_REQUEST_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PERMISSION_GRANTED && permissions[i].equals(Manifest.permission.READ_CONTACTS)) {
                    Toast.makeText(this, "" + "权限" + permissions[i] + "申请成功", Toast.LENGTH_SHORT).show();
                    fillData();
                } else {
                    Toast.makeText(this, "" + "权限" + permissions[i] + "申请失败", Toast.LENGTH_SHORT).show();
                }

            }

        }
    }

    private void fillData() {
        ListView listView = new ListView(this);
        adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                null,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.CONTACT_STATUS},
                new int[]{android.R.id.text1, android.R.id.text2},
                0);
        listView.setAdapter(adapter);
        LoaderManager lm = getSupportLoaderManager();
        lm.initLoader(CURSOR_LOADER_ID, new Bundle(), new MyLoaderCallbacks());
        setContentView(listView);
    }
}
