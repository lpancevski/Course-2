package com.example.course.database_sqlite.services;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.course.database_sqlite.database.DBHelper;
import com.example.course.database_sqlite.entities.UserData;


public class MyUserDataCRUDDAOImpl implements IMyUserDataCRUDDAO {
	
	private static MyUserDataCRUDDAOImpl instance = null;
	private Context context = null;
	
	private MyUserDataCRUDDAOImpl(Context context) {
		this.context = context;
	}
	
	public static MyUserDataCRUDDAOImpl getInstance(Context context) {
		if (instance == null) {
			instance = new MyUserDataCRUDDAOImpl(context);
		}
		
		return instance;
	}

	@Override
	public void createNewUserData(UserData userData) {
		DBHelper dbHelper = DBHelper.getInstance(context);
		
		ContentValues contentValues = new ContentValues();
		contentValues.put(DBHelper.COLUMN_NAME_USERNAME, userData.getUserName());
		contentValues.put(DBHelper.COLUMN_NAME_PASSWORD, userData.getPassWord());
		contentValues.put(DBHelper.COLUMN_NAME_DELETED, userData.getDeleted());

		SQLiteDatabase db = dbHelper.getDB();
		db.insert(DBHelper.TABLE_NAME_USERDATA, null, contentValues);
	}

	@Override
	public void updateExistingUserData(UserData userData) {
		DBHelper dbHelper = DBHelper.getInstance(context);
		
		ContentValues contentValues = new ContentValues();
		contentValues.put(DBHelper.COLUMN_NAME_USERNAME, userData.getUserName());
		contentValues.put(DBHelper.COLUMN_NAME_PASSWORD, userData.getPassWord());
		contentValues.put(DBHelper.COLUMN_NAME_DELETED, userData.getDeleted());

		dbHelper.getDB().update(DBHelper.TABLE_NAME_USERDATA, 
				contentValues, "_id=" + userData.getId(), null);
	}

	@Override
	public void deleteExistingUserData(UserData userData) {
		DBHelper.getInstance(context).getDB().delete(DBHelper.TABLE_NAME_USERDATA, "_id="+userData.getId(), null);
	}

	@Override
	public UserData findSpecificUserDataByID(Integer userDataID) {
		DBHelper dbHelper = DBHelper.getInstance(context);
		
		UserData userData = null;
		Cursor cursor = dbHelper.getDB().query(DBHelper.TABLE_NAME_USERDATA, null, "_id="+userDataID, null, null, null, null);
		
		if (cursor != null && cursor.moveToFirst()) {
			userData = new UserData();
			
			userData.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_NAME_ID)));
			userData.setUserName(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME_USERNAME)));
			userData.setPassWord(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME_PASSWORD)));
			userData.setDeleted(cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_NAME_DELETED)));
			
		}
		
		return userData;
	}

	@Override
	public List<UserData> findAllUserDataObjects() {
		DBHelper dbHelper = DBHelper.getInstance(context);
		List<UserData>userDataList = null;
		
		UserData userData = null;
		
		Cursor cursor = dbHelper.getDB().query(DBHelper.TABLE_NAME_USERDATA, null, null, null, null, null, "_id DESC");
		cursor.moveToFirst();
		
		if (cursor != null) {
			userDataList = new LinkedList<UserData>();
			do {
				userData = new UserData();
				
				userData.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_NAME_ID)));
				userData.setUserName(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME_USERNAME)));
				userData.setPassWord(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME_PASSWORD)));
				userData.setDeleted(cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_NAME_DELETED)));
			
				userDataList.add(userData);
			} while (cursor.moveToNext());
			
		}
		
		return userDataList;
	}

}
