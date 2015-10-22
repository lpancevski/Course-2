package com.example.course.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_CODE = 1920;

    private NotificationManager notificationManager = null;

    public void triggerNotification(View v) {

        // This is new implementation of calling notification

        PendingIntent contentNotificationPendingIntent =
                PendingIntent.getActivity(
                        MainActivity.this, 0,
                        new Intent(MainActivity.this, NotificationActivity.class),
                        0);

        Bundle bundle = new Bundle();
        bundle.putString("some", "key");
        PendingIntent smsNotificationPendingIntent =
                PendingIntent.getActivity(
                        MainActivity.this, 0,
                        new Intent(MainActivity.this, SMSActivity.class),
                        0, bundle);

        Uri telNumber = Uri.parse("tel:022445525");
        PendingIntent callNotificationPendingIntent =
                PendingIntent.getActivity(
                        MainActivity.this, 0,
                        new Intent(Intent.ACTION_DIAL, telNumber),
                        0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Event tracker")
                .setContentText("Events received")
                .addAction(R.mipmap.call, "Call", callNotificationPendingIntent)
                .addAction(R.mipmap.sms, "SMS", smsNotificationPendingIntent);

        mBuilder.setContentIntent(contentNotificationPendingIntent);

        Notification notification = mBuilder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager =
                (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(RESULT_CODE, notification);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
