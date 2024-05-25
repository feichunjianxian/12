package com.example.testbindservice;

import static com.example.testbindservice.R.id.btn_bind;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_bind,btn_use,btn_unbind;

    private MyConn myConn;

    private MyService.MyBinder mBinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        btn_bind=findViewById(R.id.btn_bind);
        btn_use=findViewById(R.id.btn_use);
        btn_unbind=findViewById(R.id.btn_unbind);
        btn_bind.setOnClickListener(this);
        btn_use.setOnClickListener(this);
        btn_unbind.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_bind) {
            if (myConn==null){
                myConn=new MyConn();
            }
            Intent intent = new Intent(this, MyService.class);
            bindService(intent,myConn,BIND_AUTO_CREATE);
        } else if (view.getId()==R.id.btn_use) {
            mBinder.callMethodInService();
        }
        else if(view.getId()==R.id.btn_unbind){
            if(myConn!=null){
                unbindService(myConn);
                myConn=null;
            }
        }
    }

    class MyConn implements ServiceConnection{
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBinder= (MyService.MyBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
}