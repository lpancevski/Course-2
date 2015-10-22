package com.example.course.database_sqlite.entities;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.course.database_sqlite.R;

import java.util.ArrayList;
import java.util.List;

public class UserDataAdapter extends BaseAdapter {

	List<UserData> students = new ArrayList<UserData>();
	Activity activity;
	
	public UserDataAdapter(Activity activity, List<UserData> students) {
		this.students = students;
		this.activity = activity;
	}
	
	@Override
	public int getCount() {
		return students.size();
	}

	@Override
	public Object getItem(int arg0) {
		return students.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		View rowView = convertView;
		if (rowView == null) {
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.person_layout, null);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.name = (TextView) rowView.findViewById(R.id.name);
			viewHolder.password = (TextView) rowView.findViewById(R.id.grade);
			rowView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) rowView.getTag();
		UserData student = students.get(position);
		
		holder.name.setText(student.getUserName());
		holder.password.setText(student.getPassWord());

		return rowView;
	}

	static class ViewHolder {
		public TextView name;
		public TextView password;
	}

}
