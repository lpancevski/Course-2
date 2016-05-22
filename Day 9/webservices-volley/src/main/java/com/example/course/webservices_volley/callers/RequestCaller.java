package com.example.course.webservices_volley.callers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class RequestCaller {

	// 10.0.2.2 is IP address if you are working with emulator.
	private static final String BASE_URI = "http://192.168.0.102/wstest/index.php/";
	
	//If you are trying on device, then BASE_URI should put have local IP address of the machine (ex: 192.168.1.20) 
//	private static final String BASE_URI = "http://IPAddressOfMachineWherePHPServicesAreHosted/wstest/index.php/";

	//Service on Web
//	private static final String BASE_URI = "webservicesdemo.net16.net/wstest/index.php/";


	private static final String SAVE_PERSON = "contactsservice/createperson";
	private static final String LIST_ALL_PERSONS = "contactsservice/listallpersons";
	private static final String LOGIN = "contactsservice/login";
	
	public static void saveContact(final RequestPersonCallback ctx,
			final String personName, final String personSurname, final String personAddress) {
		RequestQueue queue = Volley.newRequestQueue((Context) ctx);
		
		final String personNameValue = personName; 
		final String personSurnameValue = personSurname; 
		final String personAddressValue = personAddress; 
		
		StringRequest saveContactRequest = new StringRequest(Method.POST, BASE_URI + SAVE_PERSON, new Response.Listener<String>() {
			@Override
			public void onResponse(String arg0) {
				ctx.onFinishedContactCreated(arg0);
			}
			
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				VolleyLog.e("Error: ", error.getMessage());
			}
		}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("personname", personNameValue);
				map.put("personsurname", personSurnameValue);
				map.put("personaddress", personAddressValue);
				return map;
			};
		};
		queue.add(saveContactRequest);
	}
	
	public static void getAllPersons(final RequestPersonCallback ctx) {
		RequestQueue queue = Volley.newRequestQueue((Context) ctx);
		
		ImageRequest req = new ImageRequest("", new Response.Listener<Bitmap>() {
			
			public void onResponse(Bitmap arg0) {
			};
		}, 800,800, Config.ARGB_8888, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				VolleyLog.e("Error: ", error.getMessage());
			}
		});
		
		JsonArrayRequest getAllContactsRequest = new JsonArrayRequest(BASE_URI + LIST_ALL_PERSONS, new Response.Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray arg0) {
				ctx.onFinishedGetAllPersons(arg0);
			}

			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					VolleyLog.e("Error: ", error.getMessage());
				}
			});
		queue.add(getAllContactsRequest);
	}
	
	public static void login(final RequestLoginCallback ctx, String username, String password) {
		
		RequestQueue queue = Volley.newRequestQueue((Context) ctx);
		
		final String usernameValue = username; 
		final String passwordValue = password; 
		StringRequest loginRequest = new StringRequest(Method.POST, BASE_URI + LOGIN, new Response.Listener<String>() {

			@Override
			public void onResponse(String arg0) {
				ctx.login(arg0);
			}

			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					VolleyLog.e("Error: ", error.getMessage());
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("username", usernameValue);
				map.put("password", passwordValue);
				return map;
			}
		};
		queue.add(loginRequest);
	}
}
