package com.xyz.sqlite;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MyActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
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

	DiaryDAO diary;
	private ListView list;
	private SimpleCursorAdapter simpleCursorAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		prepareListener();
		init();
		diary = new DiaryDAO(this);

	}

	private void init() {
		list = (ListView) findViewById(R.id.list);
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(listener1);

		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(listener2);

		button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(listener3);
		button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(listener4);

		button5 = (Button) findViewById(R.id.button5);
		button5.setOnClickListener(listener5);
		listener1 = new OnClickListener() {
			public void onClick(View v) {
				if(diary.createTable()){
					setTitle("数据表成功重建");
				}else{
					setTitle("数据表重建错误");
				}
			}
		};
		listener2 = new OnClickListener() {
			public void onClick(View v) {
				if(diary.dropTable()){
					setTitle("数据表成功删除");
				}else{
					setTitle("数据表删除错误");
				}
			}
		};
		listener3 = new OnClickListener() {
			public void onClick(View v) {
				if(diary.insertItem()){
					setTitle("插入两条数据成功");
				}else{
					setTitle("插入两条数据失败");
				}
			}
		};
		listener4 = new OnClickListener() {
			public void onClick(View v) {
				if(diary.deleteItem()){
					setTitle("删除title为google的记录");
				}else{
					setTitle("删除不了title为google的记录");
				}
			}
		};
		listener5 = new OnClickListener() {
			public void onClick(View v) {
				showItems();
			}
		};
		simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.onerow, null
				, new String[]{DatabaseHelper.DIARY_TITLE,DatabaseHelper.DIARY_BODY}
				, new int[]{R.id.title, R.id.body}, SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		list.setAdapter(simpleCursorAdapter);

	}

	// 按钮响应函数
	private void prepareListener() {

	}
	MyAdapter myAdapter;
	MyView myView;
	private void showItems() {
		Cursor cur = diary.getRecords();
		if (!(cur == null)) {
			int num=cur.getCount();
			setTitle(Integer.toString(num) + " 条记录");
			myView=(MyView)findViewById(R.id.table1);
			myAdapter=new MyAdapter(this, R.layout.onerow, cur, new int[]{R.id.title,R.id.body});
			myView.setAdapter(myAdapter);
			
		}
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return new Loader(MyActivity.this);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {

	}
}