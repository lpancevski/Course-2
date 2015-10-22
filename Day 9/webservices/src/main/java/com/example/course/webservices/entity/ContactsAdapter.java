package com.example.course.webservices.entity;

import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ContactsAdapter extends BaseAdapter {

	private List<ContactDetails> contactsList;
	private Context context;
	
	public ContactsAdapter(List<ContactDetails> contactsList, Context context) {
		super();
		this.contactsList = contactsList;
		this.context = context;
	}

	@Override
	public int getCount() {
		return contactsList.size();
	}

	@Override
	public Object getItem(int position) {
		return contactsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ContactDetails contactDetails = contactsList.get(position);
		
		TextView textView = new TextView(context);
		textView.setWidth(200);
		textView.setHeight(30);
		textView.setGravity(Gravity.CENTER);
		textView.setText(contactDetails.getContactName() 
				+ " " + contactDetails.getContactSurname() 
				+ " " + contactDetails.getContactAddress());
		
		return textView;
	}

}
