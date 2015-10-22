package com.example.course.explicit_intents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends Activity {
	
	private Button pushBack = null;
	private EditText editTExt = null;
	String string;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		initView();
	}

	private void initView() {
		editTExt = (EditText) findViewById(R.id.pushedValue);
		pushBack = (Button) findViewById(R.id.pushBack);
		
		
		Bundle extras = getIntent().getExtras();	
		if (extras != null) {
			string = extras.getString("pushedValue");
			editTExt.setText(string);
			Answer answer = extras.getParcelable("pushedParcelable");
			Toast.makeText(this, answer.getmOrderNumber() + " " + answer.getmIsCorrect() + " " + answer.getmText(), Toast.LENGTH_SHORT).show();
		}
		
		pushBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
	}

	@Override
	public void finish() {
		
		Intent returnValue = new Intent();
		returnValue.putExtra("pushedBackValue", editTExt.getText().toString());
		setResult(RESULT_OK, returnValue);
		
		super.finish();
	}
}
