package com.example.course.alarm_service;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.View;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private AlarmManager alarmManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void triggerAlarm(View v) {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, 10);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(
                        MainActivity.this,
                        0,
                        new Intent(
                                MainActivity.this,
                                NotificationActivity.class), 0);

        alarmManager.set(AlarmManager.RTC_WAKEUP,
                c.getTimeInMillis(), pendingIntent);
        System.exit(0);
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
