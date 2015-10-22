package com.example.course.broadcast_receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("Message From Receiver", 
				intent.getExtras().getString("message"));
		Toast.makeText(context, "I'm receiver", Toast.LENGTH_SHORT).show();
		Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0025533445"));
		intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent2);
	}

}
