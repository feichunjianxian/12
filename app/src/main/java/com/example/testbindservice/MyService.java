package com.example.testbindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

   class MyBinder extends Binder{
        public void callMethodInService(){
            methodInService();
        }
   }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       return new MyBinder();
    }

    public void methodInService(){
        Log.i("MainActivity","调用服务里的方法");
    }
    @Override
    public void onCreate() {super.onCreate();
        Log.i("MainActivity","调用onCreate()");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity","调用onDestroy()");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("MainActivity","调用onUnbind()");
        return super.onUnbind(intent);
    }
}