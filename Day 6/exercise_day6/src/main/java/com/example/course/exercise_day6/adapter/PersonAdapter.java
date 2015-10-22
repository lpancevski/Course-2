package com.example.course.exercise_day6.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.course.exercise_day6.R;

public class PersonAdapter extends BaseAdapter {

	List<Person> persons = new ArrayList<Person>();
	Activity activity;
	
	public PersonAdapter(Activity activity, List<Person> persons) {
		this.persons = persons;
		this.activity = activity;
	}
	
	@Override
	public int getCount() {
		return persons.size();
	}

	@Override
	public Object getItem(int arg0) {
		return persons.get(arg0);
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
			viewHolder.surname = (TextView) rowView.findViewById(R.id.surname);
			viewHolder.address = (TextView) rowView.findViewById(R.id.address);
			viewHolder.image = (ImageView) rowView.findViewById(R.id.icon);
			rowView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) rowView.getTag();
		Person person = persons.get(position);
		
		holder.name.setText(person.getName());
		holder.surname.setText(person.getSurname());
		holder.address.setText(person.getAddress());
		
		return rowView;
	}

	static class ViewHolder {
		public TextView name;
		public TextView surname;
		public TextView address;
		public ImageView image;
	}

}
