package com.xyz.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class MyAdapter {
	
	private Cursor cursor;
	private int resource;
	private Context context;
	int[] ids;
	MyAdapter(Context context,int resource,Cursor cursor,int[] ids){
		this.context=context;
		this.cursor=cursor;
		this.resource=resource;
		this.ids=ids;
		
	}
	
	public int getItemsCount(){
		
		return cursor.getCount();
	}
	
	public View getView(int position){
		
		LayoutInflater mInflater;
		mInflater=LayoutInflater.from(context);
		View view = mInflater.inflate(resource,null,false);
		cursor.moveToPosition(position);
		for(int i=0;i<ids.length;i++){
			TextView tv=(TextView)view.findViewById(ids[i]);
			tv.setText(cursor.getString(i));
		}
		
		return view;
	}
	
	


}
