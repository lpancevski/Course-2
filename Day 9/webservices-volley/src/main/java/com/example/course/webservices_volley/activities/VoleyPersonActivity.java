package com.example.course.webservices_volley.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.course.webservices_volley.R;
import com.example.course.webservices_volley.callers.RequestCaller;
import com.example.course.webservices_volley.callers.RequestPersonCallback;
import com.example.course.webservices_volley.entity.Person;
import com.example.course.webservices_volley.entity.PersonAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class VoleyPersonActivity extends Activity implements RequestPersonCallback {

	private EditText name = null;
	private EditText surname = null;
	private EditText address = null;
	
	private ListView contactList = null;
	private PersonAdapter adapter = null;
	private List<Person> personObjectsList = new ArrayList<Person>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity_main);
		initView();
	}

	private void initView() {
		adapter = new PersonAdapter(this, personObjectsList);
		contactList = (ListView) findViewById(R.id.contacts);
		contactList.setAdapter(adapter);
		
		name = (EditText) findViewById(R.id.name);
		surname = (EditText) findViewById(R.id.surname);
		address = (EditText) findViewById(R.id.address);
		
	}

	public void createPerson(View v) {
		String nameString = name.getText().toString();
		String surnameString = surname.getText().toString();
		String addressString = address.getText().toString();
		
		if ("".equals(nameString) || "".equals(surnameString) || "".equals(addressString)) {
			Toast.makeText(VoleyPersonActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
			return;
		} else {
			RequestCaller.saveContact(this, nameString, surnameString, addressString);
		}
	}
	
	public void getPersons(View v) {
		RequestCaller.getAllPersons(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public void onFinishedContactCreated(String arg) {
		// Put logic here. Option to show Dialog to notify the user if successfully a person is created.
	}	

	@Override
	public void onFinishedGetAllPersons(JSONArray arg) {
		personObjectsList.clear();
		for(int i = 0; i < arg.length(); i++) {
			JSONObject jsonObject;
			try {
				jsonObject = arg.getJSONObject(i);
				Person personDetails = new Person();
				personDetails.setName(jsonObject.getString("personname"));
				personDetails.setSurname(jsonObject.getString("personsurname"));
				personDetails.setAddress(jsonObject.getString("personaddress"));
				personObjectsList.add(personDetails);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}
		adapter.notifyDataSetChanged();
		
	}

}
