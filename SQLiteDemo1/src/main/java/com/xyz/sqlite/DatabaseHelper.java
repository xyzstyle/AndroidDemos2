package com.xyz.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String TAG = "xyz";
	private static final String DATABASE_NAME = "dbForTest.db";
	private static final int DATABASE_VERSION = 5;
	private static final String TABLE_NAME_DIARY = "diary";
	private static final String DIARY_TITLE = "title";
	private static final String DIARY_BODY = "body";

	private static DatabaseHelper mDatabaseHelper;
	
	private DatabaseHelper(Context context) {

		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		Log.d(TAG, "CreateDB Version=" + DATABASE_VERSION);

	}
	public static DatabaseHelper getDatabaseHelper(Context context){
		
		if( mDatabaseHelper==null){
			 mDatabaseHelper=new  DatabaseHelper(context);
		}
		return  mDatabaseHelper;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		createTableDiary(db);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		Log.d(TAG, "onUpgrade=" + newVersion);
		dropTableDiary(db);
		createTableDiary(db);
		Log.d(TAG, "onUpgrade sucessed");
	}
	public void createTableDiary(SQLiteDatabase db){
		String sql = "CREATE TABLE " + TABLE_NAME_DIARY + " (" + DIARY_TITLE
				+ " text not null, " + DIARY_BODY + " text not null " + ");";

		db.execSQL(sql);
		Log.d(TAG, " CreateDB SUCESSED, SQL=" + sql);
	}
	public void dropTableDiary(SQLiteDatabase db){
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DIARY);
		
	}
	
}
