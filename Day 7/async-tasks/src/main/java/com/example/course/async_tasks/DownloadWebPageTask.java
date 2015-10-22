package com.example.course.async_tasks;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

public class DownloadWebPageTask extends AsyncTask<String, Integer, String> {
	 
	ProgressDialog progressDialog = null;
	Context ctx = null;
	TextView textview = null;
	
	public DownloadWebPageTask(Context ctx, TextView view) {
		this.ctx = ctx;
		textview = view;
	}
	
	
	@Override
	protected void onPreExecute() {
		progressDialog = ProgressDialog.show(ctx,
				ctx.getString(R.string.hello_world),
				ctx.getString(R.string.hello_world), true, false);
		super.onPreExecute();
	}
	
   @Override
   protected String doInBackground(String... urls) {
	   
     String response = "";
     for (String url : urls) {
       DefaultHttpClient client = new DefaultHttpClient();
       HttpGet httpGet = new HttpGet(url);
       publishProgress(10);
       try {
         HttpResponse execute = client.execute(httpGet);
         InputStream content = execute.getEntity().getContent();

         publishProgress(30);
         BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
         String s = "";
         while ((s = buffer.readLine()) != null) {
           response += s;
         }
         publishProgress(100);
       } catch (Exception e) {
         e.printStackTrace();
       }
     }
     return response;
   }

    @Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}
   
   
    @Override
    protected void onPostExecute(String result) {
      progressDialog.dismiss();
      textview.setText(result);
    }
    
 }
