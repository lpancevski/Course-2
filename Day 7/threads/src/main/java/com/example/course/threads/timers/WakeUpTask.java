package com.example.course.threads.timers;

import java.util.TimerTask;

import android.util.Log;

public class WakeUpTask extends TimerTask {

	@Override
	public void run() {
		Log.i("WakeUpTask", "Running");
	}
	
}