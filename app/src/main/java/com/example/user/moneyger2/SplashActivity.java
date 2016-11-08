package com.example.user.moneyger2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by nayon on 2016-11-08.
 */
public class SplashActivity extends Activity{

    public static int check=0;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler hd = new Handler(){
            public void handleMessage(Message msg){
                finish();
            }
        };

        hd.sendEmptyMessageDelayed(0,2500);
    }
}
