package com.example.course.webservices.utilities;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.example.course.webservices.entity.ContactDetails;

public class AsyncWSClient {
	
	private static final String HTTP_PROTOCOL = "http";
	private static final String BASE_URI = "192.168.0.102/wstest/index.php/";

	//Service on Web
//	private static final String BASE_URI = "webservicesdemo.net16.net/wstest/index.php/";
	private static final String SAVE_PERSON = "contactsservice/createperson";
	private static final String G_LIST_PERSONS = "contactsservice/listallpersons";
	
	private static AsyncWSClient wsClient = null;
	public static AsyncWSClient getInstance() {
		if (wsClient == null) {
			wsClient = new AsyncWSClient();
		}
		
		return wsClient;
	}
	
	public void saveContactPost(String userMail, String contactName, String contactNumber) throws ClientProtocolException, IOException {
		
		List<NameValuePair> listParams = new ArrayList<NameValuePair>();
		listParams.add(new BasicNameValuePair("personname", userMail));
		listParams.add(new BasicNameValuePair("personsurname", contactName));
		listParams.add(new BasicNameValuePair("personaddress", contactNumber));
		String entityResponse = "";
		HttpRequestParameters parameters = new HttpRequestParameters();
		parameters.values = listParams;
		parameters.url = HTTP_PROTOCOL+"://"+BASE_URI+""+SAVE_PERSON;
		
		try {
			   entityResponse = new HttpRequestTask().execute(parameters).get();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		System.out.println(entityResponse);
		
	}
	
	public List<ContactDetails> listAllContactsByUser(String userEmail)
			throws IOException {
		List<ContactDetails> contactDetailsList = null;
		
		List<NameValuePair> listParams = new ArrayList<NameValuePair>();
		listParams.add(new BasicNameValuePair("usermail", userEmail));
		
		String entityResponse = "";
		
		HttpRequestParameters parameters = new HttpRequestParameters();
		parameters.values = listParams;
		parameters.url = HTTP_PROTOCOL+"://"+BASE_URI+""+G_LIST_PERSONS;
		try {
		   entityResponse = new HttpRequestTask().execute(parameters).get();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(entityResponse);
		
		if (!entityResponse.contains("error")) {
			
			contactDetailsList = new LinkedList<ContactDetails>();
			
			try {
				JSONArray jsonArray = new JSONArray(entityResponse);
			
				for(int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					
					ContactDetails contactDetails = new ContactDetails();
					contactDetails.setContactId(jsonObject.getInt("id"));
					contactDetails.setContactName(jsonObject.getString("personname"));
					contactDetails.setContactSurname(jsonObject.getString("personsurname"));
					contactDetails.setContactAddress(jsonObject.getString("personaddress"));
				
					contactDetailsList.add(contactDetails);
				
				}
			
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}
		return contactDetailsList;
	}
	
	private static class HttpRequestTask extends AsyncTask<HttpRequestParameters, Void, String> {

		@Override
		protected String doInBackground(HttpRequestParameters... params) {
			HttpClient httpClient = new DefaultHttpClient();
			
			String entityResponse = "";
			HttpRequestParameters httpRequestParameters = params[0];
			HttpPost httpPost = new HttpPost(httpRequestParameters.url);
			
			HttpEntity httpEntity;
			try {
				httpEntity = new UrlEncodedFormEntity(httpRequestParameters.values);
				httpPost.setEntity(httpEntity);
				
				HttpResponse httpResponse = httpClient.execute(httpPost);
				
				HttpEntity entity = httpResponse.getEntity();
				
				entityResponse = EntityUtils.toString(entity);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return entityResponse;
		}
		
	}
}
