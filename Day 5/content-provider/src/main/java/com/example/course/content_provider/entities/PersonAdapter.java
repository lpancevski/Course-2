package com.example.course.content_provider.entities;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.course.content_provider.R;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends BaseAdapter {

	List<Student> students = new ArrayList<Student>();
	Activity activity;
	
	public PersonAdapter(Activity activity, List<Student> students) {
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
			viewHolder.grade = (TextView) rowView.findViewById(R.id.grade);
			rowView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) rowView.getTag();
		Student student = students.get(position);
		
		holder.name.setText(student.getName());
		holder.grade.setText(student.getGrade());

		return rowView;
	}

	static class ViewHolder {
		public TextView name;
		public TextView grade;
	}

}
