package com.example.course.database_sqlite.entities;

import android.content.ContentValues;

import com.example.course.database_sqlite.database.DBHelper;


public class UserData {

	private Integer id;
	private String userName;
	private String passWord;
	private Integer deleted;
	
	public UserData(){}

	public UserData(Integer id, String userName, String passWord,
			Integer deleted) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.deleted = deleted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	
	public ContentValues returnAsContentValue() {
		ContentValues contentValues = new ContentValues();
		contentValues.put(DBHelper.COLUMN_NAME_USERNAME, this.userName);
		contentValues.put(DBHelper.COLUMN_NAME_PASSWORD, this.passWord);
		contentValues.put(DBHelper.COLUMN_NAME_DELETED, this.deleted);
		
		return contentValues;
	}
	
	@Override
	public String toString() {
		return "UserData [id=" + id + ", userName=" + userName + ", passWord="
				+ passWord + ", deleted=" + deleted + "]";
	}
	
}
