package com.example.course.fragments.dinamic_fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class DinamicsFragmentsActivity extends FragmentActivity implements InterCallBackFragment {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set dynamically fragments 
		Configuration config = getResources().getConfiguration();

		android.support.v4.app.FragmentManager fragmentManager = 
				getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		/**
		 * Check the device orientation and act accordingly
		 */
		if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			/**
			 * Landscape mode of the device
			 */
			LM_Fragment ls_fragment = new LM_Fragment();
			
			if (ls_fragment.isAdded()) {
				fragmentTransaction.show(ls_fragment);
			} else {
				fragmentTransaction.replace(android.R.id.content, ls_fragment);
				fragmentTransaction.addToBackStack("Some string");
			}
		} else {
			/**
			 * Portrait mode of the device
			 */
			PM_Fragment pm_fragment = new PM_Fragment();
			fragmentTransaction.replace(android.R.id.content, pm_fragment);
			
		}
		fragmentTransaction.commit();

	}

	@Override
	public void callFragmentTwo() {
		
	}

	@Override
	public void callFragmentOne() {
		
	}

}
