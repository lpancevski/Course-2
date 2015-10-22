package com.example.course.services.binding;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.course.services.IMyAidlInterface;
import com.example.course.services.R;

/**
 * Created by Laze on 10/16/2015.
 */
public class BindingActivity extends AppCompatActivity {

    IMyAidlInterface service;
    AdditionServiceConnection connection;

    EditText value1;
    EditText value2;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.binding_main);
        initService();

        value1 = (EditText) findViewById(R.id.firstValue);
        value2 = (EditText) findViewById(R.id.secondValue);
        result = (TextView) findViewById(R.id.resultText);
    }

    // Method to start the service
    public void calculate(View view) {

        try {
            int add = service.add(new Integer(value1.getText().toString()), new Integer(value2.getText().toString()));
            result.setText("" + add);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        releaseService();
        super.onDestroy();
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

    private void initService() {
        connection = new AdditionServiceConnection();
        Intent i = new Intent();
        i.setAction("com.remote.service.CALCULATOR");
        boolean ret = bindService(i, connection, Context.BIND_AUTO_CREATE);
        Log.d("", "initService() bound with " + ret);
    }

    private void releaseService() {
        unbindService(connection);
        connection = null;
        Log.d("", "releaseService() unbound.");
    }

    class AdditionServiceConnection implements ServiceConnection {

        public void onServiceConnected(ComponentName name, IBinder boundService) {
            service = IMyAidlInterface.Stub.asInterface((IBinder) boundService);
            Log.d("", "onServiceConnected() connected");
            Toast.makeText(BindingActivity.this, "Service connected", Toast.LENGTH_LONG)
                    .show();
        }

        public void onServiceDisconnected(ComponentName name) {
            service = null;
            Log.d("", "onServiceDisconnected() disconnected");
            Toast.makeText(BindingActivity.this, "Service connected", Toast.LENGTH_LONG)
                    .show();
        }
    }
}
