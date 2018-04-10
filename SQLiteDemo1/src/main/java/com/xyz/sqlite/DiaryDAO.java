package com.xyz.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DiaryDAO {
	
	private static final String TAG="xyz";
	private DatabaseHelper mOpenHelper;
	
	private static final String TABLE_NAME = "diary";
	private static final String TITLE = "title";
	private static final String BODY = "body";
	
	DiaryDAO(Context context){
		mOpenHelper=DatabaseHelper.getDatabaseHelper(context);
	}

	/*
	 * 重新建立数据表
	 */
	public  boolean  createTable() {
		Log.d(TAG, "CreateTable before getWritableDatabase");
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Log.d(TAG, "CreateTable after getWritableDatabase");
		
		try {
			
			mOpenHelper.dropTableDiary(db);
			mOpenHelper.createTableDiary(db);
			Log.d(TAG, "Create DB Table");
			return true;
			
		} catch (SQLException e) {
			
			Log.d(TAG, e.getMessage());
			return false;
		}
	}

	/*
	 * 删除数据表
	 */
	public  boolean  dropTable() {
		Log.d(TAG, "drop table before getWritableDatabase");
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Log.d(TAG, "drop table after getWritableDatabase");
		try {
			mOpenHelper.dropTableDiary(db);
			return true;
			
		} catch (SQLException e) {
			
			Log.d(TAG, e.getMessage());
			return false;
		}
	}

	/*
	 * 插入两条数据
	 */
	public boolean insertItem() {
		Log.d(TAG, "insertItem before getWritableDatabase");
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Log.d(TAG, "insertItem after getWritableDatabase");
		String sql1 = "insert into " + TABLE_NAME + " (" + TITLE + ", " + BODY
				+ ") values('google', 'you have something to do');";
		String sql2 = "insert into " + TABLE_NAME + " (" + TITLE + ", " + BODY
				+ ") values('android', 'its a rapid progress');";
		try {
			Log.d(TAG, "Insert sql=" + sql1);
			Log.d(TAG, "Insert sql=" + sql2);
			db.execSQL(sql1);
			db.execSQL(sql2);
			Log.d(TAG, "insertItem sucessed");
			return true;
			
		} catch (SQLException e) {
			
			Log.d(TAG, e.getMessage());
			return false;
		}
	}

	/*
	 * 删除部分数据
	 */
	public  boolean  deleteItem() {
		try {
			Log.d(TAG, "deleteItem before getWritableDatabase");
			SQLiteDatabase db = mOpenHelper.getWritableDatabase();
			Log.d(TAG, "deleteItem before getWritableDatabase");
			db.delete(TABLE_NAME, " title = 'google'", null);
			return true;
			
		} catch (SQLException e) {
			
			Log.d(TAG, e.getMessage());
			return false;

		}

	}

	/*
	 * 获取所有记录的Cursor。
	 */
	
	public  Cursor showItems() {
		Cursor cur = null;
		try {
			Log.d(TAG, "showItems before getReadableDatabase");
			SQLiteDatabase db = mOpenHelper.getReadableDatabase();
			Log.d(TAG, "showItems after getReadableDatabase");
			String col[] = { TITLE, BODY };
			return cur = db.query(TABLE_NAME, col, null, null, null, null, null);
			
		} catch (SQLException e) {
			
			Log.d(TAG, e.getMessage());
		}
		return cur;
		
		
	}

}
