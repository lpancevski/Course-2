package com.example.course.webservices_volley.callers;

import org.json.JSONArray;

public interface RequestPersonCallback {

	public void onFinishedContactCreated(String arg0);
	public void onFinishedGetAllPersons(JSONArray arg0);
	
}
