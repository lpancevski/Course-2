package com.example.course.fragments.statics_fragments;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.course.fragments.R;

public class StaticsFragmentsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Toast.makeText(this, "Trigger Normal Activity", Toast.LENGTH_LONG).show();
		setContentView(R.layout.statics_fragment_main);
	}
}
