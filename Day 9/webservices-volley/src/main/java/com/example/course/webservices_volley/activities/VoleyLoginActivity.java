package com.example.course.webservices_volley.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.example.course.webservices.R;
import com.example.course.webservices.callers.RequestCaller;
import com.example.course.webservices.callers.RequestLoginCallback;

public class VoleyLoginActivity extends Activity implements RequestLoginCallback {

	private EditText username = null;
	private EditText password = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
	}

	public void login(View v) {
		RequestCaller.login(this,
				username.getText().toString(),
				password.getText().toString());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public void login(String arg) {
		if (arg.equals("OK")) {
			Intent intent = new Intent(this, VoleyPersonActivity.class);
			startActivity(intent);
		} else {
			AlertDialog.Builder errorLoginDialog = new AlertDialog.Builder(this);
			errorLoginDialog.setTitle("Invalid username or password");
			errorLoginDialog.setPositiveButton("OK", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			errorLoginDialog.create();
			errorLoginDialog.show();
		}
	}


}
