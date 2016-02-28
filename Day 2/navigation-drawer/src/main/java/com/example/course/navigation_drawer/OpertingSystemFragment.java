package com.example.course.navigation_drawer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OpertingSystemFragment extends Fragment {
	
	  public static final String ARG_OS = "OS";
	  private String value;

	  @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
		  Bundle savedInstanceState) {
		  View view = inflater.inflate(R.layout.fragment_layout, null);
		  TextView textView = (TextView) view.findViewById(R.id.textView1);
		  textView.setText(" " + value);
		  return view;
	  }

	  @Override
	  public void setArguments(Bundle args) {
		value = args.getString(ARG_OS);
	  }
}