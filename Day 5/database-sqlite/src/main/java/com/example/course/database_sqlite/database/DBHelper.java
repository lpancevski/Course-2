package com.example.course.database_sqlite.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper {
	
	private static final String DB_NAME = "mydatabase.db";
	private static final Integer DB_VERSION = 1;
	
	public static final String TABLE_NAME_USERDATA = "userdata";
	public static final String COLUMN_NAME_ID = "_id";
	public static final String COLUMN_NAME_USERNAME = "username";
	public static final String COLUMN_NAME_PASSWORD = "password";
	public static final String COLUMN_NAME_DELETED = "deleted";
	
	public static final String TABLE_NAME_ADDRESSES = "addresses";
	public static final String COLUMN_NAME_ADDRESS_ID = "_id";
	public static final String COLUMN_NAME_ADDRESS1 = "address1";
	public static final String COLUMN_NAME_ADDRESS2 = "address2";
	
	private static DBHelper dbHelper = null;
	
	private SQLiteDatabase database = null;
	private MyDBOpenHelper dbOpenHelper = null;
	
	public static DBHelper getInstance(Context context) {
		if (dbHelper == null) {
			dbHelper = new DBHelper(context);
		}
		
		return dbHelper;
	}
	
	private DBHelper(Context context) {
		dbOpenHelper = new MyDBOpenHelper(context, DB_NAME, null, DB_VERSION);
		if (this.database == null) {
			this.database = dbOpenHelper.getWritableDatabase();
		}
		
	}
	
	public SQLiteDatabase getDB() {
		return this.database;
	}
	
	public void closeDB() {
		this.database.close();
	}
	
	private static class MyDBOpenHelper extends SQLiteOpenHelper {
		
		private static final String CREATE_TABLE_USERDATA = "create table "
				+ DBHelper.TABLE_NAME_USERDATA + "("
				+ COLUMN_NAME_ID + " integer primary key autoincrement, "
				+ COLUMN_NAME_USERNAME + " varchar, "
				+ COLUMN_NAME_PASSWORD + " varchar, "
				+ COLUMN_NAME_DELETED + " integer)";				
		private static final String CREATE_TABLE_ADDRESSES = "create table "
				+ DBHelper.TABLE_NAME_ADDRESSES + "("
				+ COLUMN_NAME_ADDRESS_ID + " integer primary key autoincrement, "
				+ COLUMN_NAME_ADDRESS1 + " varchar, "
				+ COLUMN_NAME_ADDRESS2 + " varchar )";				

		public MyDBOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			try {
				db.execSQL(MyDBOpenHelper.CREATE_TABLE_USERDATA);
				db.execSQL(MyDBOpenHelper.CREATE_TABLE_ADDRESSES);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			try {
				db.execSQL("DROP TABLE IF EXISTS " + DBHelper.TABLE_NAME_USERDATA);
				db.execSQL("DROP TABLE IF EXISTS " + DBHelper.TABLE_NAME_ADDRESSES);
				this.onCreate(db);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	
}
