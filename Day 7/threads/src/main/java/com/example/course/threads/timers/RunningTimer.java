package com.example.course.threads.timers;

import android.os.CountDownTimer;
import android.util.Log;

/**
 * This is my class.
 */
public class RunningTimer extends CountDownTimer {

	public RunningTimer(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
	}

	@Override
	public void onFinish() {
		Log.i("RunningTimerStatus", "Finished");
	}

	@Override
	public void onTick(long millisUntilFinished) {
		Log.i("RunningTimerStatus", "Tick");
	}
	
}