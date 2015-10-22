package com.example.course.custom_component;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class CustomUIComponent extends LinearLayout {

	private EditText username = null;
	private EditText password = null;
	private LinearLayout customButton = null;
	Context context;
	CallbackControl activity;
	
	public CustomUIComponent(Context context, AttributeSet attrs) {
		super(context, attrs);

		
		((Activity) getContext()).getLayoutInflater().inflate(
				R.layout.customcomponent, CustomUIComponent.this);
		
		this.context = context;
		try {
			activity = (CallbackControl) context;
		} catch (ClassCastException e) {

		}
		initView();
	}
	

	private void initView() {
		username = (EditText) findViewById(R.id.editview);
		password = (EditText) findViewById(R.id.editview2);
		
		customButton = (LinearLayout) findViewById(R.id.pressbutton);
		customButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				activity.buttonPressed(
						username.getText().toString(), password.getText().toString());
			}
		});
		
	}

	public void setHintToEditText() {
		if (username != null) {
			String currentTextInUsernameInput = username.getText().toString();
			username.setHint(currentTextInUsernameInput);
		}
	}
	
	public void login() {
		
	}
}
