package com.demo.tomcat.timerdemo;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;


// http://boywhy.blogspot.tw/2015/04/javaandroid-timer.html

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = MainActivity.class.getSimpleName();

    TextView    tvMessage;
    Button      btnOnOff;

    Timer       timer;
    TimerTask   timerTask;

    boolean     startOnOff = false;
    String      timeString = "";
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat    sdf = new SimpleDateFormat("yyyy/MM/dd  hh:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate()...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initControl();
    }

    @Override
    protected void onStart()
    {
        Log.d(TAG, "onStart()...");
        super.onStart();
    }

    @Override
    protected void onResume()
    {
        Log.d(TAG, "onResume()...");
        tvMessage.setText(sdf.format(System.currentTimeMillis()) );
        super.onResume();
    }

    @Override
    protected void onDestroy()
    {
        Log.d(TAG, "onDestroy()...");
        super.onDestroy();
    }

    public void ButtonOnClick(View view)
    {
        if (!startOnOff)
        {
            startTimer();
            btnOnOff.setText("STOP");
            startOnOff = true;
        }
        else if (startOnOff)
        {
            stopTimerTask();
            btnOnOff.setText("START");
            startOnOff = false;
        }

    }

    //---------------------- User define funciton ------------------------------------//
    private void initView()
    {
        Log.d(TAG, "initView()...");
        tvMessage = findViewById(R.id.tvMsgBox);
        btnOnOff = findViewById(R.id.btn_OnOff);
    }

    private void initControl()
    {
        Log.d(TAG, "initControl()...");
        tvMessage.setTextSize(24);
        tvMessage.setTextColor(Color.BLUE);
    }

    public void startTimer()
    {
        Log.d(TAG, "startTimer()...");
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                timeString = sdf.format(System.currentTimeMillis());
                handler.obtainMessage(1).sendToTarget();
                //tvMessage.setText(timeString);
                Log.d(TAG,  timeString);
            }
        };

        timer.schedule(timerTask, 1000 * 30, 1000 * 10);
    }

    public void stopTimerTask()
    {
        if (timer != null)
        {
            timer.cancel();
            timer = null;
        }
    }

    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            tvMessage.setText(timeString);
            //super.handleMessage(msg);
        }
    };

}

