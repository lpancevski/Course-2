package com.example.course.threads;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.course.threads.timers.RunningTimer;
import com.example.course.threads.timers.WakeUpTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private Handler handler = null;
    private Handler secondHandler = null;
    TextView output;

    private RunningTimer runningTimer = null;
    private WakeUpTask wakeUpTask = null;
    private Timer timer = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = (TextView) findViewById(R.id.outputText);
    }

    public void triggerRunningTimer(View v) {
        runningTimer = new RunningTimer(11000, 500);
        runningTimer.start();
    }

    public void triggerTimer(View v) {
        wakeUpTask = new WakeUpTask();
        timer = new Timer();
        timer.schedule(wakeUpTask, 1000);
    }

    public void triggerHandler(View v) {

        handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Log.i("Handler Message", "Hi From Handler");
                Toast.makeText(MainActivity.this, "Handler Message, Hi From Handler", Toast.LENGTH_SHORT).show();
            }
        }, 2000);
    }

    public void triggerSecondHandler(View v) {
        secondHandler = new Handler() {
            public void handleMessage(Message msg) {
                String bundleResult = msg.getData().getString("RESPONSE");
                output.setText(bundleResult);
            }
        };
        triggerSecondHandler();
    }

    private void triggerSecondHandler() {
        new Thread() {
            public void run() {
                try {
                    DefaultHttpClient client = new DefaultHttpClient();
                    HttpGet httpMethod = new HttpGet("http://dl.dropboxusercontent.com/u/63837578/apache-commons.txt");
                    HttpResponse httpResponse = client.execute(httpMethod);
                    HttpEntity entity = httpResponse.getEntity();

                    Message obtainMessage =
                            secondHandler.obtainMessage();
                    Bundle bundle = new Bundle();
                    bundle.putString("RESPONSE", EntityUtils.toString(entity));
                    obtainMessage.setData(bundle);
                    secondHandler.sendMessage(obtainMessage);

                } catch (ClientProtocolException e) {
                    // log and or handle
                } catch (IOException e) {
                    // log and or handle
                }
            }
        }.start();
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
