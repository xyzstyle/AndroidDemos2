package com.xyz.sqlite;

import android.content.ContentValues;
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
		mOpenHelper=new DatabaseHelper(context);
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
	public boolean insertRecord() {
		Log.d(TAG, "insertItem before getWritableDatabase");
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(TITLE, "google");
		cv.put(BODY, "you have something to do");
		db.insert(TABLE_NAME, null, cv);
		return db.insert(TABLE_NAME, null, cv)!=-1;

	}

	/*
	 * 删除部分数据
	 */
	public  boolean  deleteRecord() {
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
	
	public  Cursor getRecords() {
		Cursor cur = null;
		try {
			Log.d(TAG, "showItems before getReadableDatabase");
			SQLiteDatabase db = mOpenHelper.getReadableDatabase();
			Log.d(TAG, "showItems after getReadableDatabase");
			String col[] = { "_id",TITLE, BODY };
			cur = db.query(TABLE_NAME, col, null, null, null, null, null);
			return cur;
			
		} catch (SQLException e) {
			
			Log.d(TAG, e.getMessage());
		}
		return cur;
		
		
	}

}
