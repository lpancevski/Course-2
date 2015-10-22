package com.example.course.fragments.dinamic_fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.course.fragments.R;

public class LM_Fragment extends Fragment {
	
	InterCallBackFragment activity;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		/**
		 * Inflate the layout for this fragment
		 */
		
		activity.callFragmentOne();
		return inflater.inflate(R.layout.lm_fragment, container, false);
	}
	
	@Override
	public void onAttach(Activity activity) {
		try {
			this.activity = (InterCallBackFragment) activity;
		} catch (ClassCastException e) {
			
		}
		super.onAttach(activity);
	}
	
}