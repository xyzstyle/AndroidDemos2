package xyz.provider3;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private Button btn;
    private ContentResolver resolver;
    private MyAdapter myAdapter;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        btn = (Button) findViewById(R.id.btn);
        resolver = getContentResolver();
        cursor = resolver.query(Uri.parse("content://com.cj.mycontentprovider/contact"), null, null,
                null, null);
        myAdapter = new MyAdapter(this,cursor);
        lv.setAdapter(myAdapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivityForResult(new Intent(MainActivity.this,AddActivity.class),
                        1);
            }
        });
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", "zzz");
                contentValues.put("number", "2342423");
                getContentResolver().insert(Uri.parse("content://com.cj.mycontentprovider/contact"), contentValues);
            }
        });
        String[] s = new String[2];
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                resolver.delete(Uri.parse("content://com.cj.mycontentprovider/contact"),
                        null,null);


            }
        });
        resolver.registerContentObserver(Uri.parse("content://com.cj.mycontentprovider/contact"),
                true,new MyContentObserver(new Handler()));
    }


    private class MyContentObserver extends ContentObserver{

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public MyContentObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            myAdapter.notifyDataSetChanged();

        }
    }
    private static class MyAdapter extends CursorAdapter{
        private Cursor cursor;
        private Context context;
        public MyAdapter(Context context, Cursor c) {
            super(context, c);
            this.context = context;
            this.cursor = c;
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.item, null);
            if(cursor !=null){
                view.setTag(cursor.getInt(cursor.getColumnIndex("_id")));
            }
            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView name =  view.findViewById(R.id.tv_name);
            TextView num =  view.findViewById(R.id.tv_num);
            if(cursor !=null){
                name.setText(cursor.getString(cursor.getColumnIndex("name")));
                num.setText(cursor.getString(cursor.getColumnIndex("number")));
            }
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }
    }


}
