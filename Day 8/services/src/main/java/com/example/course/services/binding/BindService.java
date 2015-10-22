package com.example.course.services.binding;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.example.course.services.IMyAidlInterface;

/**
 * Created by Laze on 10/16/2015.
 */
public class BindService extends Service {

    public boolean running = true;
    private final String tempVariable = "this is example";

    @Override
    public IBinder onBind(Intent arg0) {
        return new IMyAidlInterface.Stub() {

            public int add(int value1, int value2) throws RemoteException {
                Log.d("", String.format("AdditionService.add(%d, %d)", value1, value2));
                return value1 + value2;
            }
        };
    }

}