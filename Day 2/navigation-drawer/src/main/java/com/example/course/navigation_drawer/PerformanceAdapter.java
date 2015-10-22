package com.example.course.navigation_drawer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PerformanceAdapter extends ArrayAdapter<String> {
	
	private Activity activity;
	private String[] names;
	
	public PerformanceAdapter(Context context, String[] names) {
		super(context, R.layout.rowlayout, names);
		this.activity = (Activity) context;
		this.names = names;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if (rowView == null) {
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.rowlayout, null);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.text = (TextView) rowView.findViewById(R.id.label);
			viewHolder.image = (ImageView) rowView.findViewById(R.id.icon);
			rowView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) rowView.getTag();
		String s = names[position];
		holder.text.setText(s);
		holder.image.setImageResource(R.mipmap.ic_launcher);
		return rowView;
	}
	
	static class ViewHolder {
		public TextView text;
		public ImageView image;
	}
	
}
