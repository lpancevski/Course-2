package com.example.course.database_sqlite.services;

import com.example.course.database_sqlite.entities.UserData;

import java.util.List;

public interface IMyUserDataCRUDDAO {
	public void createNewUserData(UserData userData);
	public void updateExistingUserData(UserData userData);
	public void deleteExistingUserData(UserData userData);
	public UserData findSpecificUserDataByID(Integer userDataID);
	public List<UserData>findAllUserDataObjects();
}
