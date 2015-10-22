package com.example.course.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

	public boolean running = true;
	private final String tempVariable = "this is example";


	@Override
	public IBinder onBind(Intent arg0) {
		return new IMyAidlInterface.Stub() {

			public int add(int value1, int value2) throws RemoteException {
				Log.d("", String.format("AdditionService.add(%d, %d)",value1, value2));
				return value1 + value2;
			}
		};
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// Let it continue running until it is stopped.
		Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
		Thread temp = new Thread() {
			@Override
			public void run() {
				int i = 0;
				while (running) {
					i++;
					Log.i("Message", "" + tempVariable.getBytes());
				}
			}
		};
		temp.start();
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		running=false;
		Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
	}

}
