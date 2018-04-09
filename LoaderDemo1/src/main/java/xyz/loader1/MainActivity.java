package xyz.loader1;

import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private static ContentResolver contentResolver;
    private ListView lv_show;
    private SimpleCursorAdapter simpleCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = (SearchView) findViewById(R.id.searchView);
        lv_show = (ListView) findViewById(R.id.lv_show);
        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.view_list_item, null
                , new String[]{Telephony.Sms.ADDRESS, Telephony.Sms.BODY}
                , new int[]{R.id.tv_address, R.id.tv_body}, SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lv_show.setAdapter(simpleCursorAdapter);
        //监听SearchView的文本查询监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            //文本提交的时候
            @Override
            public boolean onQueryTextSubmit(String query) {

                return true;//ture 自己处理。false自己处完成后丢给系统处理
            }

            //文本改变的时候调用
            @Override
            public boolean onQueryTextChange(String newText) {
                //重新加载Loader
                Bundle bundle = new Bundle();
                bundle.putString("keyword", newText);
                getLoaderManager().restartLoader(1, bundle, callbacks);
                return true;//ture 自己处理。false自己处完成后丢给系统处理
            }
        });

        contentResolver = getContentResolver();
        //得到LoaderManager，初始化Loader
        getLoaderManager().initLoader(1, null, callbacks);
    }

    private LoaderManager.LoaderCallbacks<Cursor> callbacks = new LoaderManager.LoaderCallbacks<Cursor>() {

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            if (args == null)
                return new CursorLoader(MainActivity.this, Uri.parse("content://sms"), null, null, null, null);
            else {
                String keyword = args.getString("keyword");
                //包含keyword关键字的联系人都查询出来
                return new CursorLoader(MainActivity.this, Uri.parse("content://sms"), null, Telephony.Sms.ADDRESS + " like ?",
                        new String[]{"%" + keyword + "%"}
                        , null);
            }
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            //数据更新
            simpleCursorAdapter.swapCursor(data);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            //释放Cursor资源
            simpleCursorAdapter.swapCursor(null);
        }
    };


}
