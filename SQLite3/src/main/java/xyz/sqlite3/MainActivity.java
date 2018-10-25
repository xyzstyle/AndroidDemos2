package xyz.sqlite3;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    OnClickListener listener1 = null;
    OnClickListener listener2 = null;
    OnClickListener listener3 = null;
    OnClickListener listener4 = null;
    OnClickListener listener5 = null;

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    CursorLoader loader;
    DiaryDAO diary;
    private ListView list;
    private SimpleCursorAdapter simpleCursorAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        diary = new DiaryDAO(this);
        init();
        LoaderManager lm = getSupportLoaderManager();
        lm.initLoader(22, new Bundle(), this);


    }

    private void init() {
        list = findViewById(R.id.list);

        listener1 = new OnClickListener() {
            public void onClick(View v) {
                if (diary.createTable()) {
                    show("数据表成功重建");
                } else {
                    show("数据表重建错误");
                }
            }
        };
        listener2 = new OnClickListener() {
            public void onClick(View v) {
                if (diary.dropTable()) {
                    show("数据表成功删除");
                } else {
                    show("数据表删除错误");
                }
            }
        };
        listener3 = new OnClickListener() {
            public void onClick(View v) {
                if (diary.insertRecord()) {
                    show("插入两条数据成功");
                    getContentResolver().notifyChange(Uri.parse("content://com.xyz.content_provider"), null);
                    //LoaderManager lm = getSupportLoaderManager();
                    //lm.restartLoader(22, null, MainActivity.this);
                    loader.onContentChanged();
                } else {
                    show("插入两条数据失败");
                }
            }
        };
        listener4 = new OnClickListener() {
            public void onClick(View v) {
                if (diary.deleteRecord()) {
                    getSupportLoaderManager().restartLoader(22, new Bundle(), MainActivity.this);
                    show("删除title为google的记录");
                } else {
                    show("删除不了title为google的记录");
                }
            }
        };
        listener5 = new OnClickListener() {
            public void onClick(View v) {
                //showItems();
            }
        };


        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(listener1);

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(listener2);

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(listener3);
        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(listener4);

        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(listener5);
        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.item_listview, null
                , new String[]{DatabaseHelper.DIARY_TITLE, DatabaseHelper.DIARY_BODY}
                , new int[]{R.id.title, R.id.body}, SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        list.setAdapter(simpleCursorAdapter);

    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        loader = new CursorLoader(this,
                Uri.parse("content://com.xyz.content_provider"), null, null,
                null, null);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        simpleCursorAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        simpleCursorAdapter.swapCursor(null);
    }


    private void show(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();

    }


}